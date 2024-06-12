
package com.chihebsapplication.app.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R
import com.chihebsapplication.app.models.Distribution
import com.chihebsapplication.app.modules.fichesreparation.data.model.ListfichevoitureRowModel
import com.chihebsapplication.app.modules.fichesreparation.ui.DisViewHolder
import kotlin.Int

class ListfichevoitureAdapter(var context: Context,val list: MutableList<Distribution>) :
    RecyclerView.Adapter<DisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_listfichevoiture, parent, false)

        return DisViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisViewHolder, position: Int) {
        val item = list[position]

        holder.typeRepair.text = item.typeRepair
        holder.pieces.text = item.pieces
        holder.cars.text = item.cars
        holder.description.text = item.description
        holder.technicien.text = item.technicien
        holder.startDate.text = item.startDate.toString()
        holder.endDate.text = item.endDate.toString()
        holder.statusCar.text = item.statusCar
    }

    override fun getItemCount() = list.size
}

