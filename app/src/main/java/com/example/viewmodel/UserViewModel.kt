package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.ResetPasswordResponse
import com.example.model.User
import com.example.repository.UserRepository
import com.example.retrofit.RetrofitClient
import com.example.retrofit.UserService
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Flow


class UserViewModel : ViewModel() {
    private val userService: UserService = RetrofitClient.instance
    private val userRepository = UserRepository()

    // LiveData for created user
    private val _createdUser = MutableLiveData<User>()
    val createdUser: LiveData<User> get() = _createdUser

    // LiveData for signed-in user
    private val _signedInUser = MutableLiveData<User>()
    val signedInUser: LiveData<User> get() = _signedInUser

    // LiveData for reset password message
    private val _resetPasswordMessage = MutableLiveData<String>()
    val resetPasswordMessage: LiveData<String> get() = _resetPasswordMessage

    // Function to create a new user
    fun createUser(user: User) {
        userRepository.createUser(user).observeForever {
            _createdUser.value = it
        }
    }

    // Function to sign in a user
    fun signInUser(email: String, password: String) {
        _signedInUser.value = userRepository.signInUser(email, password).value
    }

    // Function to send a reset password request
    fun forgotPassword(email: String): LiveData<String> {
        userRepository.forgotPassword(email).observeForever { message ->
            _resetPasswordMessage.value = message
        }
        return _resetPasswordMessage
    }


    private val _resetPasswordResponse = MutableLiveData<ResetPasswordResponse>()
    val resetPasswordResponse: LiveData<ResetPasswordResponse> get() = _resetPasswordResponse

    // Function to reset the password
    fun resetPassword(email: String, otpCode: String, newPassword: String) {
        userRepository.resetPassword(email, otpCode, newPassword)
            .enqueue(object : Callback<ResetPasswordResponse> {
                override fun onResponse(
                    call: Call<ResetPasswordResponse>,
                    response: Response<ResetPasswordResponse>
                ) {
                    if (response.isSuccessful) {
                        _resetPasswordResponse.value = response.body()
                    } else {
                        // You can handle API error here if needed
                        _resetPasswordResponse.value =
                            ResetPasswordResponse(error = "Password reset failed.")
                    }
                }

                override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                    // Handle network or unexpected errors
                    _resetPasswordResponse.value = ResetPasswordResponse(error = "An error occurred.")
                }
            })
    }
    private val _updateResult = MutableLiveData<Boolean>()
    val updateResult: LiveData<Boolean>
        get() = _updateResult

    // Add a user ID parameter to the function
    fun updateUserProfile(id: String, user: User) {
        userRepository.updateUserProfile(id, user) { isSuccess ->
            _updateResult.value = isSuccess
        }
    }
    fun forgotPasswordSMS(email: String): LiveData<String> {
        userRepository.forgotPasswordSMS(email).observeForever { message ->
            _resetPasswordMessage.value = message
        }
        return _resetPasswordMessage
    }




}




