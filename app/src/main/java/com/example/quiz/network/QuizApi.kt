package com.example.quiz.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("/api/questions")
    suspend fun getQuestions(
        @Query("limit") limit: Int = 10,
        @Query("categories") categories: String? = null,
        @Query("difficulty") difficulty: String? = null,
    ): Response<ArrayList<Question>>

    @GET("/api/categories")
    suspend fun getCategories(): Response<QuizCategories>

    @GET("/api/metadata")
    suspend fun getMetaData(): Response<QuizTags>




}