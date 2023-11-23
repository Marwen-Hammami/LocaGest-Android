package tn.sim.locagest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.stripe.android.PaymentConfiguration
import tn.sim.locagest.databinding.ActivityMainReservationBinding
import tn.sim.locagest.fragments.HistoriqueFragment
import tn.sim.locagest.ui.PaiementActivity
import tn.sim.locagest.ui.PaiementStripeeFragment
import tn.sim.locagest.ui.ReservationActivity


class MainActivityReservation : AppCompatActivity() {

    lateinit var binding: ActivityMainReservationBinding
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    companion object {
        private  const val API_KEY ="pk_test_51OF1qTCCAcch0OaOh4RBDmMrgB2H2fldhAv7VHVTcSVMAMyryPXrYSoZ5Yd4UMGt9PSKhmXSe3b4rzDVp2Jnf61P00LBqL30Oe"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)



        PaymentConfiguration.init(
            applicationContext,
            publishableKey = API_KEY
        )


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
