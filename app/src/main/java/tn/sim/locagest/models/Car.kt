package tn.sim.locagest.models

import java.io.Serializable

data class Car (
    val immatriculation: String?,
    val marque: String?,
    val modele: String?,
    val carburant: String?,
    val boite: String?,
    val disponibility: String?
    ):Serializable

