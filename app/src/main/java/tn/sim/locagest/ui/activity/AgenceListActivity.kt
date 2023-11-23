package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.AgenceAdapter
import tn.sim.locagest.databinding.ActivityAgenceListBinding
import tn.sim.locagest.viewmodel.AgenceViewModel

class AgenceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgenceListBinding
    private lateinit var agenceAdapter: AgenceAdapter
    private lateinit var agenceViewModel: AgenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgenceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        agenceViewModel = ViewModelProvider(this).get(AgenceViewModel::class.java)

        binding.addAgenceButton.setOnClickListener {
            startActivity(Intent(this, ManageAgenceActivity::class.java))
        }

        agenceViewModel.getAgenceListObservable().observe(this) { data ->
            data?.let {
                println(it)
                println(it)
                println(it)
                println(it)
                println(it)
                println(it)
                println(it)
                println(it)

                agenceAdapter = AgenceAdapter(it, agenceViewModel)
                binding.agenceRV.adapter = agenceAdapter
                binding.agenceRV.layoutManager = LinearLayoutManager(this)
            }
        }

        agenceViewModel.deleteLiveData.observe(this) {
            agenceViewModel.getAll()
        }
    }

    override fun onResume() {
        super.onResume()
        agenceViewModel.getAll()
    }
}
