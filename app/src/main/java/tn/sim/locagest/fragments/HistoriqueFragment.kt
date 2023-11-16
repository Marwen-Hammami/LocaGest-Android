package tn.sim.locagest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.databinding.FragmentHistoriqueBinding
import tn.sim.locagest.viewmodel.HistoriqueViewModel

class HistoriqueFragment : Fragment() {

    private lateinit var binding: FragmentHistoriqueBinding

    //For Subjects ViwModel
    lateinit var historiqueViewModel: HistoriqueViewModel
    lateinit var listHistorique: MutableList<Historique>

   /* private fun démarrerPaiementActivity() {
        val intent = Intent(requireActivity(), PaiementActivity::class.java)
        startActivity(intent)
    }




    private fun démarrerReservationActivity() {
        val intent = Intent(requireActivity(), ReservationActivity::class.java)
        startActivity(intent)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_paiement -> {
                démarrerPaiementActivity()
                return true
            }
            R.id.action_reservations -> {
                démarrerReservationActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriqueBinding.inflate(layoutInflater)

        //get data from backend
        listHistorique = mutableListOf()
        historiqueViewModel = ViewModelProvider(this).get(HistoriqueViewModel::class.java)

//        historiqueViewModel.getReservationList()
//
//        historiqueViewModel.getReservationListObservable().observe(viewLifecycleOwner, Observer<List<Historique>?> {
//            if(it == null) {
//                Log.w("MyApp", "There is no Reservations")
//            }else {
//                listHistorique = it.toMutableList()
//
//                binding.rvHistorique.adapter = HistoriqueAdapter(listHistorique)
//
//                binding.rvHistorique.layoutManager =
//                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            }
//        })

        return binding.root
    }

}