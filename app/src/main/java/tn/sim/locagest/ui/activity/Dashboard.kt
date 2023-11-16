package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import tn.sim.locagest.R

class dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        constraintLayout.setOnClickListener {
            val intent = Intent(this@dashboard, detail_flotte::class.java)
            startActivity(intent)
        }

        // Ajoutez d'autres écouteurs de clic si nécessaire pour d'autres éléments
    }
}
