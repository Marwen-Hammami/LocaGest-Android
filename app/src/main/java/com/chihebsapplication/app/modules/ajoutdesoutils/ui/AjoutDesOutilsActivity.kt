package com.chihebsapplication.app.modules.ajoutdesoutils.ui

import android.Manifest
import android.content.ActivityNotFoundException
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.base.BaseActivity
import com.chihebsapplication.app.databinding.ActivityAjoutDesOutilsBinding
import com.chihebsapplication.app.modules.ajoutdesoutils.`data`.viewmodel.AjoutDesOutilsVM
import com.chihebsapplication.app.service.ApiService
import com.chihebsapplication.app.service.ToolService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.String
import kotlin.Unit
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chihebsapplication.app.modules.ajoutdesoutils.data.model.fileutil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class AjoutDesOutilsActivity :
    BaseActivity<ActivityAjoutDesOutilsBinding>(R.layout.activity_ajout_des_outils) {
  private val viewModel: AjoutDesOutilsVM by viewModels<AjoutDesOutilsVM>()

 private var txtName : TextInputEditText? = null
  var txtStock: TextInputEditText? = null
  var txtMarque: TextInputEditText? = null
  var txtPrice: TextInputEditText? = null
    var txtType: TextInputEditText? = null
    var image: ImageView? = null

    lateinit var uri: Uri
    var f: fileutil = fileutil()

  var btnAdd: Button? = null
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.ajoutDesOutilsVM = viewModel
  }

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      txtName = findViewById(R.id.txtNameexampleco)
      txtStock = findViewById(R.id.txtStockexampleco)
      txtMarque = findViewById(R.id.txtMarqueexampleco)
      txtPrice = findViewById(R.id.txtPriceexampleco)
      txtType = findViewById(R.id.txtTypeexampleco)

      image = findViewById(R.id.imagePageOne)
      btnAdd = findViewById(R.id.btnAjouter)

      image!!.setOnClickListener {
          val fintent = Intent(Intent.ACTION_GET_CONTENT)
          fintent.type = "image/jpeg"
          try {
              startActivityForResult(fintent, 100)
          } catch (e: ActivityNotFoundException) {
          }
      }

      btnAdd!!.setOnClickListener {

          checkAndRequestPermission()

          val file = File(f.getPath(uri,this)!!)
          val  reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
          var imagee = MultipartBody.Part.createFormData("image", file.name, reqFile)



          ApiService.TOOL_SERVICE.register(

              imagee,
                  txtName!!.text.toString(),
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
                              val intent = Intent(this@AjoutDesOutilsActivity, DisplayTools::class.java)
                              startActivity(intent)
                              Toast.makeText(this@AjoutDesOutilsActivity, "Tools ajouté avec success !!!", Toast.LENGTH_SHORT).show()

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
    private val apppermissions = arrayOf<String>(

        Manifest.permission.INTERNET,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    fun init(){

    }
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

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "AJOUT_DES_OUTILS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AjoutDesOutilsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
