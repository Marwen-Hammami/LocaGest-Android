package tn.sim.locagest.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import tn.sim.locagest.R
import tn.sim.locagest.fragments.MethodePaiementFragment

class PaiementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement)

        val newsImage1 = findViewById<ImageView>(R.id.newsImage1)
        val Title1 = findViewById<TextView>(R.id.Title1)
        val newsDescription2 = findViewById<TextView>(R.id.newsDescription2)
        val totalAmountLabel = findViewById<TextView>(R.id.totalAmountLabel)
        val totalAmountValue = findViewById<TextView>(R.id.totalAmountValue)
        val btnMethodeP = findViewById<Button>(R.id.btn_methodeP)
        val btnPayer = findViewById<Button>(R.id.btn_payer)

        btnMethodeP.setOnClickListener {
            val fragment = MethodePaiementFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(com.google.android.material.R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnPayer.setOnClickListener {
            // Ajoutez ici le code pour gérer le clic sur le bouton "Payer"
        }
    }
}