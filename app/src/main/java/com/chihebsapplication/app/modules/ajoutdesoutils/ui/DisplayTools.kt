package com.chihebsapplication.app.modules.ajoutdesoutils.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chihebsapplication.app.R
import com.chihebsapplication.app.models.Tool
import com.chihebsapplication.app.modules.ToolsAdapter
import com.chihebsapplication.app.modules.swapGestor
import com.chihebsapplication.app.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayTools : AppCompatActivity() {

    lateinit var recylcerToy: RecyclerView
    lateinit var recylcerProfileItemAdapter: ToolsAdapter

    var btnAdd: Button? = null


    var toolList: MutableList<Tool> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_tools)
        btnAdd = findViewById(R.id.btnAjouter)

        Log.d("Tesy", "OnCreate")
        recylcerToy = findViewById(R.id.recyclerListTools)
 getTools()

        /*val toolList = mutableListOf(
            Tool("test", "tet", "test", "test", 50, 50, "test"),
            Tool("test", "tet", "test", "test", 50, 50, "test")
        )*/

        recylcerProfileItemAdapter = ToolsAdapter(this,toolList)


        val swipeGesture = object : swapGestor(this@DisplayTools) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedTask = toolList[viewHolder.absoluteAdapterPosition]

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        deleteItem(swipedTask._id!!)
                        Log.d("swap", "Left")
                    }
                    ItemTouchHelper.RIGHT -> {
                        updateItem(swipedTask)
                        Log.d("swap", "Right")
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(recylcerToy)

        recylcerToy.adapter = recylcerProfileItemAdapter
        recylcerToy.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        btnAdd!!.setOnClickListener {

            val intent = Intent(this@DisplayTools, AjoutDesOutilsActivity::class.java)
            startActivity(intent)
        }

        }


    private fun getTools() {
        Log.d("getTools", "bn1")

        ApiService.TOOL_SERVICE.getTools().enqueue(object : Callback<MutableList<Tool>> {

            override fun onResponse(
                call: Call<MutableList<Tool>>,
                response: Response<MutableList<Tool>>
            ) {
                Log.d("getTools", "bn2")
                val toy = response.body()
                if (toy != null) {
                    Log.d("getTools", "bn3")
                    toolList = toy
                    recylcerProfileItemAdapter = ToolsAdapter(this@DisplayTools, toolList)
                    recylcerToy.adapter = recylcerProfileItemAdapter
                }

            }

            override fun onFailure(call: Call<MutableList<Tool>>, t: Throwable) {
                Log.d("getTools", "bn4")
            }
        })
    }


    private fun deleteItem(itemId: String) {


        ApiService.TOOL_SERVICE.deleteTool(itemId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Successfully deleted the item
                    Log.d("Delete", "Item deleted successfully")
                    Toast.makeText(
                        this@DisplayTools,
                        "Event Deleted Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    getTools()
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


            val intent = Intent(this@DisplayTools, ModifierDesOutils::class.java)
            intent.putExtra("Tool", item)
            startActivity(intent)
        }

}
