package com.example.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.model.SignInInfo
import com.example.model.User
import com.example.retrofit.RetrofitClient
import com.example.retrofit.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import com.example.model.ResetPasswordResponse
import com.example.viewmodel.UserViewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import java.util.concurrent.Flow

class UserRepository(private val userService: UserService = RetrofitClient.instance) {
    fun createUser(user: User): MutableLiveData<User> {
        val userLiveData = MutableLiveData<User>()
        userService.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Sign up was successful
                    val createdUser = response.body()
                    userLiveData.postValue(createdUser) // Use postValue for UI thread
                } else {
                    // Sign up failed
                    val error = response.errorBody()?.string()
                    Log.e("UserRepository", "User creation failed: $error")
                    userLiveData.postValue(null) // Use postValue for UI thread
                }
            }


            override fun onFailure(call: Call<User>, t: Throwable) {
                // Sign up failed
                Log.e("UserRepository", "User creation failed", t)
                userLiveData.postValue(null) // Use postValue for UI thread
            }


        })
        return userLiveData
    }

    fun signInUser(email: String, password: String): MutableLiveData<User> {
        val userLiveData = MutableLiveData<User>()
        val signInInfo = SignInInfo(email, password)

        userService.signInUser(signInInfo).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val signedInUser = response.body()
                    userLiveData.value = signedInUser
                    val user = response.body()
                    Log.d("SIGN_IN_BUTTON", "Sign-in successful. User: $user")
                } else {
                    // Handle sign-in failure
                    val errorBody = response.errorBody()?.string()
                    Log.e("SIGN_IN_BUTTON", "Sign in failed. Error: $errorBody")
                    // Log or handle the error
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Sign-in failed
                Log.e("SIGN_IN_BUTTON", "Sign-in failed", t)
                // Handle the failure
            }

        })

        return userLiveData
    }

    fun forgotPassword(email: String): MutableLiveData<String> {
        val forgotPasswordResponse = MutableLiveData<String>()

        // Create a JSON object
        val jsonObject = JSONObject()
        jsonObject.put("email", email)

        // Convert the JSON object to a request body
        val requestBody = jsonObject.toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        userService.forgotPassword(requestBody)?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful) {
                    // Reset token sent successfully
                    forgotPasswordResponse.value = "Reset token sent to your email."
                } else {
                    // Failed to send reset token
                    val errorBody = response.errorBody()?.string()
                    Log.e("UserRepository", "Failed to send reset token. Error: $errorBody")
                    forgotPasswordResponse.value = "Failed to send reset token."
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                // An error occurred
                Log.e("UserRepository", "An error occurred.", t)
                forgotPasswordResponse.value = "An error occurred."
            }
        })

        return forgotPasswordResponse
    }

    fun resetPassword(email: String, otpCode: String, newPassword: String): Call<ResetPasswordResponse> {
        return userService.resetPassword(email, otpCode, newPassword)
    }
    fun updateUserProfile(id: String, user: User, callback: (Boolean) -> Unit) {
        userService.updateUser(id, user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback(false)
            }
        })
    }
    fun forgotPasswordSMS(email: String): LiveData<String> {
        val result = MutableLiveData<String>()

        val requestBody = JsonObject().apply {
            addProperty("email", email)
        }

        val call = userService.forgotPasswordSMS(RequestBody.create("application/json".toMediaTypeOrNull(), requestBody.toString()))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    result.value = "Reset OTP sent to your phone."
                } else {
                    result.value = "Error sending OTP via SMS."
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                result.value = "Network error. Please try again."
            }
        })

        return result
    }


}




