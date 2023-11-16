package tn.sim.locagest.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.adapters.CarAdapter
import tn.sim.locagest.databinding.DetailFlotteBinding
import tn.sim.locagest.models.Car
import tn.sim.locagest.viewmodel.CarViewModel

class detail_flotte: AppCompatActivity() {

    private lateinit var binding : DetailFlotteBinding

    //For Subjects ViwModel
    lateinit var messageViewModel: CarViewModel
    lateinit var listMessagess: MutableList<Car>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailFlotteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.btnAjout.setOnClickListener {
            var c = Car("4444","Renault","Model","Hybride", "Manuelle")

            messageViewModel.createMessage(c)
        }
    }
    private fun initViewModel() {
        listMessagess = mutableListOf()
        messageViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        messageViewModel.getCars()

        messageViewModel.getCarsObservable().observe(this, Observer<List<Car>?> {
            if(it == null) {
                Log.w("MyApp", "There is no Messages")
            }else {
                listMessagess = it.toMutableList()
                initRecyclerView()
            }
        })
    }

    fun initRecyclerView() {
        val myAdapter = CarAdapter(listMessagess.toTypedArray())  //to get the latest message at the bottom


//        val myAdapter = CarAdapter(listUsers)

        binding.recyclerView.adapter = myAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
    }
}