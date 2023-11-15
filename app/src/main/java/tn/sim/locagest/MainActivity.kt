package tn.sim.locagest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tn.sim.locagest.databinding.ActivityMainBinding
import tn.sim.locagest.fragments.HistoriqueFragment
import tn.sim.locagest.ui.PaiementActivity
import tn.sim.locagest.ui.ReservationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = findViewById(R.id.app_bar)
        toolbarTitle = findViewById(R.id.toolbar_title)

       // toolbarTitle.text = " Historique Des Réservations "

        setSupportActionBar(toolbar)

        val historiqueFragment = HistoriqueFragment()
        changeFragment(historiqueFragment, "historiqueFragment")


       // val intent = Intent(this, ReservationActivity::class.java)
       // startActivity(intent)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {


                R.id.action_reservations -> {
                    val intent = Intent(this, ReservationActivity::class.java)
                    startActivity(intent)
                    toolbarTitle.text = "Reservations"
                }


                R.id.action_historique -> {
                    val historiqueFragment = HistoriqueFragment()
                    changeFragment(historiqueFragment, "historiqueFragment")
                    toolbarTitle.text = "Historique Reservation"
                }



                R.id.action_paiement -> {
                    val intent = Intent(this, PaiementActivity::class.java)
                    startActivity(intent)
                    toolbarTitle.text = "Paiement"
                }

            }
            true
        }
    }



    private fun changeFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        if (tag != null && tag.isNotEmpty()) {
            transaction.replace(binding.fragmentContainerView.id, fragment, tag).addToBackStack(tag)
        } else {
            transaction.replace(binding.fragmentContainerView.id, fragment)
        }
        transaction.commit()

    }





}
