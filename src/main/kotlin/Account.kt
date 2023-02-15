import kotlinx.serialization.Serializable

@Serializable
data class Account( val accountId: Int, val accountType: String) {
    var amount: Float = 0.0F
    var isMessageMiljon1: Boolean = true
    var isMessageMiljon5: Boolean = true
    var isMessageMiljon10: Boolean = true
    var isMessageMiljon20: Boolean = true


    fun showDepotAmount(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val message = Message(
            messageId,
            "Härligt! du får värdepapper med ett värde på ${ this.amount.toInt().formatDecimalSeparator() } SEK 🤑" ,
            "",
            "blinking"
        )
        message.status.accountDepotAmount = this.amount.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showSeverancePay(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
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
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() > 20000000.0F && this.isMessageMiljon20) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "★ Sjukt galet!, vad ska du göra med 20 miljoner???",
                            "",
                            "blinking"
                        )
                    )
                    this.isMessageMiljon20 = false

                    storyId += 1
                } else if (this.amount.toInt() > 10000000.0F && this.isMessageMiljon10) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "★ Galet!, du har tjänat ihop över 10 miljoner SEK på ${year.formatDecimalSeparator()} år!!!",
                            "",
                            "blinking"
                        )
                    )
                    this.isMessageMiljon10 = false

                    storyId += 1
                } else if (this.amount.toInt() > 5000000.0F && this.isMessageMiljon5) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "★ Wow!, du har tjänat ihop över 5 miljoner SEK på ${year.formatDecimalSeparator()} år!",
                            "",
                            "blinking"
                        )
                    )
                    this.isMessageMiljon5 = false

                    storyId += 1
                } else if (this.amount.toInt() > 1000000.0F && this.isMessageMiljon1) {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "★ Wow!, du har tjänat ihop över 1 miljon SEK på ${year.formatDecimalSeparator()} år!",
                            "",
                            "blinking"
                        )
                    )

                    storyId += 1
                } else {
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "----------------------- Summa konton ------------------------",
                            "deepskyblue",
                            ""
                        )
                    )

                    storyId += 1

                    if (this.amount.toInt() < 0.0F) {
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😒",
                            "",
                            ""
                        )
                        message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)

                        storyId += 1

                    } else {
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😀",
                            "",
                            ""
                        )
                        message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)

                        storyId += 1
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
                message.status.accountDepotAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)

                storyId += 1
            }

            "pensionskonto" -> {
                message = Message(
                        storyId,
                        "Pensionskonto: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                        "",
                        ""
                    )
                message.status.accountPensionAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)

                storyId += 1
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

                storyId += 1
            }
        }

        return storyList
    }

    fun showAccountCost(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

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

                    storyId += 1
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

                    storyId += 1
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

                    storyId += 1
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
                    storyId += 1
                }
            }
        }

        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
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