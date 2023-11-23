package tn.sim.locagest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.Adapters.MethodePaiementAdapter
import tn.sim.locagest.databinding.FragmentMethodePaiementBinding
import utils.MyStatics

class   MethodePaiementFragment : Fragment() {

    private lateinit var binding: FragmentMethodePaiementBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMethodePaiementBinding.inflate(layoutInflater)
        binding.rvMethode.adapter = MethodePaiementAdapter(MyStatics.getListPaiement())

        binding.rvMethode.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}
