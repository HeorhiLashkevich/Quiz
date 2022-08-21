//package com.example.quiz
//
//
//import com.example.quiz.network.QuizApi
//import com.example.quiz.adapter.QuizViewModel
//import javax.inject.Inject
//
//class QuestionsViewModelProvider @Inject constructor(
//    private val api: QuizApi
//) : BaseViewModelFactory<QuizViewModel>(QuizViewModel::class.java) {
//
//    override fun createViewModel(): QuizViewModel {
//        return QuizViewModel(api)
//    }
//}