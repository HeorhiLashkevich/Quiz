package com.example.quiz.adapter


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.User
import com.example.quiz.repository.UserRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel() : ViewModel() {

    private val repository = UserRepository()

    val currentUserEmail = MutableLiveData<String>()
    val currentUserFirstAndLastName = MutableLiveData<User>()


    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsers()
        }
    }

    fun insertNewUser(email: String, firstName: String, lastName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                if (firstName.isNotBlank() && password.isNotBlank() && lastName.isNotBlank() && email.isNotBlank())
                    repository.insertUser(User(email, firstName, lastName, password))

            } catch (e: Exception) {

            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    suspend fun findUser(login: String, password: String): User? {
        return repository.findUser(login, password)
    }

    fun currentUser(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentUserFirstAndLastName.postValue(repository.findUser(login, password))
        }
    }

    fun currentEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentUserEmail.postValue(email)
        }
    }


}