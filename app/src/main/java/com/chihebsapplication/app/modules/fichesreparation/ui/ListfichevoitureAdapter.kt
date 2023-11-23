/*
package com.chihebsapplication.app.modules.fichesreparation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R
import com.chihebsapplication.app.databinding.RowListfichevoitureBinding
import com.chihebsapplication.app.modules.fichesreparation.`data`.model.ListfichevoitureRowModel
import kotlin.Int
import kotlin.collections.List

class ListfichevoitureAdapter(
  var list: List<ListfichevoitureRowModel>
) : RecyclerView.Adapter<ListfichevoitureAdapter.RowListfichevoitureVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListfichevoitureVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listfichevoiture,parent,false)
    return RowListfichevoitureVH(view)
  }

  override fun onBindViewHolder(holder: RowListfichevoitureVH, position: Int) {
    val listfichevoitureRowModel = ListfichevoitureRowModel()
    // TODO uncomment following line after integration with data source
    // val listfichevoitureRowModel = list[position]
    holder.binding.listfichevoitureRowModel = listfichevoitureRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListfichevoitureRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: ListfichevoitureRowModel
    ) {
    }
  }

  inner class RowListfichevoitureVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListfichevoitureBinding = RowListfichevoitureBinding.bind(itemView)
    init {
      binding.btnLivrerVoiture.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ListfichevoitureRowModel())
      }
    }
  }
}
*/
