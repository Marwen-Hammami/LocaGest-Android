package tn.sim.locagest.models


data class SignInResult(
    val success: Boolean = false,
    val user: User? = null,
    val token: String? = null,
    val error: String? = null
)
