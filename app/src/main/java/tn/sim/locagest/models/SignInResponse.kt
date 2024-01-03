package tn.sim.locagest.models

data class SignInResponse(
    val user: User,
    val token: String
)
