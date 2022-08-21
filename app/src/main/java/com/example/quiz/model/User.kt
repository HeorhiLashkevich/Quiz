package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var email: String,
    @ColumnInfo(name = "firstName")
    var firstName: String = "",
    @ColumnInfo(name = "lastName")
    var lastName: String,
    @ColumnInfo(name = "password")
    var password: String
)


