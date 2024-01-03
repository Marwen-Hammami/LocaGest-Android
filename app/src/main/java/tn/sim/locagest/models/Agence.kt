package tn.sim.locagest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Agence(
    @SerializedName("_id") val id: String?,
    @SerializedName("AgenceName") var name: String,
    @SerializedName("Adresse") var address: String,
    @SerializedName("IdHead") val head: User?,
    @SerializedName("longitude") var longitude: Double?,
    @SerializedName("latitude") var latitude: Double?,
) : Serializable
