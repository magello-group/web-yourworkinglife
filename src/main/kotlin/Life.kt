import kotlinx.serialization.Serializable
import kotlin.math.absoluteValue

@Serializable
data class Life ( val personId: Int) {
    var person = Person(personId)
    var firstStep = true
    var pensionStep = false
    var age = 0
    var parent = Parent(personId)
    var employee = Employee(1)
    var professionId: Int = 0
    var accountPension = Account(1, "pensionskonto")
    var accountSalary = Account(2, "lönekonto")
    var accountDepot = Account(3, "depå")
    var accountTax = Account(4, "skatt")
    var accountNoAkassa = Account(5, "noakassa")
    var messageList: List<Message> = emptyList()
    var lastMessageId: Int = 0
    var isPandemi = false
    var isBoom = false
    var isRecession = false
    var isQuestion = false
    var questionMessageId = 0
    var isNewProfession = false
    var professionMessageId = 0
    var firstSalary: Float = 0.0F
    var urlPension = "https://www.pensionsmyndigheten.se/forsta-din-pension/sa-fungerar-pensionen/sa-tjanar-du-in-till-din-pension"

    fun showPensionLife(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var currentAmount: Float

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du gick i pension vid en ålder på ${this.age} år.",
                "",
                "blinking"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du har jobbat i ${(person.countWorkMonth/12).formatDecimalSeparator()} år.",
                "",
                ""
            )
        )

        if (parent.countBabies > 0) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du är förälder till ${(parent.countBabies).formatDecimalSeparator()} barn.",
                    "",
                    ""
                )
            )
        }

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du har haft ${person.professions.size} yrken: ",
                "",
                ""
            )
        )

        for (profession in person.professions) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    profession.title,
                    "",
                    ""
                )
            )
        }

        currentAmount = employee.currentSalary - this.firstSalary

        if (currentAmount > 0) {

            currentAmount = (currentAmount.absoluteValue / employee.currentSalary) * 100

            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du har haft en löneökning på: ${currentAmount.toInt().formatDecimalSeparator()}%",
                    "",
                    ""
                )
            )
        } else {
            currentAmount = (currentAmount.absoluteValue / employee.currentSalary) * 100

            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du har haft en lönesänkning på: ${currentAmount.toInt().formatDecimalSeparator()}%",
                    "",
                    ""
                )
            )
        }

        return storyList
    }
}