package tn.sim.locagest.models

data class PasswordResetDetails(
    val email: String,
    val otpCode: String,
    val newPassword: String
)