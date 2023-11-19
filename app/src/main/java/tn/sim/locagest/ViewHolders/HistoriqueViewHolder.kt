package tn.sim.locagest.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import tn.sim.locagest.models.Historique
import tn.sim.locagest.databinding.ItemHistoriqueBinding
import tn.sim.locagest.viewmodel.ReservationViewModel
import java.text.SimpleDateFormat
import java.util.Locale





class HistoriqueViewHolder(private val binding: ItemHistoriqueBinding,private val viewModel: ReservationViewModel) : RecyclerView.ViewHolder(binding.root) {


    private var listener: OnDeleteClickListener? = null



    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        this.listener = listener
    }

   // private val format = SimpleDateFormat("dd/MM/yyyy")
    var format = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)

    fun setData(historique: Historique) {
        binding.DateD.text = historique.DateDebut?.let { format.format(it) }
        binding.DateF.text = historique.DateFin?.let { format.format(it) }
        binding.Total.text = historique.Total.toString()

        binding.btnDelete.setOnClickListener {

            historique._id?.let { it1 -> delete(it1) }
            println("NICE")
            //Toast.makeText(binding.root,"the reservation has been added" ,Toast.LENGTH_LONG).show()
        Snackbar.make(binding.root,"the reservation has been deleted",Snackbar.LENGTH_LONG)
        }

    }

    private fun delete(id: String) {
        viewModel.deleteReservation(id)// hetheya fun 9a3da t3ayet lel end point mta3 delete
    }
}
