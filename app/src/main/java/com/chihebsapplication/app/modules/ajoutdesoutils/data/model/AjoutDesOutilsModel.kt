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
  var txtNameexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_nom_d_outils)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtStockexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_stock)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMarqueexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_marque)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPriceexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_prix)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTypeexampleco: String? = MyApp.getInstance().resources.getString(R.string.lbl_type_d_outils)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtImageexampleco: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_upload_image)
  ,

)
