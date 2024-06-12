package com.chihebsapplication.app.models
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Distribution (

    @SerializedName("_id")
    val _id: String,
    @SerializedName("typeRepair")
    val typeRepair: String,
    @SerializedName("pieces")
    val pieces: String,
    @SerializedName("cars")
    val cars: String,
    @SerializedName("description")
    val description: String,
@SerializedName("technicien")
val technicien: String,
@SerializedName("startDate")
val startDate: Date,
@SerializedName("endDate")
val endDate: Date,
@SerializedName("statusCar")
val statusCar: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        Date(parcel.readLong()),  // Reading Date as Long
        Date(parcel.readLong()),  // Reading Date as Long
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(typeRepair)
        parcel.writeString(pieces)
        parcel.writeString(cars)
        parcel.writeString(description)
        parcel.writeString(technicien)
        parcel.writeLong(startDate.time) // Writing Date as Long
        parcel.writeLong(endDate.time)   // Writing Date as Long
        parcel.writeString(statusCar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tool> {
        override fun createFromParcel(parcel: Parcel): Tool {
            return Tool(parcel)
        }

        override fun newArray(size: Int): Array<Tool?> {
            return arrayOfNulls(size)
        }
    }
}
