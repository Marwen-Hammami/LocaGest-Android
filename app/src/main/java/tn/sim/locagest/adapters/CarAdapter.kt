package tn.sim.locagest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardviewVoitureBinding
import tn.sim.locagest.models.Car
import tn.sim.locagest.viewholder.CarViewHolder

class CarAdapter(val cars: Array<Car>) : RecyclerView.Adapter<CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CardviewVoitureBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CarViewHolder(parent.context,binding)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.setData(car)

    }
}
