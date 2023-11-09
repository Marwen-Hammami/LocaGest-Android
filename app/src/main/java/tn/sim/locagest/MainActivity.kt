package tn.sim.locagest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.fragments.HistoriqueFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Créez une nouvelle instance de votre fragment.
        val historiqueFragment = HistoriqueFragment()

        // Obtenez le FragmentManager et ouvrez une transaction.
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Ajoutez le fragment à votre FrameLayout en utilisant son identifiant.
        fragmentTransaction.replace(R.id.fragment_container, historiqueFragment)
        fragmentTransaction.commit()
    }
}
