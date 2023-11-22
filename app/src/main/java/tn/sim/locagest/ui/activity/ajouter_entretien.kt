package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.databinding.AjouterEntretienBinding
import java.util.regex.Pattern

class ajouter_entretien : AppCompatActivity() {

    private lateinit var binding: AjouterEntretienBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AjouterEntretienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddEntretien.setOnClickListener {
            val cout = binding.Cout.text.toString()
            val titre = binding.Titre.toString()
            val description = binding.Description.toString()

            // Vérifiez que le champ coût ne soit pas vide
            if (cout.isNotEmpty() && titre.isNotEmpty() && description.isNotEmpty()) {
                // Vérifiez que le champ coût suit le format attendu
                if (isValidCoutFormat(cout)) {
                    // Ajoutez ici le code pour gérer l'ajout d'entretien à votre modèle de données

                    // Affichez un message indiquant que l'entretien a été ajouté avec succès
                    Toast.makeText(this, "Entretien ajouté avec succès", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, page_entretien::class.java)
                    startActivity(intent)

                    // Redirection vers l'activité DetailFlotteActivity ou autre activité nécessaire
                } else {
                    // Affichez un message d'erreur si le format du coût est incorrect
                    Toast.makeText(this@ajouter_entretien, "Format du coût incorrect. Utilisez le format: [nombre]DT", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Affichez un message d'erreur si un champ est vide
                Toast.makeText(this@ajouter_entretien, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCoutFormat(cout: String): Boolean {
        val pattern = Pattern.compile("\\d+DT")
        return pattern.matcher(cout).matches()
    }
}
