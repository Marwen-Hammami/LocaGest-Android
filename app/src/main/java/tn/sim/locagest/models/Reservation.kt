package tn.sim.locagest.models

import java.util.Date

enum class StatutRes {
    Reservee,
    Payee,
    Achevee
}

data class Reservation(
    val _id: String?,
    val DateDebut: String,
    val DateFin: String,
    val HeureDebut: String,
    val HeureFin: String,
    val Total: Float
)