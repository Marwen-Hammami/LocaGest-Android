package tn.sim.locagest.api.retrofit


import tn.sim.locagest.models.User


import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.sim.locagest.models.ResetPasswordResponse
import tn.sim.locagest.models.SignInResult


interface UserService {
    @POST("User/signup")
    fun createUser(@Body user: User): Call<User>

    /* @GET("user/")
     fun getAllUsers(): Call<List<User>>*/

    @PUT("User/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User): Call<User>

    @POST("/User/sms")
    fun forgotPasswordSMS(@Body requestBody: RequestBody): Call<Void>

    /* @DELETE("user/{id}")
      fun deleteUser(@Path("id") id: String): Call<User>
  */

    @FormUrlEncoded
    @POST("User/signing")
    fun signInUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignInResult>

    @POST("User/password")
    fun forgotPassword(@Body email: RequestBody): Call<Void?>?

    @FormUrlEncoded
    @POST("User/reset-password") // Replace with your actual API endpoint
    fun resetPassword(
        @Field("email") email: String,
        @Field("otpCode") otpCode: String,
        @Field("newPassword") newPassword: String
    ): Call<ResetPasswordResponse>
}