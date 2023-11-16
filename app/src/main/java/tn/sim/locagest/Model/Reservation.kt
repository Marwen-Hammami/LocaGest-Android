package tn.sim.locagest.Model

import java.util.Date

enum class StatutRes {
    Reservee,
    Payee,
    Achevee
}

data class Reservation(
    val _id: String?,
    val DateD: Date,
    val DateF: Date,
    val HeureD: String,
    val HeureF: String,
    val Statut: StatutRes,
    val Total: Float
)
