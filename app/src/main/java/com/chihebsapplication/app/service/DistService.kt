package com.chihebsapplication.app.service

import com.chihebsapplication.app.models.Distribution
import com.chihebsapplication.app.models.Tool
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.Date

interface DistService {
    data class DisResponse(
        @SerializedName("tool")
        val Dis: Distribution
    )


    data class ToolBody(val typeRepair: String,
                        val pieces: String,
                        val cars: String,
                        val description : String,
                        val technicien : String,
                        val startDate: Date,
                        val endDate: Date,
                        val statusCar: String,


                        )
    @Multipart
    @POST("/distribution/addDistribution/{typeRepair}/{pieces}/{cars}/{description}/{technicien}/{startDate}/{endDate}/{statusCar}")
    fun register( @Path("typeRepair")typeRepair : String,@Path("pieces")pieces : String,
                 @Path("cars")cars : String, @Path("description")description : String,
                 @Path("technicien")technicien : String, @Path("startDate")startDate : Date,
                  @Path("endDate")endDate : Date,@Path("statusCar")statusCar : String,


                 ): Call<DisResponse>



    @GET("/distribution/getAllDistributions")
    fun getDis(): Call<MutableList<Distribution>>

    @DELETE("/distribution/deleteDistribution/{id}")
    fun deleteDis( @Path("id")id : String): Call<Void>

//    @Multipart
//    @PUT("/distribution/updateTool/{id}/{name}/{stock}/{price}/{marque}/{type}")
//    fun updatePost(@Part image: MultipartBody.Part, @Path("id")id : String, @Path("name")name : String,
//                   @Path("stock")stock : Number, @Path("price")price : Number,
//                   @Path("marque")marque : String, @Path("type")type : String): Call<ToolResponse>



//
//    @PUT("/tools/updatetoolno/{id}/{name}/{stock}/{price}/{marque}/{type}")
//    fun updatePostWithoutImage(@Path("id")id : String, @Path("name")name : String,
//                               @Path("stock")stock : Number, @Path("price")price : Number,
//                               @Path("marque")marque : String, @Path("type")type : String): Call<ToolResponse>



    // @Multipart
    //  @POST("/Client/update/{ID}/{userName}/{email}/{phoneNumber}")
    //  fun updateClient(@Part image: MultipartBody.Part,@Path("ID")ID : String,@Path("userName")userName : String,
    //    @Path("email")email : String,@Path("phoneNumber")phoneNumber : String): Call<Client>






}
