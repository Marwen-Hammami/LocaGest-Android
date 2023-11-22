package tn.sim.locagest.models
import java.io.Serializable
import java.util.Date


data class  HistoriqueEntretien (
    val immatriculation: String?,
    val date_entretien: Date?,
    val description: String?,
    val cout_reparation: Number?,
    val vehicule_type: String?
    ):Serializable
