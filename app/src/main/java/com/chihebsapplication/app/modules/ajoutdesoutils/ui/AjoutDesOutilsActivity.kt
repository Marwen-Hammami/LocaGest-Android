package com.chihebsapplication.app.modules.ajoutdesoutils.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.base.BaseActivity
import com.chihebsapplication.app.databinding.ActivityAjoutDesOutilsBinding
import com.chihebsapplication.app.modules.ajoutdesoutils.`data`.viewmodel.AjoutDesOutilsVM
import kotlin.String
import kotlin.Unit

class AjoutDesOutilsActivity :
    BaseActivity<ActivityAjoutDesOutilsBinding>(R.layout.activity_ajout_des_outils) {
  private val viewModel: AjoutDesOutilsVM by viewModels<AjoutDesOutilsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.ajoutDesOutilsVM = viewModel
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
