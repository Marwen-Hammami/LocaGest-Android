package com.example.retrofit

import com.example.model.SignInInfo
import com.example.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("User/signup")
    fun createUser(@Body user: User): Call<User>

   /* @GET("user/")
    fun getAllUsers(): Call<List<User>>

    @PUT("user/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User): Call<User>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Call<User>
*/
    @POST(" User/signing")
    fun signInUser(@Body loginInfo: SignInInfo): Call<User>
}
