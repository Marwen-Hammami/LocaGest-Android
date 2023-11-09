package com.chihebsapplication.app.modules.ajoutdesoutils.`data`.model

import com.chihebsapplication.app.R
import com.chihebsapplication.app.appcomponents.di.MyApp
import kotlin.String

data class AjoutDesOutilsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtAjouterdesout: String? =
      MyApp.getInstance().resources.getString(R.string.msg_ajouter_des_out)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNameexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_stock)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNameexamplecoOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_marque)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_prix)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNameexamplecoTwo: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_upload_image)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etGroupSevenValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etGroupEightValue: String? = null
)
