package utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.Model.MethodeP
import java.util.Date

class MyStatics {

    companion object {
        fun hideKeyboard(context: Context, view: View) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun getListHistorique() : MutableList<Historique> {
            val DateD = Date()
            val DateF = Date()
            return mutableListOf(
            //   Historique(1, DateD, DateF, 100.0f),
             //   Historique(2, DateD, DateF, 200.0f),
              //  Historique(3, DateD, DateF, 300.0f),
              //  Historique(4, DateD, DateF, 400.0f)
            )
        }



        fun getListPaiement() : MutableList<MethodeP> {
            val DateD = Date()
            val DateF = Date()
            return mutableListOf(
             TODO()
            )
        }

    }

}