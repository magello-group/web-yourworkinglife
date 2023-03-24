import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Account( val accountId: Int, val accountType: String) {
    var amount: Float = 0.0F
    var isMessageMiljon1: Boolean = true
    var isMessageMiljon5: Boolean = true
    var isMessageMiljon10: Boolean = true
    var isMessageMiljon20: Boolean = true


    fun showDepotAmount(amount: Float, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        val message = Message(
            storyId,
            "Härligt! du får värdepapper med ett värde på ${ amount.toInt().formatDecimalSeparator() } SEK 🤑" ,
            "",
            "blinking"
        )
        message.status.accountDepotAmount = this.amount.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showAccountAmount(year: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() > 20000000.0F && this.isMessageMiljon20) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "★ Sjukt galet!, vad ska du göra med 20 miljoner???",
                        "",
                        "blinking"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                    this.isMessageMiljon20 = false
                } else if (this.amount.toInt() > 10000000.0F && this.isMessageMiljon10) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "★ Galet!, du har tjänat ihop över 10 miljoner SEK på ${year.formatDecimalSeparator()} år!!!",
                        "",
                        "blinking"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                    this.isMessageMiljon10 = false
               } else if (this.amount.toInt() > 5000000.0F && this.isMessageMiljon5) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "★ Wow!, du har tjänat ihop över 5 miljoner SEK på ${year.formatDecimalSeparator()} år!",
                        "",
                        "blinking"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                    this.isMessageMiljon5 = false
                } else if (this.amount.toInt() > 1000000.0F && this.isMessageMiljon1) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "★ Wow!, du har tjänat ihop över 1 miljon SEK på ${year.formatDecimalSeparator()} år!",
                        "",
                        "blinking"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                    this.isMessageMiljon1 = false
                } else {
                    /*
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "----------------------- Summa konton ------------------------",
                            "deepskyblue",
                            ""
                        )
                    )

                     */
                    if (this.amount.toInt() < 0.0F) {
                        storyId += 1
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😒",
                            "",
                            ""
                        )
                        message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)
                    } else {
                        storyId += 1
                        message = Message(
                            storyId,
                            "Lönekonto: ${this.amount.toInt().formatDecimalSeparator()} SEK 😀",
                            "",
                            ""
                        )
                        message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                        storyList = storyList.plus(message)
                    }
                }
            }

            "depå" -> {
                storyId += 1
                message = Message(
                    storyId,
                    "Depå: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
                message.status.accountDepotAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)
            }

            "pensionskonto" -> {
                storyId += 1
                message = Message(
                        storyId,
                        "Pensionskonto: ${this.amount.toInt().formatDecimalSeparator()} SEK",
                        "",
                        ""
                    )
                message.status.accountPensionAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)
            }

            "skatt" -> {
                storyId += 1
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
        var storyId = messageId
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter kostnadsavdrag) 😒",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                } else {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter kostnadsavdrag)",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)
                }
            }

            "noakassa" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
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
                    storyId += 1
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

    fun showAccountSoldHouse(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter husförsäljning) 😒",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                } else {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter husförsäljning)",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)
                }
            }

            "noakassa" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter husförsäljning) 😒",
                            "grey",
                            ""
                        )
                    )
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter husförsäljning) 😅",
                            "grey",
                            ""
                        )
                    )
                }
            }
        }

        return storyList
    }

    fun showAccountBoughtHouse(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message

        when (this.accountType) {
            "lönekonto" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter husköp) 😒",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)

                } else {
                    storyId += 1
                    message = Message(
                        storyId,
                        "Lönekonto: ${
                            this.amount.toInt().formatDecimalSeparator()
                        } SEK (efter husköp)",
                        "",
                        "blinkingPink"
                    )
                    message.status.accountSalaryAmount = this.amount.toInt().formatDecimalSeparator()
                    storyList = storyList.plus(message)
                }
            }

            "noakassa" -> {
                if (this.amount.toInt() < 0.0F) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter husköp) 😒",
                            "grey",
                            ""
                        )
                    )
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "utan A-kassa: ${
                                this.amount.toInt().formatDecimalSeparator()
                            } SEK (efter husköp) 😅",
                            "grey",
                            ""
                        )
                    )
                }
            }

            "depå" -> {
                storyId += 1
                message = Message(
                    storyId,
                    "Depå: ${this.amount.toInt().formatDecimalSeparator()} SEK (efter husköp) 😅",
                    "",
                    ""
                )
                message.status.accountDepotAmount = this.amount.toInt().formatDecimalSeparator()
                storyList = storyList.plus(message)
            }

        }

        return storyList
    }

    fun showSumAccountCost(messageList: List<Message>, messageId: Int, cost: Float): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Summa kostnader per månad: ${
                    cost.toInt().formatDecimalSeparator()
                } SEK",
                "orange",
                ""
            )
        )

        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1
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
}