package tn.sim.locagest.models

import java.util.Date

data class Message(
    val conversationId: String,
    val sender: String,
    val text: String?,
    val file: List<String>?,
    val createdAt: Date?,
    val updatedAt: Date?
) {
}