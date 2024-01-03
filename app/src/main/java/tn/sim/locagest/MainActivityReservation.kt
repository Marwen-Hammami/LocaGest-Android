package tn.sim.locagest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tn.sim.locagest.databinding.ActivityMainReservationBinding
import tn.sim.locagest.fragments.HistoriqueFragment
import tn.sim.locagest.ui.MainActivityChat
import tn.sim.locagest.ui.PaiementActivity
import tn.sim.locagest.ui.ReservationActivity
import tn.sim.locagest.ui.activity.AgenceListActivity
import tn.sim.locagest.ui.activity.UserProfile


class MainActivityReservation : AppCompatActivity() {

    lateinit var binding: ActivityMainReservationBinding

    private lateinit var toolbarTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)


        binding = ActivityMainReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
      //  toolbarTitle = "LocaGest" // Initialize the property with a value



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

      //  toolbarTitle.text = tileText
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.btm_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        return when (item.itemId) {
            R.id.CarPage -> {
                intent = Intent(this, MainActivityFlotte::class.java)
                startActivity(intent)
                true
            }

            R.id.UserPage -> {
                intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
                true
            }
            R.id.ChatPage-> {
                intent = Intent(this, MainActivityChat::class.java)
                startActivity(intent)
                true
            }
             R.id.ResPage-> {
                intent = Intent(this, MainActivityReservation::class.java)
                startActivity(intent)
                true
            }
            R.id.AgencePage-> {
                intent = Intent(this, AgenceListActivity::class.java)
                startActivity(intent)
                true
            }
            /* tn.sim.locagest.R.id.GaragePage-> {
                 intent = Intent(this, ::class.java)
                 startActivity(intent)
                 true
             }*/

            else -> super.onOptionsItemSelected(item)
        }
    }

}
