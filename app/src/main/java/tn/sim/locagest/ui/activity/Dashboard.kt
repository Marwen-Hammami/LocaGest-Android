package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        binding.imageView8.setOnClickListener{
            Log.w("d","d")
            val intent3 = Intent(this, page_entretien::class.java )
            startActivity(intent3)
        }


    }
}
