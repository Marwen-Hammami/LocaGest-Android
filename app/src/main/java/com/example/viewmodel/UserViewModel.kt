package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.User
import com.example.repository.UserRepository

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val createdUser: MutableLiveData<User> = MutableLiveData()

    fun createUser(user: User) {
        userRepository.createUser(user).observeForever {
            createdUser.value = it
        }
    }

    val signedInUser: MutableLiveData<User> = MutableLiveData()

        fun signInUser(email: String, password: String) {
            signedInUser.value = userRepository.signInUser(email, password).value
        }
    }




