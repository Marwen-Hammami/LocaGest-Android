package tn.sim.locagest.ui


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import tn.sim.locagest.R
import tn.sim.locagest.fragments.MethodePaiementFragment

class PaiementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement)

        val newsImage1 = findViewById<ImageView>(R.id.newsImage1)
        val Title1 = findViewById<TextView>(R.id.Title1)
        val newsDescription2 = findViewById<TextView>(R.id.newsDescription2)
        val myTextView = findViewById<TextView>(R.id.my_text_view)
        val btnMethodeP = findViewById<Button>(R.id.btn_methodeP)
        val btnPayer = findViewById<ExtendedFloatingActionButton>(R.id.btnpayer)

        btnMethodeP.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(com.google.android.material.R.id.container, MethodePaiementFragment())
            transaction.commit()
        }


        btnPayer.setOnClickListener {
            // Ajoutez ici le code pour gérer le clic sur le bouton "Payer"
        }
    }
}
