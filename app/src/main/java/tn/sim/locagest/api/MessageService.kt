package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.sim.locagest.models.Message

interface MessageService {
    @POST("messages/")
    fun createMessage(@Body params: Message): Call<Message>

    @GET("messages/{id}")
    fun getMessagesOfAConversation(@Path("id") id: String): Call<List<Message>>

    @DELETE("messages/{id}")
    fun deleteMessage(@Path("id") id: String): Call<Message>
}