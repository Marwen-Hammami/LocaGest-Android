package com.chihebsapplication.app.modules.ajoutdesoutils.ui
import android.Manifest
import android.content.ActivityNotFoundException

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.chihebsapplication.app.R
import com.chihebsapplication.app.models.Tool
import com.chihebsapplication.app.service.ApiService
import com.google.android.material.textfield.TextInputEditText
import android.net.Uri
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chihebsapplication.app.modules.ajoutdesoutils.data.model.fileutil
import com.chihebsapplication.app.service.ToolService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModifierDesOutils : AppCompatActivity() {

    private lateinit var txtName: TextInputEditText
    private lateinit var txtStock: TextInputEditText

    private lateinit var txtMarque: TextInputEditText

    private lateinit var txtPrice: TextInputEditText
    private lateinit var txtType: TextInputEditText
    private lateinit var image: ImageView

    private lateinit var id: String
    lateinit var uri: Uri
    var f: fileutil = fileutil()
    var btnUpdate: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_des_outils)

        btnUpdate = findViewById(R.id.btnModifier)

        txtName = findViewById(R.id.txtNameexampleco)
        txtStock = findViewById(R.id.txtStockexampleco)
        txtMarque = findViewById(R.id.txtMarqueexampleco)
        txtPrice = findViewById(R.id.txtPriceexampleco)
        txtType = findViewById(R.id.txtTypeexampleco)

        image = findViewById(R.id.imagePageOne)

        image!!.setOnClickListener {
            val fintent = Intent(Intent.ACTION_GET_CONTENT)
            fintent.type = "image/jpeg"
            try {
                startActivityForResult(fintent, 100)
            } catch (e: ActivityNotFoundException) {
            }
        }

        val tool = intent.getParcelableExtra<Tool>("Tool")

        if (tool != null) {
            id = tool._id!!
            txtName.setText(tool.name)
            txtStock.setText(tool.stock.toString())
            txtMarque.setText(tool.marque)
            txtPrice.setText(tool.price.toString())
            txtType.setText(tool.type)


            uri = Uri.parse(tool.image)

            Glide.with(image).load(ApiService.BASE_URL + "uploads/" + tool.image)
                .placeholder(R.drawable.imageload)
                .override(100, 100).error(R.drawable.notfoundd).into(image)
        }
        btnUpdate!!.setOnClickListener {

            checkAndRequestPermission()

            if(uri != Uri.parse(tool!!.image) ){

                val file = File(f.getPath(uri, this))
                val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                var imagee = MultipartBody.Part.createFormData(
                    "image",
                    file.getName(), reqFile
                )


                ApiService.TOOL_SERVICE.updatePost(
                    imagee,
                    id,
                    txtName!!.text.toString() ,
                    txtStock!!.text.toString().toInt(),
                    txtPrice!!.text.toString().toInt(),
                    txtMarque!!.text.toString(),
                    txtType!!.text.toString(),

                ).enqueue(
                    object : Callback<ToolService.ToolResponse> {
                        override fun onResponse(
                            call: Call<ToolService.ToolResponse>,
                            response: Response<ToolService.ToolResponse>
                        ) {
                            if (response.code() == 200) {
                                Toast.makeText(this@ModifierDesOutils, "Tool  updated!!!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@ModifierDesOutils, DisplayTools::class.java)
                                startActivity(intent)


                            } else {
                                Log.d("HTTP ERROR", "status code is " + response.code())
                            }
                        }

                        override fun onFailure(
                            call: Call<ToolService.ToolResponse>,
                            t: Throwable
                        ) {
                            Log.d("FAIL", "fail")
                        }
                    }
                )


            }else{

                ApiService.TOOL_SERVICE.updatePostWithoutImage(
                    id,
                    txtName!!.text.toString() ,
                    txtStock!!.text.toString().toInt(),
                    txtPrice!!.text.toString().toInt(),
                    txtMarque!!.text.toString(),
                    txtType!!.text.toString(),

                ).enqueue(
                    object : Callback<ToolService.ToolResponse> {
                        override fun onResponse(
                            call: Call<ToolService.ToolResponse>,
                            response: Response<ToolService.ToolResponse>
                        ) {
                            if (response.code() == 200) {
                                Toast.makeText(this@ModifierDesOutils, "Tool  updated!!!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@ModifierDesOutils, DisplayTools::class.java)
                                startActivity(intent)
                            } else {
                                Log.d("HTTP ERROR", "status code is " + response.code())
                            }
                        }

                        override fun onFailure(
                            call: Call<ToolService.ToolResponse>,
                            t: Throwable
                        ) {
                            Log.d("FAIL", "fail")
                        }
                    }
                )

            }
        }
    }


    private val apppermissions = arrayOf<String>(

        Manifest.permission.INTERNET,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        when (requestCode) {
            100 -> if (resultCode == RESULT_OK) {
                uri = data.data!!
                image!!.setImageURI(data.data)

            }
        }
    }

    private fun checkAndRequestPermission(): Boolean {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (perm in apppermissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(perm)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this, listPermissionsNeeded.toTypedArray(),
                200
            )
            return false
        }
        return true
    }
}


