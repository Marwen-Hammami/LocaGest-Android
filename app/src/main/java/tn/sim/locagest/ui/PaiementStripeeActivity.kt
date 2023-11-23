package tn.sim.locagest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.stripe.android.paymentsheet.CreateIntentResult
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import tn.sim.locagest.App
import tn.sim.locagest.R
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.stripe.android.PaymentConfiguration
import tn.sim.locagest.api.ReservationService

class PaiementStripeeActivity : AppCompatActivity() {

    private lateinit var paymentSheet: PaymentSheet
    lateinit var customerConfig: PaymentSheet.CustomerConfiguration
    lateinit var paymentIntentClientSecret: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement)

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
        "http://10.0.2.2:9090/res/payment-sheet".httpPost().responseJson { _, _, result ->
            if (result is Result.Success) {
                val responseJson = result.get().obj()
                paymentIntentClientSecret = responseJson.getString("paymentIntent")
                customerConfig = PaymentSheet.CustomerConfiguration(
                    responseJson.getString("customer"),
                    responseJson.getString("ephemeralKey")
                )
                val publishableKey = responseJson.getString("publishableKey")
                PaymentConfiguration.init(this, publishableKey)
                presentPaymentSheet()
            }
        }

    }

    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {

        when(paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                print("Canceled")
            }
            is PaymentSheetResult.Failed -> {
                print("Error: ${paymentSheetResult.error}")
            }
            is PaymentSheetResult.Completed -> {
                // Display for example, an order confirmation screen
                print("Completed")
            }
        }

          }


    fun presentPaymentSheet() {
        Log.d("Paymentsheet", "Présentation du Paymentsheet")
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret,
            PaymentSheet.Configuration(
                merchantDisplayName = "Reservation car",
                customer = customerConfig,
                // Set `allowsDelayedPaymentMethods` to true if your business handles
                // delayed notification payment methods like US bank accounts.
                allowsDelayedPaymentMethods = true
            )
        )
    }
    private fun handleCheckoutButtonPressed() {
        val intentConfig = PaymentSheet.IntentConfiguration(
            mode = PaymentSheet.IntentConfiguration.Mode.Setup(
                currency = "usd",
            ),
            // Other configuration options...
        )

        paymentSheet.presentWithIntentConfiguration(
            intentConfiguration = intentConfig,
            // Optional configuration - See the "Customize the sheet" section in this guide
            configuration = PaymentSheet.Configuration(
                merchantDisplayName = "Example Inc.",
            )
        )
    }

}