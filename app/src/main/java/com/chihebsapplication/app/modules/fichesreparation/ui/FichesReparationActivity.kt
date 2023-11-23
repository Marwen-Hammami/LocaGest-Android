/*
package com.chihebsapplication.app.modules.fichesreparation.ui

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.base.BaseActivity
import com.chihebsapplication.app.databinding.ActivityFichesReparationBinding
import com.chihebsapplication.app.modules.ajoutdesoutils.ui.AjoutDesOutilsActivity
import com.chihebsapplication.app.modules.fichesreparation.`data`.model.ListfichevoitureRowModel
import com.chihebsapplication.app.modules.fichesreparation.`data`.viewmodel.FichesReparationVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FichesReparationActivity :
    BaseActivity<ActivityFichesReparationBinding>(R.layout.activity_fiches_reparation) {
  private val viewModel: FichesReparationVM by viewModels<FichesReparationVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listfichevoitureAdapter =
    ListfichevoitureAdapter(viewModel.listfichevoitureList.value?:mutableListOf())
    binding.recyclerListfichevoiture.adapter = listfichevoitureAdapter
    listfichevoitureAdapter.setOnItemClickListener(
    object : ListfichevoitureAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListfichevoitureRowModel) {
        onClickRecyclerListfichevoiture(view, position, item)
      }
    }
    )
    viewModel.listfichevoitureList.observe(this) {
      listfichevoitureAdapter.updateData(it)
    }
    binding.fichesReparationVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = AjoutDesOutilsActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
      binding.imageArrowleft.setOnClickListener {
        finish()
      }
    }

    fun onClickRecyclerListfichevoiture(
      view: View,
      position: Int,
      item: ListfichevoitureRowModel
    ): Unit {
      when(view.id) {
      }
    }

    companion object {
      const val TAG: String = "FICHES_REPARATION_ACTIVITY"

    }
  }
*/
