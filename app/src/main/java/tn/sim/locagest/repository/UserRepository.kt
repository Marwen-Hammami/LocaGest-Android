package tn.sim.locagest.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tn.sim.locagest.models.User
import tn.sim.locagest.api.retrofit.RetrofitClient
import tn.sim.locagest.api.retrofit.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

import com.google.gson.JsonObject
import okhttp3.RequestBody
import tn.sim.locagest.models.ResetPasswordResponse
import tn.sim.locagest.models.SignInResult

class UserRepository(private val userService: UserService = RetrofitClient.instance) {
    fun createUser(user: User): MutableLiveData<User?> {
        val userLiveData = MutableLiveData<User?>()
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

    /*  fun signInUser(email: String, password: String, callback: SignInCallback-> Unit) {
          val signInInfo = SignInInfo(email, password)

          userService.signInUser(signInInfo).enqueue(object : Callback<User> {
              override fun onResponse(call: Call<User>, response: Response<User>) {
                  if (response.isSuccessful) {
                      val signedInUser = response.body()

                      // Print response headers to log
                      val headers = response.headers()
                      for (i in 0 until headers.size) {
                          Log.d("ResponseHeader", "${headers.name(i)}: ${headers.value(i)}")
                      }

                      val token = response.headers().get("Authorization") // Extract the token from the response headers

                      if (signedInUser != null) {
                          signedInUser.token = token  // Set the token received from the server
                          val signInResponse = SignInResponse(signedInUser, token!!)
                          callback(signInResponse)
                          Log.d("SIGN_IN_BUTTON", "Sign-in successful. User: $signedInUser")
                      } else {
                          // Handle case where User object is null
                          val errorBody = response.errorBody()?.string()
                          Log.e("SIGN_IN_BUTTON", "Sign in failed. Error: $errorBody")
                          // Log or handle the error
                          // callback(SignInResponse(User("", ""), ""))
                      }
                  } else {
                      // Handle sign-in failure
                      val errorBody = response.errorBody()?.string()
                      Log.e("SIGN_IN_BUTTON", "Sign in failed. Error: $errorBody")
                      // Log or handle the error
                      // callback(SignInResponse(User("", ""), ""))
                  }
              }

              override fun onFailure(call: Call<User>, t: Throwable) {
                  // Sign-in failed
                  Log.e("SIGN_IN_BUTTON", "Sign-in failed", t)
                  // Handle the failure
                 // callback(SignInResponse(User("", ""), ""))
              }
          })
      }

  */fun signInUser(email: String, password: String, callback: SignInCallback) {
        val call = userService.signInUser(email, password)
        call.enqueue(object : Callback<SignInResult> {
            override fun onResponse(call: Call<SignInResult>, response: Response<SignInResult>) {
                val userResponse = response.body()
                if (response.isSuccessful && userResponse != null) {
                    if (userResponse.success) {

                        callback.onSuccess(userResponse.user, userResponse.token, true)
                    } else {
                        callback.onError(userResponse.error)
                    }
                } else {
                    callback.onError("An error occurred")
                }
            }

            override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }



    interface SignInCallback {
        fun onSuccess(user: User?, token: String?, success: Boolean)
        fun onError(error: String?)
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




