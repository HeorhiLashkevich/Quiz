package com.example.quiz.ui


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quiz.adapter.QuizViewModel
import com.example.quiz.databinding.FragmentQuizBinding
import kotlinx.android.synthetic.main.fragment_quiz.view.firstOption
import kotlinx.android.synthetic.main.fragment_quiz.view.fourthOption
import kotlinx.android.synthetic.main.fragment_quiz.view.question
import kotlinx.android.synthetic.main.fragment_quiz.view.secondOption
import kotlinx.android.synthetic.main.fragment_quiz.view.thirdOption
import kotlinx.android.synthetic.main.item_questions.*

class QuizFragment(
    private var category: String? = null
) : Fragment() {

    //    @Inject
//    lateinit var viewModelProvider: QuestionsViewModelProvider
    private lateinit var buttonOptions: List<Button>
    private lateinit var binding: FragmentQuizBinding
    private var correctAnswer = ""
    private var index = 0

    private val viewModel by viewModels<QuizViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
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
    }



    private fun loadQuestions() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            binding.root.run {
                val answers: ArrayList<String> = arrayListOf()
                answers.add(it.correctAnswer)
                answers.add(it.incorrectAnswers[0])
                answers.add(it.incorrectAnswers[1])
                answers.add(it.incorrectAnswers[2])
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
        } else {
            buttonOptions[index].setBackgroundColor(Color.RED)
            for (element in buttonOptions) {
                if (element.text.toString() == correctAnswer) {
                    element.setBackgroundColor(Color.GREEN)
                }
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.loadNextQuestion()
            loadQuestions()
            setWhiteBackgroundForButtons()
            setButtonsEnabled()
        }, 1000L)


    }


}


