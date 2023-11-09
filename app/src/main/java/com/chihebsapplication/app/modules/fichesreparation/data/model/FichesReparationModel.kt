package com.chihebsapplication.app.modules.fichesreparation.`data`.model

import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.di.MyApp
import kotlin.String

data class FichesReparationModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDone: String? = MyApp.getInstance().resources.getString(R.string.lbl_done)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPaymentMethod: String? =
      MyApp.getInstance().resources.getString(R.string.msg_fiches_r_parati)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNovCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_nov_1_2023)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAddNewMethod: String? =
      MyApp.getInstance().resources.getString(R.string.msg_ajouter_r_parat)

)
