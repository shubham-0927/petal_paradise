package com.example.plants.fragments.recommendation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.plants.R
import androidx.appcompat.app.AppCompatActivity
import com.example.plants.databinding.FragmentCareBinding
import com.example.plants.fragments.shopping.SearchFragment
import com.google.android.material.snackbar.Snackbar

class CareFragment: Fragment(R.layout.fragment_care)  {
    private lateinit var binding: FragmentCareBinding
//    private lateinit var low: TextView
//    private lateinit var mid: TextView
//    private lateinit var high: TextView
//    private lateinit var back : ImageView
//    private lateinit var next : ImageView )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCareBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call your function here or perform UI setup
        registerButtonClick(binding.root)
    }

    private val questions = listOf(
        MCQQuestion("Heya Ritesh !! How you have been feeling lately?", listOf("Not great.", "Happy.", "Anxious.", "Depressed.")),
        MCQQuestion("Ummm…I think you also get mood swings a lot.", listOf("Not at all.", "Several days.", "More than half a day -almost every day.")),
        MCQQuestion("Well well Can u control the emotion like this character does.", listOf("No.", "Yes easily.", "Hard to say but can try.")),
        MCQQuestion("So how’s life going?", listOf("Stress.", "Normal.", "Happy.", "Sad or Depressed.")),
        MCQQuestion("Have you ever felt anxious or panic attack?", listOf("Yes.", "No.")),
        MCQQuestion("So well I have a proper sleep schedule ! You tell me about yours.", listOf("Sleeps too much.", "Sleeps well.", "Can not Sleep properly.")),
        MCQQuestion("I am a foodie I love food. What about you do you have a proper appetite?", listOf("Poor appetite.", "Over eating.", "Proper diet.")),
        MCQQuestion("How’s the Josh ??  How’s ur energy level for now?", listOf("Low.", "Tired almost everyday.","Normal","Exited.")),
        MCQQuestion("How well are you socially connected with other?", listOf("Introvert.", "Extrovert but now live alone.", "Have good social connects.")),
        MCQQuestion("What do you feel about your life ?", listOf("Feeling of harming myself.", "Feeling of Sucide.", "Well not giving up so easily.","Blessed to be alive."))
        // Add more MCQ questions with options here...
    )

    private val userResponses = mutableListOf<String>()
    private var currentQuestionIndex = 0
    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup



    private fun registerButtonClick(view: View) {
        val nextButton = binding.nextButton
        nextButton.setBackgroundColor(Color.parseColor("#4E4467"))
        questionTextView = binding.koalaDilogueText
        optionsRadioGroup = binding.optionsRadioGroup
        displayCurrentQuestion()

        binding.nextButton.setOnClickListener { view:View ->
            onFabClick(view)
        }
    }

    private fun onFabClick(view: View) {
        // Check if any option is selected
        if (optionsRadioGroup.checkedRadioButtonId == -1) {
            Snackbar.make(view, "Please select an option", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Get the user's response
        val selectedRadioButtonId = binding.optionsRadioGroup.checkedRadioButtonId
        val selectedRadioButton = binding.optionsRadioGroup.findViewById<RadioButton>(selectedRadioButtonId)
        val selectedOption = selectedRadioButton.text.toString()

        // Store the user's response
        userResponses.add(selectedOption)

        // Clear the selected option
        optionsRadioGroup.clearCheck()

        // Move to the next question or finish if all questions are answered
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            displayCurrentQuestion()
        } else {
           val fragmentSearch = SearchFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentSearch)
            }
        }
    }


    private fun displayCurrentQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.question
            optionsRadioGroup.removeAllViews()

            for (option in currentQuestion.options) {
                val radioButton = RadioButton(requireContext())
                radioButton.text = option
                radioButton.id = View.generateViewId()


                radioButton.setBackgroundResource(R.drawable.radio_button_background)

                val textColorSelector = ContextCompat.getColorStateList(requireContext(), R.color.radio_button_text_selector  )
                radioButton.setTextColor(textColorSelector)

                val textSizeInSp = 20 // Set the text size in sp (change to your desired size)
                val textSizeInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    textSizeInSp.toFloat(),
                    resources.displayMetrics
                )
                radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPx)

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val marginBottomInPx = 50 // Set the margin bottom in pixels (change to your desired margin)
                layoutParams.bottomMargin = marginBottomInPx

                val paddingHorizontalInPx = 40
                val paddingVerticalInPx = 30
                radioButton.setPadding(
                    paddingHorizontalInPx,
                    paddingVerticalInPx,
                    paddingHorizontalInPx,
                    paddingVerticalInPx
                )
                radioButton.layoutParams = layoutParams


                optionsRadioGroup.addView(radioButton)
            }
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        low = binding.low
//        mid = binding.mid
//        high = binding.high
//        back = binding.icon2
//        next = binding.icon1
//        val place = arguments?.getString("place")
//        low.setOnClickListener {
//            low.setBackgroundColor(resources.getColor(R.color.g_blue100))
//            val bundle = Bundle()
//            bundle.putString("place",place)
//            bundle.putString("care","low")
//            val fragmentB = SpaceFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//            }
//        }
//        mid.setOnClickListener {
//            mid.setBackgroundColor(resources.getColor(R.color.g_blue100))
//            val bundle = Bundle()
//            bundle.putString("place",place)
//            bundle.putString("care","mid")
//            val fragmentB = SpaceFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//
//            }
//        }
//        high.setOnClickListener {
//            high.setBackgroundColor(resources.getColor(R.color.g_blue100))
//
//            val bundle = Bundle()
//            bundle.putString("place",place)
//            bundle.putString("care","high")
//            val fragmentB = SpaceFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//
//            }
//        }
//        next.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("place","any" )
//            bundle.putString("care","any" )
//            val fragmentB = SpaceFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//            }
//        }
//        back.setOnClickListener {
//            val fragmentB = PlaceFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//            }
//        }
//    }
}

data class MCQQuestion(val question: String, val options: List<String>)