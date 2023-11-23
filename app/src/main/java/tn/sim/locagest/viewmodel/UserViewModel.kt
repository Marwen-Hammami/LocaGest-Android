package tn.sim.locagest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tn.sim.locagest.models.User
import tn.sim.locagest.repository.UserRepository
import tn.sim.locagest.api.retrofit.RetrofitClient
import tn.sim.locagest.api.retrofit.UserService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.models.ResetPasswordResponse
import tn.sim.locagest.models.SignInResult


class UserViewModel : ViewModel() {
    private val userService: UserService = RetrofitClient.instance
    private val userRepository = UserRepository()

    // LiveData for created user
    private val _createdUser = MutableLiveData<User>()
    val createdUser: LiveData<User> get() = _createdUser

    private val _signInResult = MutableLiveData<SignInResult>()
    val signInResult: LiveData<SignInResult>
        get() = _signInResult

    fun signInUser(email: String, password: String) {
        userRepository.signInUser(email, password, object : UserRepository.SignInCallback {
            override fun onSuccess(user: User?, token: String?, success: Boolean) {
                val result = SignInResult(user = user, token = token, success = success)
                _signInResult.postValue(result)
            }

            override fun onError(error: String?) {
                val result = SignInResult(error = error)
                _signInResult.postValue(result)
            }
        })
    }



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




