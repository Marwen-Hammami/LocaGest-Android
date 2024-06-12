
package com.chihebsapplication.app.modules.fichesreparation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.base.BaseActivity
import com.chihebsapplication.app.databinding.ActivityFichesReparationBinding
import com.chihebsapplication.app.models.Distribution
import com.chihebsapplication.app.models.Tool
import com.chihebsapplication.app.modules.ListfichevoitureAdapter
import com.chihebsapplication.app.modules.ToolsAdapter
import com.chihebsapplication.app.modules.ajoutdesoutils.ui.AjoutDesOutilsActivity
import com.chihebsapplication.app.modules.ajoutdesoutils.ui.ModifierDesOutils
import com.chihebsapplication.app.modules.fichesreparation.`data`.model.ListfichevoitureRowModel
import com.chihebsapplication.app.modules.fichesreparation.`data`.viewmodel.FichesReparationVM
import com.chihebsapplication.app.modules.swapGestor
import com.chihebsapplication.app.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FichesReparationActivity : AppCompatActivity() {

  lateinit var recylcerToy: RecyclerView
  lateinit var recylcerProfileItemAdapter: ListfichevoitureAdapter

  var btnAdd: Button? = null


  var list: MutableList<Distribution> = ArrayList()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fiches_reparation)
    btnAdd = findViewById(R.id.btnAjouter)

    Log.d("Tesy", "OnCreate")
    recylcerToy = findViewById(R.id.recyclerListfichevoiture)
    getDis()

    /*val toolList = mutableListOf(
        Tool("test", "tet", "test", "test", 50, 50, "test"),
        Tool("test", "tet", "test", "test", 50, 50, "test")
    )*/

    recylcerProfileItemAdapter = ListfichevoitureAdapter(this,list)


//    val swipeGesture = object : swapGestor(this@FichesReparationActivity) {
//      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        val swipedTask = list[viewHolder.absoluteAdapterPosition]
//
//        when (direction) {
//          ItemTouchHelper.LEFT -> {
//            deleteItem(swipedTask._id!!)
//            Log.d("swap", "Left")
//          }
//          ItemTouchHelper.RIGHT -> {
//            updateItem(swipedTask)
//            Log.d("swap", "Right")
//          }
//        }
//      }
//    }
//    val itemTouchHelper = ItemTouchHelper(swipeGesture)
//    itemTouchHelper.attachToRecyclerView(recylcerToy)

    recylcerToy.adapter = recylcerProfileItemAdapter
    recylcerToy.layoutManager =
      LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//    btnAdd!!.setOnClickListener {
//
//      val intent = Intent(this@DisplayTools, AjoutDesOutilsActivity::class.java)
//      startActivity(intent)
//    }

  }


  private fun getDis() {
    Log.d("getDis", "bn1")

    ApiService.DIST_SERVICE.getDis().enqueue(object : Callback<MutableList<Distribution>> {

      override fun onResponse(
        call: Call<MutableList<Distribution>>,
        response: Response<MutableList<Distribution>>
      ) {
        Log.d("getTools", "bn2")
        val toy = response.body()
        if (toy != null) {
          Log.d("getTools", "bn3")
          list = toy
          recylcerProfileItemAdapter = ListfichevoitureAdapter(this@FichesReparationActivity, list)
          recylcerToy.adapter = recylcerProfileItemAdapter
        }

      }

      override fun onFailure(call: Call<MutableList<Distribution>>, t: Throwable) {
        Log.d("getTools", "bn4")
      }
    })
  }


  private fun deleteItem(itemId: String) {


    ApiService.DIST_SERVICE.deleteDis(itemId).enqueue(object : Callback<Void> {
      override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if (response.isSuccessful) {
          // Successfully deleted the item
          Log.d("Delete", "Item deleted successfully")
          Toast.makeText(
            this@FichesReparationActivity,
            "Event Deleted Successfully",
            Toast.LENGTH_SHORT
          ).show()
          getDis()
        } else {
          // Handle error response
          Log.e("Delete", "Error deleting item. Status code: ${response.code()}")
          // Add any error handling logic
        }
      }

      override fun onFailure(call: Call<Void>, t: Throwable) {
        // Handle network failure
        Log.e("Delete", "Network failure while deleting item. Error: ${t.message}")
        // Add any error handling logic
      }
    })
  }


  private fun updateItem(item: Tool) {


    val intent = Intent(this@FichesReparationActivity, ModifierDesOutils::class.java)
    intent.putExtra("Tool", item)
    startActivity(intent)
  }

}

