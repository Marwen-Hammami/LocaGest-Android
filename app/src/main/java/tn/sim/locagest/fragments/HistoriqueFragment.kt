package tn.sim.locagest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.HistoriqueAdapter
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.databinding.FragmentHistoriqueBinding
import tn.sim.locagest.viewmodel.HistoriqueViewModel

class HistoriqueFragment : Fragment() {

    private lateinit var binding: FragmentHistoriqueBinding

    //For Subjects ViwModel
    lateinit var historiqueViewModel: HistoriqueViewModel
    lateinit var listHistorique: MutableList<Historique>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriqueBinding.inflate(layoutInflater)

        //get data from backend
        listHistorique = mutableListOf()
        historiqueViewModel = ViewModelProvider(this).get(HistoriqueViewModel::class.java)

        historiqueViewModel.getReservationList()

        historiqueViewModel.getReservationListObservable().observe(viewLifecycleOwner, Observer<List<Historique>?> {
            if(it == null) {
                Log.w("MyApp", "There is no Reservations")
            }else {
                listHistorique = it.toMutableList()

                binding.rvHistorique.adapter = HistoriqueAdapter(listHistorique)

                binding.rvHistorique.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })

        return binding.root
    }

}