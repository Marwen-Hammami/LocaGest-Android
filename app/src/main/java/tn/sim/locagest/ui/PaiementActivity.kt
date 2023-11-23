package tn.sim.locagest.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import tn.sim.locagest.R
import tn.sim.locagest.databinding.ActivityPaiementBinding
import tn.sim.locagest.databinding.FragmentHistoriqueBinding
import tn.sim.locagest.fragments.MethodePaiementFragment

class PaiementActivity : Fragment() {

    private lateinit var paymentSheet: PaymentSheet
    private lateinit var binding: ActivityPaiementBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityPaiementBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val newsImage1 = binding.newsImage1
        val Title1 = binding.Title1
        val newsDescription2 = binding.newsDescription2
        val totalAmountLabel = binding.totalAmountLabel
        val totalAmountValue = binding.totalAmountValue
        val btnMethodeP = binding.btnMethodeP
        val btnPayer = binding.btnPayer*/

      /*  btnMethodeP.setOnClickListener {
            val fragment = MethodePaiementFragment()
            val transaction: FragmentTransaction = fragmentManager?.beginTransaction()!!
            transaction.replace(com.google.android.material.R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnPayer.setOnClickListener {
            val intent = Intent(requireContext(), PaiementStripeeActivity::class.java)
            startActivity(intent)

        }*/
    }}

