package com.chihebsapplication.app.service;


import com.chihebsapplication.app.models.Tool
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.*

interface ToolService {

    data class ToolResponse(
        @SerializedName("tool")
        val tool: Tool
    )


    data class ToolBody(val name: String, val marque: String, val type: String,val stock : Number,val price : Number, val image: MultipartBody.Part )




 /*   @Multipart
    @POST("/tools/addTool")
    fun register(@Body toolBody: ToolBody,@Part image: MultipartBody.Part): Call<ToolResponse>
*/
    @Multipart
    @POST("/tools/addTool/{name}/{stock}/{price}/{marque}/{type}")
    fun register(@Part image: MultipartBody.Part,@Path("name")name : String,
                 @Path("stock")stock : Number,@Path("price")price : Number,
                @Path("marque")marque : String,@Path("type")type : String,

                ): Call<ToolResponse>



    @GET("/tools/getAllTools")
    fun getTools(): Call<MutableList<Tool>>

    @DELETE("/tools/deleteTool/{id}")
    fun deleteTool( @Path("id")id : String): Call<Void>

    @Multipart
    @PUT("/tools/updateTool/{id}/{name}/{stock}/{price}/{marque}/{type}")
    fun updatePost( @Part image: MultipartBody.Part,@Path("id")id : String,@Path("name")name : String,
                    @Path("stock")stock : Number,@Path("price")price : Number,
                    @Path("marque")marque : String,@Path("type")type : String): Call<ToolResponse>




    @PUT("/tools/updatetoolno/{id}/{name}/{stock}/{price}/{marque}/{type}")
    fun updatePostWithoutImage(@Path("id")id : String,@Path("name")name : String,
                               @Path("stock")stock : Number,@Path("price")price : Number,
                               @Path("marque")marque : String,@Path("type")type : String): Call<ToolResponse>



    // @Multipart
    //  @POST("/Client/update/{ID}/{userName}/{email}/{phoneNumber}")
    //  fun updateClient(@Part image: MultipartBody.Part,@Path("ID")ID : String,@Path("userName")userName : String,
    //    @Path("email")email : String,@Path("phoneNumber")phoneNumber : String): Call<Client>






}
