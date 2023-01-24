import kotlinx.serialization.Serializable

@Serializable
data class Account( val personId: Int, val accountType: String )
{
    var amount: Double = 0.0

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