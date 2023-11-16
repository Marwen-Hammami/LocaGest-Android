package tn.sim.locagest.models

import java.io.Serializable

data class Conversation(
    val _id: String?,
    val members: ArrayList<String>,
    val membersUsers: ArrayList<User>? = null,
    val isGroup: Boolean,
    val name: String?,
    val image: String?
    ): Serializable //Serializable so i can pass the object in an intent