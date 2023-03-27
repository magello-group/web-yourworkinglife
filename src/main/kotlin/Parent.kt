import kotlinx.serialization.Serializable

@Serializable
data class Parent (val personId: Int) {
    //val link = "https://www.forsakringskassan.se/privatperson/foralder/vard-av-barn-vab"
    val incomePercentage: Float = 0.765F
    val maxSalary: Float = 43750.0F
    val maxIncome: Float = 33480.0F
    var familySalary: Float = 0.0F
    var familyMonth = 6
    var countBabies = 0
    var countFamilyMonth = 0

    fun getIncome(salary: Float): Float {

        return if (salary < maxSalary)
            salary * incomePercentage
        else
            maxIncome
    }

    fun costBabies(): Float {
        var cost = 0.0F

        for (baby in 1..this.countBabies) {
            cost += 2500.0F * 12.0F
        }

        return cost
    }

    fun showParent(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message

        if (this.countBabies == 1) {
            storyId += 1
            message = Message(
                storyId,
                "Stort grattis!! du har fått barn!",
                "",
                "blinking"
            )
            message.status.countBabies = this.countBabies.toString()
            storyList = storyList.plus(message)

        } else {
            storyId += 1
            message = Message(
                storyId,
                "Stort grattis!! du har fått ett till barn och du har nu ${ this.countBabies } barn!",
                "",
                "blinking"
            )
            message.status.countBabies = this.countBabies.toString()
            storyList = storyList.plus(message)
        }

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du får en föräldrarpenningen på ${ this.familySalary.toInt().formatDecimalSeparator() } kr i 6 månader.",
                "hotpink",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Genomsnittskostnad för barn är 2500 SEK per månad.",
                "hotpink",
                ""
            )
        )

        return storyList
    }

    fun showVAB(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Oj! ditt barn är sjukt och du VAB:ar ${ this.countFamilyMonth } månader.",
                "orange",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du får en föräldrarpenningen på ${ this.familySalary.toInt().formatDecimalSeparator() } SEK.",
                "hotpink",
                ""
            )
        )

        return storyList
    }
}