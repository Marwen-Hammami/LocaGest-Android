package tn.sim.locagest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.sim.locagest.databinding.DashboardBinding
import tn.sim.locagest.ui.activity.detail_flotte
import tn.sim.locagest.ui.activity.geolocalisation
import tn.sim.locagest.ui.activity.page_entretien

class MainActivity : AppCompatActivity() {

    private lateinit var binding : DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearLayoutCompat.setOnClickListener {
            val intent2 = Intent(this, detail_flotte::class.java)
            startActivity(intent2)
        }
        binding.linearLayout.setOnClickListener{
            val intent3 = Intent(this, page_entretien::class.java)
            startActivity(intent3)
        }
        binding.linearLayout2.setOnClickListener{
            val intent4 = Intent(this, geolocalisation::class.java)
            startActivity(intent4)
        }
    }
}