package com.example.model


// User data class

data class User(
    val id: String?,
    val username: String,
    val email: String,
    val password: String,
    val firstName: String?,
    val lastName: String?,
    val creditCardNumber: String?,
    val rate: String = "GOOD",
    val specialization: String?,
    val experience: Int?,
    val roles: String = "client"
)
