import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class House(val personId: Int, val houseType: String)
{
    var isMortgage: Boolean = false
    var houseLoan: Loan = Loan(personId, houseType)
    var houseAmount: Float = 0.0F
    var houseMonthPayment: Float = 0.0F
    var description: String = ""

    fun raiseTheRent(): Float {
        val randomValues = List(1) { Random.nextInt(1, 2) }

        return this.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)
    }
}