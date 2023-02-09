import kotlinx.serialization.Serializable

@Serializable
data class Account( val accountId: Int, val accountType: String) {
    var amount: Float = 0.0F
    var messageMilgon1: Boolean = true
    var messageMilgon5: Boolean = true
    var messageMilgon10: Boolean = true
    var messageMilgon20: Boolean = true


    fun showDepotAmount(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId
        val message = Message(
            storyId,
            "Härligt! du får värdepapper med ett värde på ${ this.amount.toInt().formatDecimalSeparator() } SEK 🤑" ,
            "",
            "blinking"
        )
        message.actualDepotAmount = this.amount.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showSeverancePay(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

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
        val storyId = messageId
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
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
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😒",
                            "",
                            ""
                        )
                        message.actualSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)

                    } else {
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😀",
                            "",
                            ""
                        )
                        message.actualSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)
                    }
                }
            }

            "depå" -> {
                message = Message(
                    storyId,
                    "Depå: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
                message.actualDepotAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)
            }

            "pensionskonto" -> {
                message = Message(
                        storyId,
                        "Pensionskonto: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                        "",
                        ""
                    )
                message.actualPension = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)
            }

            "skatt" -> {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Betald skatt: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                        "",
                        ""
                    )
                )
            }
        }

        return storyList
    }

    fun showAccountCost(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Lönekonto: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter kostnadsavdrag) 😒",
                            "",
                            "blinkingRed"
                        )
                    )
                } else {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Lönekonto: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter kostnadsavdrag)",
                            "",
                            ""
                        )
                    )
                }
            }

            "noakassa" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter kostnadsavdrag) 😒",
                            "grey",
                            ""
                        )
                    )
                } else {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter kostnadsavdrag) 😅",
                            "grey",
                            ""
                        )
                    )
                }
            }
        }

        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du måste skuldsanera och säljer dina värdepapper och får: ${this.amount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
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