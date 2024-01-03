package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.AgenceAdapter
import tn.sim.locagest.databinding.ActivityAgenceListBinding
import tn.sim.locagest.models.Agence
import tn.sim.locagest.viewmodel.AgenceViewModel

class AgenceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgenceListBinding
    private lateinit var agenceAdapter: AgenceAdapter
    private lateinit var agenceViewModel: AgenceViewModel

    private var agenceList: List<Agence> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgenceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        agenceViewModel = ViewModelProvider(this)[AgenceViewModel::class.java]

        binding.addAgenceButton.setOnClickListener {
            startActivity(Intent(this, ManageAgenceActivity::class.java))
        }

        agenceViewModel.getAgenceListObservable().observe(this) { data ->
            data?.let {
                agenceList = it
                refreshData(it)
            }
        }

        agenceViewModel.deleteLiveData.observe(this) {
            agenceViewModel.getAll()
        }

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    refreshData(agenceList)
                } else {
                    refreshData(
                        agenceList.filter { agence ->
                            agence.name.contains(s, ignoreCase = true)
                        }
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun refreshData(list: List<Agence>) {
        agenceAdapter = AgenceAdapter(list, agenceViewModel)
        binding.agenceRV.adapter = agenceAdapter
        binding.agenceRV.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        agenceViewModel.getAll()
    }
}
