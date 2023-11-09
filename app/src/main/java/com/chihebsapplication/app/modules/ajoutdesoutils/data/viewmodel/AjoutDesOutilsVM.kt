package com.chihebsapplication.app.modules.ajoutdesoutils.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chihebsapplication.app.modules.ajoutdesoutils.`data`.model.AjoutDesOutilsModel
import org.koin.core.KoinComponent

class AjoutDesOutilsVM : ViewModel(), KoinComponent {
  val ajoutDesOutilsModel: MutableLiveData<AjoutDesOutilsModel> =
      MutableLiveData(AjoutDesOutilsModel())

  var navArguments: Bundle? = null
}
