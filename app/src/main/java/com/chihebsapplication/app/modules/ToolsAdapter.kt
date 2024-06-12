package com.chihebsapplication.app.modules

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chihebsapplication.app.R
import com.chihebsapplication.app.models.Tool
import com.chihebsapplication.app.service.ApiService.BASE_URL



class ToolsAdapter(var context: Context, val ToolList: MutableList<Tool>) :
    RecyclerView.Adapter<ToolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_listfichevoiture, parent, false)

        return ToolViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {

        val tool = ToolList[position]

        Glide.with(holder.ToolPic).load(BASE_URL+"uploads/"+tool.image).placeholder(R.drawable.imageload)
            .override(25, 25).error(R.drawable.notfoundd).into(holder.ToolPic)

        holder.Name.text = tool.name
        holder.marque.text = tool.marque
        holder.price.text = tool.price.toString()
        holder.stock.text = tool.stock.toString()



     /*   holder.itemView.setOnClickListener {
            Toast.makeText(context, toy.Name, Toast.LENGTH_LONG).show()
            var intent = Intent(context, ToyDetails::class.java)

            intent.putExtra("image", toy.image)
            intent.putExtra("Name", toy.Name)
            intent.putExtra("description", toy.description)
            intent.putExtra("Price", toy.Price)
            intent.putExtra("_id", toy._id)
            intent.putExtra("owner", toy.OwnerId)

            context.startActivity(intent)

        }*/

    }

    override fun getItemCount() = ToolList.size

}