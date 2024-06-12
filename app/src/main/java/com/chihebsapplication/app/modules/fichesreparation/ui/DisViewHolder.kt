package com.chihebsapplication.app.modules.fichesreparation.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R

class DisViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {


    val typeRepair: TextView
    val pieces: TextView
    val cars: TextView
    val description: TextView
    val technicien: TextView
    val startDate: TextView
    val endDate: TextView
    val statusCar: TextView
    val imageView: ImageView



    //val type: TextView = itemView.findViewById<TextView>(R.id.txtT)

    init {

        typeRepair  = itemView.findViewById<TextView>(R.id.txtname)
        pieces  = itemView.findViewById<TextView>(R.id.txtname)
        cars  = itemView.findViewById<TextView>(R.id.txtprix)
        description  = itemView.findViewById<TextView>(R.id.txtDesc)
        technicien  = itemView.findViewById<TextView>(R.id.txtMarque)
        startDate  = itemView.findViewById<TextView>(R.id.txtprix)
        endDate  = itemView.findViewById<TextView>(R.id.txtprix)
        statusCar  = itemView.findViewById<TextView>(R.id.txtstock)
        imageView = itemView.findViewById<ImageView>(R.id.imagetool)

        // Set static image resource
        imageView.setImageResource(R.drawable.img_oval)
    }

}