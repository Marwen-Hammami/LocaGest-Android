package tn.sim.locagest.models
import java.util.Date


enum class MethodeP {
    PaiementAnticipe,
    Cheque,
    Prelevement,
    CarteDeCredit,
    ContreRemboursement,
    PaiementATemperament
}

class Paiement(val id: Int, val montant: Double, val datePaiement: Date, val methodePaiement: MethodeP) {
}










