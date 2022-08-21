package com.example.quiz.network


data class Question(
    val category: String,
    val correctAnswer: String,
    val difficulty: String,
    val id: String,
    val incorrectAnswers: List<String>,
    val question: String,
    val regions: List<Any>,
    val tags: List<String>,
    val type: String
)

data class Questions(
    val listQuestions: List<Question>
    )


data class QuizCategories(
    val ArtsAndLiterature: List<String>,
    val FilmAndTV: List<String>,
    val FoodAndDrink: List<String>,
    val GeneralKnowledge: List<String>,
    val Geography: List<String>,
    val History: List<String>,
    val Music: List<String>,
    val Science: List<String>,
    val SocietyAndCulture: List<String>,
    val SportAndLeisure: List<String>
)


data class QuizTags(
    val byCategory: QuizCategories,
    val byDifficulty: ByDifficulty,
    val byState: ByState,
    val lastCreated: String,
    val lastReviewed: String
)

data class ByState(
    val approved: Int,
    val pending: Int,
    val rejected: Int
)

data class ByDifficulty(
    val easy: Int,
    val hard: Int,
    val medium: Int,
    val `null`: Int

)