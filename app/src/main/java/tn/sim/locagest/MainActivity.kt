package tn.sim.locagest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tn.sim.locagest.databinding.ActivityMainBinding
import tn.sim.locagest.databinding.ActivityMainReservationBinding
import tn.sim.locagest.fragments.HistoriqueFragment
import tn.sim.locagest.ui.PaiementActivity
import tn.sim.locagest.ui.ReservationActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainReservationBinding
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = findViewById(R.id.app_bar)
        toolbarTitle = findViewById(R.id.toolbar_title)

        setSupportActionBar(toolbar)

        val historiqueFragment = HistoriqueFragment()
        changeFragment(historiqueFragment, "historiqueFragment","Historique Reservation")

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_reservations -> {
                    val reservationActivity = ReservationActivity()
                    changeFragment(reservationActivity, "reservationActivity","Reservations")
                }
                R.id.action_historique -> {
                    val historiqueFragment = HistoriqueFragment()
                    changeFragment(historiqueFragment, "historiqueFragment","Historique Reservation")
                }

                R.id.action_paiement -> {
                    val paiementActivity = PaiementActivity()
                    changeFragment(paiementActivity, "reservationActivity","Paiement")
                }

            }
            true
        }
    }

    fun changeFragment(fragment: Fragment, tag: String, tileText:String) {

        val transaction = supportFragmentManager.beginTransaction()

        if (tag.isNotEmpty()) {
            transaction.replace(binding.fragmentContainerView.id, fragment, tag).addToBackStack(tag)
        } else {
            transaction.replace(binding.fragmentContainerView.id, fragment)
        }
        transaction.commit()

        toolbarTitle.text = tileText
    }

}
