package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import tn.sim.locagest.models.Conversation

interface ConversationService {
    @POST("conversations/")
    fun createConversation(@Body params: Conversation): Call<Conversation>

    @GET("conversations/{id}")
    fun getConversationsOfAUser(@Path("id") id: String): Call<List<Conversation>>

    @PATCH("conversations/{id}")
    fun updateConversation(@Path("id") id: String, @Body params: Conversation): Call<Conversation>
}