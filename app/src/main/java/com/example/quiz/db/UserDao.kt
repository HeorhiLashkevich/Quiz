package com.example.quiz.ui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.model.User


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun findUserForLogin(email: String, password: String): User

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun currentUser(email: String): User

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>


}