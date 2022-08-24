package com.example.quiz.ui


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.adapter.QuizViewModel
import com.example.quiz.databinding.FragmentQuestionsBinding

import kotlinx.android.synthetic.main.item_questions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionsFragment(
    private var category: String? = null,
    private var userEmail: String
) : Fragment() {

    //    @Inject
//    lateinit var viewModelProvider: QuestionsViewModelProvider
    private lateinit var buttonOptions: List<Button>
    private lateinit var currentQuestion: TextView
    private lateinit var binding: FragmentQuestionsBinding
    private var correctAnswer = ""
    private var index = 0
    private var result = 0
    private var number = 0

    private val viewModel by viewModels<QuizViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category?.let { viewModel.getQuestions(it) }
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            correctAnswer = it.correctAnswer
        }




        buttonOptions = mutableListOf(
            binding.firstOption,
            binding.secondOption,
            binding.thirdOption,
            binding.fourthOption
        )


        firstOption.setOnClickListener {
            index = 0
            checkAnswer(buttonOptions, index)

        }
        secondOption.setOnClickListener {
            index = 1
            checkAnswer(buttonOptions, index)
        }

        thirdOption.setOnClickListener {
            index = 2
            checkAnswer(buttonOptions, index)
        }

        fourthOption.setOnClickListener {
            index = 3
            checkAnswer(buttonOptions, index)
        }
        loadQuestions()
        viewModel.questionNumberLiveData.observe(viewLifecycleOwner) {
            number = it
            binding.root.run {
                currentQuestion = findViewById(R.id.currentQuestion)
                currentQuestion.text = (it + 1).toString()  + "/10"
            }
        }
    }


    private fun loadQuestions() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            binding.root.run {
                var answers: ArrayList<String> = arrayListOf()
                answers = arrayListOf(
                    it.correctAnswer,
                    it.incorrectAnswers[0],
                    it.incorrectAnswers[1],
                    it.incorrectAnswers[2]
                )
                answers.shuffle()
                question.text = it.question
                firstOption.text = answers[0]
                secondOption.text = answers[1]
                thirdOption.text = answers[2]
                fourthOption.text = answers[3]
            }
        }


    }

    private fun setButtonsDisabled() {
        for (element in buttonOptions) {
            element.isEnabled = false
        }
    }

    private fun setButtonsEnabled() {
        for (element in buttonOptions) {
            element.isEnabled = true
        }
    }

    private fun setWhiteBackgroundForButtons() {
        for (element in buttonOptions) {
            element.setBackgroundColor(Color.WHITE)
        }
    }

    private fun checkAnswer(buttonOptions: List<Button>, index: Int) {
        setButtonsDisabled()
        if (buttonOptions[index].text == correctAnswer) {
            buttonOptions[index].setBackgroundColor(Color.GREEN)
            result++
        } else {
            buttonOptions[index].setBackgroundColor(Color.RED)
            for (element in buttonOptions) {
                if (element.text.toString() == correctAnswer) {
                    element.setBackgroundColor(Color.GREEN)
                }
            }
        }
        options()


    }

    private fun options() {
        if (number > 8) {
            Handler(Looper.getMainLooper()).postDelayed({
            }, 1000L)
            lifecycleScope.launch(Dispatchers.IO) {
                Handler(Looper.getMainLooper()).postDelayed({
                }, 1000L)
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        StatsFragment(userEmail, result, viewModel.questions)
                    )
                    .addToBackStack("")
                    .commit()
            }
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.loadNextQuestion()
                loadQuestions()
                setWhiteBackgroundForButtons()
                setButtonsEnabled()
            }, 1000L)
        }


    }


}


