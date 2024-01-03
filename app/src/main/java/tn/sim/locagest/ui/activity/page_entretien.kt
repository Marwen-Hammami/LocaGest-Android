package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.CarAdapter
import tn.sim.locagest.databinding.PageEntretienBinding
import tn.sim.locagest.models.Car
import tn.sim.locagest.viewmodel.CarViewModel

class page_entretien : AppCompatActivity() {

    private lateinit var binding: PageEntretienBinding

    // For Car ViewModel
    lateinit var carViewModel: CarViewModel
    lateinit var carList: MutableList<Car>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PageEntretienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()



        // Uncomment the following block if you want to navigate to ajouter_entretien

        binding.button.setOnClickListener {
            val intent = Intent(this, ajouter_entretien::class.java)
            startActivity(intent)
        }
        binding.imageView11.setOnClickListener { onBackPressed() }

    }

    private fun initViewModel() {
        carList = mutableListOf()
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        carViewModel.getCars()

        carViewModel.getCarsObservable().observe(this, Observer<List<Car>?> {
            if (it == null) {
                Log.w("MyApp", "There is no Cars")
            } else {
                carList = it.toMutableList()
                initRecyclerView()
            }
        })
    }

    fun initRecyclerView() {
        val carAdapter = CarAdapter(carList.toTypedArray())
        binding.recyclerView2.adapter = carAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.layoutManager = layoutManager
    }
    override fun onBackPressed() {


        // Appeler la méthode super.onBackPressed() pour fermer l'activité actuelle
        super.onBackPressed()
    }
}
