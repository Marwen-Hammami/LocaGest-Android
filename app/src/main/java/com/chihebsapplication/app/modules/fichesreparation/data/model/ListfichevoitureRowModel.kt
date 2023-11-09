package com.chihebsapplication.app.modules.fichesreparation.`data`.model

import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ListfichevoitureRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFichevoiture: String? = MyApp.getInstance().resources.getString(R.string.msg_fiche_voiture)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtJohnnyRios: String? = MyApp.getInstance().resources.getString(R.string.msg_chiheb_ben_abde)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHeading: String? = MyApp.getInstance().resources.getString(R.string.msg_1_nov_2023_16)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHeadingOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_en_cours)

)
