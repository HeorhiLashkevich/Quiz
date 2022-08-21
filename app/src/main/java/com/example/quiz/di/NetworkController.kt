package com.example.quiz.di

import android.util.Log
import com.example.quiz.network.QuizApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val BASE_URL = "https://the-trivia-api.com"


object NetworkController {

    fun getQuizApi(): QuizApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(QuizApi::class.java)
    }


    val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message -> Log.d("OK HTTP", message) }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .connectTimeout(1000L, TimeUnit.SECONDS)
            .readTimeout(1000L, TimeUnit.SECONDS)
            .writeTimeout(1000L, TimeUnit.SECONDS)
            .build()
}