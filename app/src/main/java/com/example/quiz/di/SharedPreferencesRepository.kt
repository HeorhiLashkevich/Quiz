package com.example.quiz.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.quiz.network.Question
import com.example.quiz.network.Questions
import com.google.gson.Gson

private const val SHARED_PREF_FAIL = "prefName"


class SharedPreferencesRepository(
    context: Context,
    userEmail: String
) {


    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_FAIL, MODE_PRIVATE)


    fun addTheQuestionsToFavorites(list: List<Question>, userEmail: String) {
        val jsonStudent = Gson().toJson(list)
        sharedPref.edit {
            putString(userEmail, jsonStudent)
        }
    }

    fun getFavoritesQuestions(userEmail: String): List<Question> {
        val jsonQuestions = sharedPref.getString(userEmail, "")
        if (jsonQuestions.isNullOrBlank()) {
            return arrayListOf()
        }
        return Gson().fromJson(jsonQuestions, Array<Question>::class.java).toList()
    }

    fun clear() {
        sharedPref.edit {
            this.clear()
        }
    }
}

