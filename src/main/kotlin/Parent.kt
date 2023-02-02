import kotlinx.serialization.Serializable
import kotlin.math.max

@Serializable
data class Parent (val personId: Int) {
    val link: String = "https://www.forsakringskassan.se/privatperson/foralder/vard-av-barn-vab"
    val incomePercentage: Float = 0.765F
    val maxSalary: Float = 43750.0F
    val maxIncome: Float = 33480.0F
    var familySalary: Float = 0.0F
    var familyMonth = 6
    var countBabies = 0
    var countFamilyMonth = 0

    fun getIncome(salary: Float): Float {
        var sum: Float = 0.0F
        sum = if (salary < maxSalary)
            salary * incomePercentage
        else
            maxIncome

        return sum
    }

    fun showParent(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.countBabies > 0) {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "När du är $age år får du barn!",
                    "",
                    ""
                )
            )
        } else {
            storyList = storyList.plus(
                Message(
                    storyId,
                    "När du är $age år får du ännu fler barn!",
                    "",
                    ""
                )
            )
        }
        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Nu har du ${ this.countBabies } barn.",
                "",
                ""
            )
        )
        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du får en föräldrarpenningen på ${ this.familySalary.toInt().formatDecimalSeparator() } kr i 6 månader.",
                "",
                ""
            )
        )
        return storyList
    }

    fun showVAB(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "När du är $age år VAB:ar du ${ this.countFamilyMonth } månader.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du får en föräldrarpenningen på ${ this.familySalary.toInt().formatDecimalSeparator() } SEK.",
                "",
                ""
            )
        )

        return storyList
    }


    fun registerVAB() {
        //Insert union in db
    }

    fun updateVAB() {
        //Update union in db
    }

    fun getVAB() {
        //Select union information
    }
}