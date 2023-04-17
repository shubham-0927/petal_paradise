package com.example.plants.fragments.loginRegister

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.plants.R
import com.example.plants.activities.ShoppingActivity
import com.example.plants.data.UserData
import com.example.plants.data.Users
import com.example.plants.databinding.FragmentRegisterBinding
import com.example.plants.fragments.shopping.PlantDetailsFragment
import com.example.plants.viewmodel.RegisterViewModel
import com.example.plants.viewmodel.RegisterViewModelFactory
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.realm.ImportFlag
import io.realm.Realm
import io.realm.kotlin.internal.interop.RealmAppT
import io.realm.kotlin.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.Document
import java.util.*


class RegistrationFragment: Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    val PICK_IMAGE_REQUEST = 1
    lateinit var galleryButton: Button
    lateinit var galleryButtonText : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        Realm.init( context)
/*        val connectionString =
            (*//* connectionString = *//* "mongodb+srv://Master:%30%5FRAtlas%5Fadms%40PRS@atlascluster.npc8jrq.mongodb.net/?retryWrites=true&w=majority")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        val mongoClient = MongoClients.create(settings)
        val database = mongoClient.getDatabase("patalParadisedb")*/

/*        val uri = "mongodb+srv://Master:%30%5FRAtlas%5Fadms%40PRS@atlascluster.npc8jrq.mongodb.net/?retryWrites=true&w=majority"
        val connString = ConnectionString(uri);
        val mongoClient : MongoClient = MongoClients.create(connString)
        val database: MongoDatabase = mongoClient.getDatabase("patalParadisedb")*/

/*        val uri = MongoClientURI("mongodb+srv://Master:%30%5FRAtlas%5Fadms%40PRS@atlascluster.npc8jrq.mongodb.net/?retryWrites=true&w=majority")
        val client = MongoClients.create(uri.toString())
        val database =client.getDatabase("patalParadisedb")*/

/*        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString("mongodb+srv://Master:%30%5FRAtlas%5Fadms%40PRS@atlascluster.npc8jrq.mongodb.net/?retryWrites=true&w=majority"))
            .build()
        val client = MongoClients.create(settings)*/
//        val collection = database.getCollection("users")


        val appID = "application-0-yyhyb"
        val app = io.realm.mongodb.App(AppConfiguration.Builder(appID).build())
//        val realmApp :RealmApp.create(appConfig)
        val realm =Realm.getDefaultInstance()


        galleryButton = binding.selectButton
        galleryButtonText = binding.profilepictext
        galleryButton.setOnClickListener {
            Toast.makeText(context, "select a image", Toast.LENGTH_SHORT).show()
            openGallery()
        }
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(app)).get(RegisterViewModel::class.java)
        binding.register.setOnClickListener{


            val email  = binding.userEmailET.text.toString()
            val password = binding.password.text.toString()
            val repass = binding.retypePass.text.toString()
            val username = binding.usernameET.text.toString()
            val mobilenumber = binding.mobilenumber.text.toString()
            val dob = binding.birthdate.text.toString()
            val addr = binding.address.text.toString()
            val userData = UserData(email,password)
            //create new Users object
            val users = Users()
            //query for duplicate user
            val task = realm.where(Users::class.java).equalTo("email", email).count()
//            val taskQuery = app.where(Users::class.java)
            if(username.isEmpty()){
                Toast.makeText(context, "Empty username", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()){
                Toast.makeText(context, "Empty Email field", Toast.LENGTH_SHORT).show()
            } else if(mobilenumber.isEmpty()){
                Toast.makeText(context, "Empty mobile number field", Toast.LENGTH_SHORT).show()
            } else if (dob.isEmpty()){
                Toast.makeText(context, "Enter dob", Toast.LENGTH_SHORT).show()
            }else if (password.length<6){
                Toast.makeText(context, "length of password >6", Toast.LENGTH_SHORT).show()
            } else if (repass != password){
                Toast.makeText(context, "password is not matching", Toast.LENGTH_SHORT).show()
            }else if(task >0){
                Toast.makeText(context, "email already exists", Toast.LENGTH_SHORT).show()
                Log.v("EXAMPLE", "Fetched object by primary key: $task")
            }else{
/*                val task : Task = Task()
                task.name = "New Task"

                backgroundThreadRealm.executeTransaction { transactionRealm ->
                    transactionRealm.insert(task)
                }*/
                binding.registerTV.visibility = View.INVISIBLE
                binding.registerProgessBar.visibility = View.VISIBLE
                users._id = UUID.randomUUID().toString()
                users.username = username
                users.email = email
                users.mobilenumber = mobilenumber
                users.dob = dob
                users.address = addr
                users.image = "imageUrl"

          /*      val database = client.getDatabase("patalParadisedb")
                val collection = database.getCollection("users")
                val document = Document("name","fisrtUser")
                document.append("email","fisrtuser@gmail.com")
                collection.insertOne(document)
                val task1 = collection.find()
                Log.v("EXAMPLE", "collection :$task1")*/



                val document = Document()
                document.append("_id",users._id)
                document.append("username",users.username)
                document.append("email",users.email)
                document.append("mobilenumber",users.mobilenumber)
                document.append("dob",users.dob)
                document.append("address",users.address)
                document.append("image",users.image)
//                collection.insertOne(document)
                realm.executeTransactionAsync{
                    realm->
                    realm.createObject(Users::class.java, users._id).apply {
                    this.username=  users.username
                    this.email = users.email
                    this.mobilenumber = users.mobilenumber
                    this.dob = users.dob
                    this.address = users.address
                    this.image = users.image
                    realm.insertOrUpdate(users)
                        realm.copyToRealmOrUpdate(users,
                            ImportFlag.CHECK_SAME_VALUES_BEFORE_SET)

                    }
                }

                //register user to realm using viewmodel
                viewModel.registerUsers(userData)

                //login user
                fragmentManager?.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<LoginFragment>(R.id.fragmentContainerView)
                    addToBackStack(null)
                }
                /*app.loginAsync(Credentials.emailPassword(email, password)) { result ->
                    if (result.isSuccess) {
                        // Successful login, navigate to main activity
                        val user: io.realm.mongodb.User? = app.currentUser()!!
                        val email = user?.profile?.email
                        Log.d("V","email:$email")
                        val realm = Realm.getDefaultInstance()
                        val task = realm.where(Users::class.java).equalTo("email", email).findFirst()
                        val intent = Intent(context, ShoppingActivity::class.java)

                        intent.putExtra("email",task?.email)
                        intent.putExtra("username",task?.username)
                        intent.putExtra("mobilenumber",task?.mobilenumber)
                        intent.putExtra("dob",task?.dob)
                        intent.putExtra("address",task?.address)
                        intent.putExtra("image",task?.image)
                        startActivity(intent)
                        Toast.makeText(context, "Successfully registered and login", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    } else {
                        // Failed login, display error message
                        val errorMessage = result.error.errorMessage
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }*/
     /*           val task = realm.where(Users::class.java).findAllAsync()
                Log.v("EXAMPLE", "Fetched object by primary key: $task")*/


            }


        }
        viewModel.registrationResult.observe(viewLifecycleOwner, { result ->
            result.onSuccess {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }.onFailure {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
//        realm.close()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            val imageUrl = data.data
            val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
            val cursor =
                imageUrl?.let { activity?.contentResolver?.query(it, projection, null, null , null) }
            cursor?.moveToFirst()
            val imageName = cursor?.getString(0)
            cursor?.close()
            galleryButtonText.text = imageName
        }
    }

}