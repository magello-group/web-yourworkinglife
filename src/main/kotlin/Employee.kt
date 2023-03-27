import kotlinx.serialization.Serializable
import kotlin.math.roundToInt
import kotlin.random.Random

@Serializable
data class Employee( val employeeId: Int )
{
    var title: String = ""
    var firstSalary: Float = 0.0F
    var currentSalary: Float = 0.0F
    var countWorkMonth: Int = 0
    var sickSalary: Float = 0.0F
    var countSickMonth: Int = 0

    fun raiseTheSalary(isBoom: Boolean, age: Int): Float {

        val randomValues: List<Int> = if (isBoom) {
            List(1) { Random.nextInt(30, 50) }
        } else if ( age <= 50 ) {
            List(1) { Random.nextInt(10, 30) }
        } else {
            List(1) { Random.nextInt(5, 10) }
        }

        return (this.currentSalary * (randomValues[0].toFloat() / 1000.0F))
    }
    fun showEmployeeSalary(salaryincrease: Float, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message
        val roundoff = (salaryincrease * 100.0F).roundToInt() / 100.0F

        if (this.currentSalary == 4180.0F) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Utan inkomst får du försörjningsstöd på 4180 SEK per månad.",
                    "",
                    ""
                )
            )
        } else {

            if (roundoff * 100 > 0.0F) {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du har fått löneökning på ${
                            (roundoff * 100)
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
        }

        return storyList
    }

    fun showSeverancePay(amount: Float, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Oj, du får avgångsvederlag på ${amount.toInt().formatDecimalSeparator()} 🤑",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showEmployeeSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

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
        val storyId = messageId + 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Tyvärr!! din sjukskrivning godkänns inte 🤥",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showEmployeeCountSickMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

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
        val storyId = messageId + 1

        if (this.countWorkMonth < 0) {
            storyList = storyList.plus(
                Message(
                    storyId,
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
                        storyId,
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
                    storyId,
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

}