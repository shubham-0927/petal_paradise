package com.example.plants.fragments.shopping

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.plants.R
import com.example.plants.activities.ShoppingActivity
import com.example.plants.databinding.FragmentSearchBinding
import com.example.plants.ml.PlantModel
import org.tensorflow.lite.support.image.TensorImage
import java.io.IOException

class SearchFragment: Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var tvOutput: TextView
    private val GALLERY_REQUEST_CODE = 123
    private var resolver = ShoppingActivity()
    private var appContext = context /*ShoppingActivity().applicationContext*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = binding.imgDetect
        button = binding.btnCaptureImage
        tvOutput = binding.outputText
        val buttonLoad = binding.btnLoadImage
        button.setOnClickListener{
            if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                {
         takePicturePreview.launch(null)
        }
        else {
            requestPermission.launch(android.Manifest.permission.CAMERA)
            }
        }
        buttonLoad.setOnClickListener{
            if(ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ==PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg","image/png","image/jpg")
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                onresult.launch(intent)
            }else{
                requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        tvOutput.setOnClickListener {
/*            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${tvOutput.text}"))
            startActivity(intent)*/
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<PlantDetailsFragment>(R.id.shoppinHostFragment)
            }
        }
        imageView.setOnLongClickListener{
            requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return@setOnLongClickListener true
        }

    }
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        granted -> if(granted){
            takePicturePreview.launch(null)
        }else{
        Toast.makeText(requireContext(),"permission Denied!!", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap->if(bitmap != null) {
            imageView.setImageBitmap(bitmap)
            outputGenerator(bitmap)
        }
    }
    private val onresult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        Log.i("TAG","This is the result : ${result.data} ${result.resultCode}")
        onResultReceived(GALLERY_REQUEST_CODE,result)
    }
    private fun onResultReceived(requestCode: Int, result: ActivityResult?){
        when(requestCode){
            GALLERY_REQUEST_CODE->{
                if(result?.resultCode == Activity.RESULT_OK){
                    result.data?.data?.let { uri ->
                        Log.i("TAG", "onResultReceived: $uri")

                        val bitmap = BitmapFactory.decodeStream(ShoppingActivity().contentResolver.openInputStream(uri))
                        imageView.setImageBitmap(bitmap)
                        outputGenerator(bitmap)
                    }
                }else{
                    Log.e("TAG","onActivityResult: error in selecting image")
                }
            }
        }
    }
    private fun outputGenerator(bitmap: Bitmap){
        val plantModel = PlantModel.newInstance(requireContext())

// Creates inputs for reference.
        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true)
        val tfimage = TensorImage.fromBitmap(newBitmap)

// Runs model inference and gets result.
        val outputs = plantModel.process(tfimage)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }

        val highprobabilityOutput = outputs[0]

        tvOutput.text = highprobabilityOutput.label
        Log.i("TAG","outputGenerator: $highprobabilityOutput")

// Releases model resources if no longer used.

    }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted: Boolean ->
        if(isGranted) {
            AlertDialog.Builder(requireContext()).setTitle("Download Image?")
                .setMessage("Do you want to download this image to your device?")
                .setPositiveButton("yes"){_,_ ->
                    val drawable:BitmapDrawable = imageView.drawable as BitmapDrawable
                    val bitmap = drawable.bitmap
                    downloadImage(bitmap)
                }
                .setNegativeButton("No"){dialog, _ -> dialog.dismiss()}
                .show()
        }else{
            Toast.makeText(requireContext(),"Please allow permission to download image", Toast.LENGTH_LONG).show()
        }
    }
    private fun downloadImage(mBitmap: Bitmap):Uri?{
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME,"Plant_Images"+ System.currentTimeMillis()/1000)
            put(MediaStore.Images.Media.MIME_TYPE,"image/png")
        }
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        if(uri != null){
            ShoppingActivity().contentResolver.insert(uri, contentValues)?.also{
                ShoppingActivity().contentResolver.openOutputStream(it).use{ outputStream ->
                    if(!mBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)){
                        throw IOException("Couldn't save the bitmap")
                }else{
                    Toast.makeText(appContext,"Image Saved", Toast.LENGTH_LONG).show()
                    }
                }
                return it
            }
        }
        return null
    }
}