package tn.sim.locagest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.Adapters.HistoriqueAdapter
import tn.sim.locagest.MainActivity
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.R
import tn.sim.locagest.databinding.FragmentHistoriqueBinding
import tn.sim.locagest.ui.ReservationActivity
import tn.sim.locagest.viewmodel.HistoriqueViewModel
import tn.sim.locagest.viewmodel.ReservationViewModel
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.log

class HistoriqueFragment : Fragment() {

    private lateinit var binding: FragmentHistoriqueBinding

    private val viewModel:ReservationViewModel by viewModels()

    //For Subjects ViwModel
    lateinit var historiqueViewModel: HistoriqueViewModel
    lateinit var listHistorique: MutableList<Historique>

    lateinit var historyRecycleView:RecyclerView
    lateinit var layoutManager : LinearLayoutManager
    lateinit var historyAdapter:HistoriqueAdapter

    var mutableHistoryList: MutableList<Historique> = mutableListOf()

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



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addReservation.setOnClickListener {
            gotToReservationView()
        }
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)

        val dtStart1 = "05/01/2020"
        val dtEnd1 = "04/02/2020"

        val dtStart2 = "04/01/2021"
        val dtEnd2 = "04/02/2021"

        val dtStart3 = "04/03/2022"
        val dtEnd3 = "04/03/2022"

        val dtStart4 = "04/03/2024"
        val dtEnd4 = "04/03/2024"

       // val history1 = Historique(1, format.parse(dtStart1)!!,format.parse(dtEnd1)!!,102f)
        //val history2 = Historique(2,format.parse(dtStart2)!!,format.parse(dtEnd2)!!,202f)
        //val history3 = Historique(3, format.parse(dtStart3)!!,format.parse(dtEnd3)!!,302f)
        //val history4 = Historique(4, format.parse(dtStart4)!!,format.parse(dtEnd4)!!,402f)


        //mutableHistoryList.add(history1)
       // mutableHistoryList.add(history2)
       // mutableHistoryList.add(history3)
        //mutableHistoryList.add(history4)

      //  initRecycler(mutableHistoryList)

        //get data from backend
        listHistorique = mutableListOf()
        historiqueViewModel = ViewModelProvider(this).get(HistoriqueViewModel::class.java)
        //        historiqueViewModel.getReservationList()
//
//        historiqueViewModel.getHistoriqueListObservable().observe(viewLifecycleOwner, Observer<List<Historique>?> {
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
//    }
        historiqueViewModel.getHistoriqueList()
        historiqueViewModel.getHistoriqueListObservable().observe( viewLifecycleOwner) {
            Log.d("MyApp", "onViewCreated:$it ")
            it?.let { it1 -> initRecycler(it1) }
        }

    }


    private fun initRecycler(historiquelist : List<Historique>) {

        layoutManager =   LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        historyRecycleView = binding.rvHistorique
        historyRecycleView.layoutManager = layoutManager

        historyAdapter = HistoriqueAdapter(historiquelist,viewModel)
        historyRecycleView.adapter = historyAdapter
    }

    private fun gotToReservationView(){
        val reservationActivity = ReservationActivity()
        (activity as MainActivity).changeFragment(reservationActivity, "reservationActivity","Reservations")
    }

}