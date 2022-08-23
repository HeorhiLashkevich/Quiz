package com.example.quiz.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.di.NetworkController
import com.example.quiz.network.Question
import com.example.quiz.network.Questions


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuizViewModel(


) : ViewModel(


) {

    val questions = mutableListOf<Question>()
    var questionNumber = 0
    var questionNumberLiveData = MutableLiveData<Int>()
    val currentQuestion = MutableLiveData<Question>()


    fun getQuestions(string: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NetworkController.getQuizApi().getQuestions(
                categories = string
            )
            if (response.isSuccessful) {
                response.body()?.let { questions.addAll(it) }
                val index = questionNumber
                val elem = questions[index]
                currentQuestion.postValue(elem)

            }

        }
    }

    fun loadNextQuestion() {
        questionNumber++
        currentQuestion.postValue(questions[questionNumber])
        questionNumberLiveData.postValue(questionNumber)
    }

}

