package tn.sim.locagest.api.retrofit

//import tn.sim.locagest.models.ResetPasswordResponse
import tn.sim.locagest.models.SignInInfo
import tn.sim.locagest.models.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface UserService {
    @POST("User/signup")
    fun createUser(@Body user: User): Call<User>

   /* @GET("user/")
    fun getAllUsers(): Call<List<User>>*/

    @PUT("user/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User): Call<User>

    @POST("/User/sms")
    fun forgotPasswordSMS(@Body requestBody: RequestBody): Call<Void>

  /* @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Call<User>
*/
    @POST(" User/signing")
    fun signInUser(@Body loginInfo: SignInInfo): Call<User>

    @POST("User/password")
    fun forgotPassword(@Body email: RequestBody): Call<Void?>?

//    @FormUrlEncoded
//    @POST("User/reset-password") // Replace with your actual API endpoint
//    fun resetPassword(
//        @Field("email") email: String,
//        @Field("otpCode") otpCode: String,
//        @Field("newPassword") newPassword: String
//    ): Call<ResetPasswordResponse>
}
