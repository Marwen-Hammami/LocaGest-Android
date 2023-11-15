package tn.sim.locagest.Model

import java.util.Date

enum class StatutRes {
    Reservee,
    Payee,
    Achevee
}

data class Reservation(
    val IdRes: Int,
    val DateD: Date,
    val DateF: Date,
    val Statut: StatutRes,
    val Total: Float
)
