package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.databinding.CardviewVoitureBinding
import tn.sim.locagest.models.Car

class cardview_voiture : AppCompatActivity() {

    private lateinit var binding: CardviewVoitureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CardviewVoitureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ... Initialiser les autres éléments de votre CardView ...

        // Gérer le clic sur le bouton "Détails"

    }
}
