import kotlinx.serialization.Serializable

@Serializable
data class Employee( val employeeId: Int )
{
    var title: String = ""
    var firstSalary: Float = 0.0F
    var currentSalary: Float = 0.0F
    var countWorkMonth: Int = 0
    var sickSalary: Float = 0.0F
    var countSickMonth: Int = 0

    fun showEmployeeSalary(salaryincrease: Float, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        message = Message(
            storyId,
            "Du jobbar som ${this.title}.",
            "",
            ""
        )
        message.status.profession = this.title
        storyList = storyList.plus(message)

        storyId += 1

        if (this.currentSalary == 4180.0F) {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Utan inkomst får du försörjningsstöd på 4180 SEK per månad.",
                    "",
                    ""
                )
            )

            storyId += 1

        } else {

            if (salaryincrease > 0.0F) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du har fått löneökning på ${
                            salaryincrease.toInt().formatDecimalSeparator()
                        }% 💪",
                        "",
                        ""
                    )
                )
            }

            storyId += 1

            message = Message(
                storyId,
                "Lön: ${
                    this.currentSalary.toInt().formatDecimalSeparator()
                } SEK per månad.",
                "",
                ""
            )
            message.status.employeeSalary = this.currentSalary.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        }

        return storyList
    }

    fun showEmployeeSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                "Du får en sjukpenning på ${this.sickSalary.toInt().formatDecimalSeparator()} SEK.",
                "orange",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeNoSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                "Tyvärr!! din sjukskrivning godkänns inte och du får ingen sjukpenning.",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showEmployeeCountSickMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                "Du är sjukskriven i ${this.countSickMonth} månader.",
                "orange",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeCountWorkMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        if (this.countWorkMonth < 0) {
            storyList = storyList.plus(
                Message(
                    messageId,
                    "Tråkigt du kunde inte jobba alls detta år, kvar är ${
                        this.countWorkMonth.formatDecimalSeparator()
                    } månader.",
                    "orange",
                    ""
                )
            )
        } else if (this.countWorkMonth < 12) {
                storyList = storyList.plus(
                    Message(
                        messageId,
                        "Tråkigt du kunde bara jobba ${
                            this.countWorkMonth.formatDecimalSeparator()
                        } månader.",
                        "orange",
                        ""
                    )
                )
        } else {
            storyList = storyList.plus(
                Message(
                    messageId,
                    "Du jobbade heltid ${
                        this.countWorkMonth.formatDecimalSeparator()
                    } månader.",
                    "hotpink",
                    ""
                )
            )
        }
        return storyList
    }

    fun registerWork() {
        //Insert work in db
    }

    fun updateWork() {
        //Update work in db
    }

    fun getWork() {
        //Select work information
    }
}