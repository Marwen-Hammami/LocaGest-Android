package tn.sim.locagest

import android.app.Application
import com.stripe.android.PaymentConfiguration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_51OF1qTCCAcch0OaOh4RBDmMrgB2H2fldhAv7VHVTcSVMAMyryPXrYSoZ5Yd4UMGt9PSKhmXSe3b4rzDVp2Jnf61P00LBqL30Oe"
        )

    }
}