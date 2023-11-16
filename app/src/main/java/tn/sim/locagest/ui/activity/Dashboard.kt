package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import tn.sim.locagest.R
import tn.sim.locagest.databinding.DashboardBinding

class dashboard : AppCompatActivity() {

    private lateinit var binding : DashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        binding.imageView7.setOnClickListener {
            Log.w("d","d")
            val intent2 = Intent(this, detail_flotte::class.java)
            startActivity(intent2)
        }

        // Ajoutez d'autres écouteurs de clic si nécessaire pour d'autres éléments
    }
}
