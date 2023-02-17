import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Loan( val personId: Int, val loanType: String) {

    var loanAmount: Float = 0.0F
    var loanInterest: Float = 0.0F
    var loanMonthPayment: Float = 0.0F
    var ageWhenPayed: Int = 0

    fun calculateLoan(year: Int): Loan {
        val currentLoan: Loan = this
        val randomValues = List(1) { Random.nextInt(1, 4) } //Ränta

        currentLoan.loanInterest = randomValues[0].toFloat()
        currentLoan.ageWhenPayed = year
        currentLoan.loanMonthPayment = currentLoan.loanAmount / ( currentLoan.ageWhenPayed * 12)
        return currentLoan

    }
}