package com.chihebsapplication.app.modules

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R

class ToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val ToolPic: ImageView
    val Name: TextView
    val marque: TextView
    val price: TextView
    val stock: TextView


    //val type: TextView = itemView.findViewById<TextView>(R.id.txtT)

    init {
        ToolPic = itemView.findViewById<ImageView>(R.id.imagetool)
         Name  = itemView.findViewById<TextView>(R.id.txtname)
         marque  = itemView.findViewById<TextView>(R.id.txtMarque)
         price  = itemView.findViewById<TextView>(R.id.txtprix)
         stock  = itemView.findViewById<TextView>(R.id.txtstock)
    }

}