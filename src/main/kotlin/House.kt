import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class House(val personId: Int, val houseType: String)
{
    var isMortgage: Boolean = false
    var houseLoan: Loan = Loan(personId, "Bostad")
    var houseAmount: Float = 0.0F
    var houseMonthPayment: Float = 0.0F

    fun raiseTheRent(): Float {
        var randomValues = List(1) { Random.nextInt(1, 4) }

        return this.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)
    }

    fun registerAccount() {
        //Insert account in db
    }

    fun updateAccount() {
        //Update account in db
    }

    fun getAccount() {
        //Select account from db
    }
}