package tn.sim.locagest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.HistoriqueAdapter
import tn.sim.locagest.databinding.FragmentHistoriqueBinding
import utils.MyStatics

class HistoriqueFragment : Fragment() {

    private lateinit var binding: FragmentHistoriqueBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriqueBinding.inflate(layoutInflater)
        binding.rvHistorique.adapter = HistoriqueAdapter(MyStatics.getListHistorique())

        binding.rvHistorique.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}