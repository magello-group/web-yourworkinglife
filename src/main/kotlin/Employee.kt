import kotlinx.serialization.Serializable

@Serializable
data class Employee( val employeeId: Int )
{
    var title: String = ""
    var firstSalary: Float = 0.0F
    var currentSalary: Float = 0.0F
    var salaryFixedPercentage: Float = 0.0F
    var salaryVariablePercentage: Float = 0.0F
    var countWorkMonth: Int = 0
    var sickSalary: Float = 0.0F
    var countSickMonth: Int = 0

    fun showEmployeeSalary(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du är $age år och som ${ this.title } tjänar du ${ this.currentSalary.toInt().formatDecimalSeparator() } SEK per månad.",
                "",
                ""
            )
        )
        return storyList
    }

    fun showEmployeeSickSalary(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du är försäkrad och får en sjukpenning på ${this.sickSalary.toInt().formatDecimalSeparator()}.",
                "",
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
                "Tyvärr blir din sjukskrivning avslagen och du får ingen sjukpenning.",
                "",
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
                "",
                ""
            )
        )
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