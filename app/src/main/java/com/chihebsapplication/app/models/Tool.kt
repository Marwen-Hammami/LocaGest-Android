package com.chihebsapplication.app.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Tool(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("marque")
    val marque: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(name)
        parcel.writeString(marque)
        parcel.writeString(type)
        parcel.writeInt(stock)
        parcel.writeInt(price)
        parcel.writeString(image)
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
