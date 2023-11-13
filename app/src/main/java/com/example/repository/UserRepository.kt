package com.example.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.model.SignInInfo
import com.example.model.User
import com.example.retrofit.RetrofitClient
import com.example.retrofit.UserService
import retrofit2.*

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
                    Log.e("SIGN_IN_BUTTON", "Sign-in failed. Error: $errorBody")
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
}
