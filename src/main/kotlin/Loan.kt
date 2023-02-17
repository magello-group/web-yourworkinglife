import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Loan( val personId: Int, val loanType: String) {

    var loanAmount: Float = 0.0F
    var loanInterest: Float = 0.0F
    var loanMonthPayment: Float = 0.0F
    var ageWhenPayed: Int = 0


    fun calculateInterest(): Float {

        return ((this.loanAmount * (this.loanInterest / 100.0F)) / (this.ageWhenPayed.toFloat() * 12.0F))
    }
}