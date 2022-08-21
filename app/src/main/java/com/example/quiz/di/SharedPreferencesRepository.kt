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


    fun addTheQuestionsToFavorites(list: Questions, userEmail: String) {
        val jsonStudent = Gson().toJson(list)
        sharedPref.edit {
            putString(userEmail, jsonStudent)
        }
    }

    fun getFavoritesQuestions(userEmail: String): Questions? {
        val jsonQuestions = sharedPref.getString(userEmail, "")
        if (jsonQuestions.isNullOrBlank()) {
            return null
        }
        return Gson().fromJson(jsonQuestions, Questions::class.java)
    }

    fun clear() {
        sharedPref.edit {
            this.clear()
        }
    }
}

