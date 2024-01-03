package tn.sim.locagest.models

import java.io.Serializable
import java.util.Date

data class Message(
    val _id: String?,
    val conversationId: String,
    val sender: String,
    val text: String?,
    val file: List<String>?,
    val createdAt: Date?,
    val updatedAt: Date?
): Serializable //Serializable so i can pass the object in an intent