import kotlinx.serialization.Serializable

@Serializable
data class Loan( val personId: Int, val loanType: String) {

    var loanAmount: Float = 0.0F
    var loanInterest: Float = 0.0F
    var loanMonthPayment: Float = 0.0F
}