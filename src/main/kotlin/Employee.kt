import kotlinx.serialization.Serializable

@Serializable
data class Employee( val employeeId: Int )
{
    var title: String = ""
    var firstSalary: Float = 0.0F
    var currentSalary: Float = 0.0F
    var maxSalary: Float = 0.0F
    var medianSalary: Float = 0.0F
    var countWorkMonth: Int = 0
    var sickSalary: Float = 0.0F
    var countSickMonth: Int = 0

    fun showEmployeeSalary(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.currentSalary == 4180.0F) {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Utan inkomst får du försörjningsstöd på 4180 SEK per månad.",
                    "",
                    ""
                )
            )
        } else {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du är $age år och tjänar som ${this.title} ${
                        this.currentSalary.toInt().formatDecimalSeparator()
                    } SEK per månad.",
                    "",
                    ""
                )
            )
        }
        return storyList
    }

    fun showEmployeeSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du får en sjukpenning på ${this.sickSalary.toInt().formatDecimalSeparator()} SEK.",
                "orange",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeNoSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Tyvärr!! din sjukskrivning godkänns inte och du får ingen sjukpenning.",
                "orange",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeCountSickMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du är sjukskriven i ${this.countSickMonth} månader.",
                "orange",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeCountWorkMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.countWorkMonth < 0) {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Tråkigt du kunde inte jobba alls detta år, kvar är ${
                        this.countWorkMonth.toInt().formatDecimalSeparator()
                    } månader.",
                    "orange",
                    ""
                )
            )
        } else if (this.countWorkMonth < 12) {
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Tråkigt du kunde bara jobba ${
                            this.countWorkMonth.toInt().formatDecimalSeparator()
                        } månader.",
                        "orange",
                        ""
                    )
                )
        } else {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Härligt! du jobbade heltid ${
                        this.countWorkMonth.toInt().formatDecimalSeparator()
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