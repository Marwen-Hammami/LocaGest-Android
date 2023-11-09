package com.chihebsapplication.app.modules.fichesreparation.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chihebsapplication.app.modules.fichesreparation.`data`.model.FichesReparationModel
import com.chihebsapplication.app.modules.fichesreparation.`data`.model.ListfichevoitureRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class FichesReparationVM : ViewModel(), KoinComponent {
  val fichesReparationModel: MutableLiveData<FichesReparationModel> =
      MutableLiveData(FichesReparationModel())

  var navArguments: Bundle? = null

  val listfichevoitureList: MutableLiveData<MutableList<ListfichevoitureRowModel>> =
      MutableLiveData(mutableListOf())
}
