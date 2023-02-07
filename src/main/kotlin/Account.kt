import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Account( val accountId: Int, val accountType: String) {
    var amount: Float = 0.0F
    var messageMilgon1: Boolean = true
    var messageMilgon5: Boolean = true
    var messageMilgon10: Boolean = true
    var messageMilgon20: Boolean = true


    fun showDepotAmount(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Härligt! du får värdepapper med ett värde på ${ this.amount.toInt().formatDecimalSeparator() } SEK 🤑" ,
                "",
                "blinking"
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
                "Oj, du får avgångsvederlag på ${this.amount.toInt().formatDecimalSeparator()} 🤑",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showAccountAmount(year: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.accountType == "lönekonto") {
            if (this.amount.toInt() > 20000000.0F && this.messageMilgon20) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "★ Sjukt galet!, vad ska du göra med 20 miljoner???",
                        "",
                        "blinking"
                    )
                )
                this.messageMilgon20 = false
            } else if (this.amount.toInt() > 10000000.0F && this.messageMilgon10) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "★ Galet!, du har tjänat ihop över 10 miljoner SEK på ${year.formatDecimalSeparator()} år!!!",
                        "",
                        "blinking"
                    )
                )
                this.messageMilgon10 = false
            } else if (this.amount.toInt() > 5000000.0F && this.messageMilgon5) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "★ Wow!, du har tjänat ihop över 5 miljoner SEK på ${year.formatDecimalSeparator()} år!",
                        "",
                        "blinking"
                    )
                )
                this.messageMilgon5 = false
            } else if (this.amount.toInt() > 1000000.0F && this.messageMilgon1) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "★ Wow!, du har tjänat ihop över 1 miljon SEK på ${year.formatDecimalSeparator()} år!",
                        "",
                        "blinking"
                    )
                )
                this.messageMilgon1 = false
            } else {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "----------------------- Summa konton ------------------------",
                        "deepskyblue",
                        ""
                    )
                )

                if (this.amount.toInt() < 0.0F) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😒",
                            "",
                            ""
                        )
                    )
                } else {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😀",
                            "",
                            ""
                        )
                    )
                }
            }
        } else if (this.accountType == "depå") {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Depå: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
            )
        } else if (this.accountType == "pensionskonto") {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Pensionskonto: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
            )
        } else if (this.accountType == "skatt") {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Betald skatt: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
            )
        }

        return storyList
    }

    fun showAccountCost(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.amount.toInt() < 0.0F) {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK (efter kostnadsavdrag) 😒",
                    "",
                    "blinkingRed"
                )
            )
        } else {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK (efter kostnadsavdrag) 😅",
                    "",
                    ""
                )
            )
        }
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