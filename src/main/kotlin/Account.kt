import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Account( val accountId: Int, val accountType: String) {
    var amount: Float = 0.0F

    fun showDepotAmount(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "När du är $age år får du bonus på ${ this.amount.toInt().formatDecimalSeparator() } SEK!",
                "",
                ""
            )
        )
        return storyList
    }

    fun showSeverancePay(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "När du är $age år får du avgångsvederlag på ${this.amount.toInt().formatDecimalSeparator()}!",
                "",
                ""
            )
        )
        return storyList
    }

    fun showAccountAmount(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Kontosaldo för ${this.accountType}: ${ this.amount.toInt().formatDecimalSeparator() } SEK",
                "",
                ""
            )
        )
        return storyList
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