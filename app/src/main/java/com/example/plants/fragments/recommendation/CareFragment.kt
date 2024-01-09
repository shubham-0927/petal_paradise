package com.example.plants.fragments.recommendation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.plants.R
import com.example.plants.databinding.FragmentCareBinding
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
        MCQQuestion("How much sunlight does the location receive?", listOf("Full Sun", "Partial Sun", "partial shade", "Shade")),
        MCQQuestion("How often do you plan to water your plant?", listOf("Daily ", "Every 2-3 days.", "Weekly."," Bi-weekly")),
        MCQQuestion("How much space do you have for your plant?.", listOf("Small tabletop.", "Medium-sized floor space", "Large floor space","Hanging option")),
        MCQQuestion("How much time are you willing to invest in plant care?", listOf("Low maintenance.", "Moderate maintenance.", "High maintenance.", " I'm a plant enthusiast, no limits!")),
        MCQQuestion("Are you interested in plants that change with the seasons?", listOf(" Yes, I love seasonal variety!.", " No, I prefer consistent appearance")),
        MCQQuestion("Will the plant be kept indoors or outdoors?", listOf("Indoors", "Outdoors", "Both")),
        MCQQuestion("How well can your chosen location maintain temperature?", listOf("Stable temperature", "Mild fluctuations", "Seasonal variations"," Extreme variations")),
        MCQQuestion("What's the main purpose of your plant?", listOf("Decoration.", "Air purification"," Edible produce","Medicinal purposes")),
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
           val fragmentSearch = SuggestedplantFragment()
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