package tn.sim.locagest.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import tn.sim.locagest.App
import com.stripe.android.PaymentConfiguration
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tn.sim.locagest.MainActivityReservation
import tn.sim.locagest.api.ReservationService
import tn.sim.locagest.databinding.ActivityPaiementInfoBinding

class PaiementStripeeFragment : Fragment() {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 100
    }

    private lateinit var binding: ActivityPaiementInfoBinding
    private val retroInstance = App.ReservationServiceRetroInstance
    private lateinit var paymentIntentClientSecret: String
    private lateinit var paymentLauncher: PaymentLauncher
    private lateinit var alertDialog: AlertDialog
    private val CREATE_FILE_REQUEST_CODE = 1
    private var isReceiptGenerated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityPaiementInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val paymentConfiguration = PaymentConfiguration.getInstance(requireContext())
        paymentLauncher = PaymentLauncher.create(
            this,
            paymentConfiguration.publishableKey,
            paymentConfiguration.stripeAccountId,
            ::onPaymentResult
        )

        // Start the checkout process immediately
        startCheckout()

        binding.btnSubmitPayment.setOnClickListener {
            Log.d("TAG", "onCreate: okk")
            if (::paymentIntentClientSecret.isInitialized) {
                confirmPayment()
            } else {
                Log.d("PaiementActivity", "Failed to confirm payment")
            }
        }
    }

    private fun startCheckout() {
        // Call the createPaymentIntent endpoint without any parameters
        val call = retroInstance.createPaiementIntent()
        call.enqueue(object : retrofit2.Callback<ReservationService.PaymentIntentResponse> {
            override fun onResponse(
                call: retrofit2.Call<ReservationService.PaymentIntentResponse>,
                response: retrofit2.Response<ReservationService.PaymentIntentResponse>
            ) {
                if (response.isSuccessful) {
                    paymentIntentClientSecret = response.body()?.clientSecret.orEmpty()
                } else {
                    Log.d("PaymentActivity", "Failed to create payment intent")
                }
            }

            override fun onFailure(
                call: retrofit2.Call<ReservationService.PaymentIntentResponse>,
                t: Throwable
            ) {
                Log.e("PaymentActivity", "Error creating payment intent", t)
            }
        })
    }

    private fun confirmPayment() {
        binding.cardInputWidget.paymentMethodCreateParams?.let { params ->
            val confirmParams =
                ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                    params,
                    paymentIntentClientSecret
                )
            // to LAUNCH A BACKGROUND THREAD (io)
            // to LAUNCH A MAIN THREAD (MAiN)

            CoroutineScope(Dispatchers.IO).launch {
                paymentLauncher.confirm(confirmParams)
            }

        }
    }

    private fun onPaymentResult(paymentResult: PaymentResult) {

        val message = when (paymentResult) {
            is PaymentResult.Completed -> {
                if (!isReceiptGenerated) {

                }
                "Completed!"
            }

            is PaymentResult.Canceled -> {
                "Canceled!"
            }

            is PaymentResult.Failed -> {
                // This string comes from the PaymentIntent's error message.
                // See here: https://stripe.com/docs/api/payment_intents/object#payment_intent_object-last_payment_error-message
                "Failed: " + paymentResult.throwable.message
            }
        }
        Log.d("PaymentActivity", message)
        showPaymentResultDialog(message)
        Log.d("PaymentActivity", "onPaymentResult called")
        // Add additional log to check if this function is being called
        Log.d("PaymentActivity", "onPaymentResult called")
    }

    private fun showPaymentResultDialog(message: String) {
        // Create an AlertDialog.Builder
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
        builder.setCancelable(false)

        // Add "Return Home" button
        val paiementFragment = PaiementActivity()
        // Add "Return Home" button
        builder.setPositiveButton("Return Home") { _, _ ->
            (activity as MainActivityReservation).changeFragment(paiementFragment, "paiementFragment","Paiement")
        }


        // Create the AlertDialog
        alertDialog = builder.create()

        // Show the AlertDialog
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //cancel() // Cancel the coroutine scope when the activity is destroyed
    }


}