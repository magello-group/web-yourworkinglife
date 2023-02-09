import kotlinx.serialization.Serializable

@Serializable
data class Person (val id: Int) {
    var name: String = ""
    var age: Int = 0
    var pension: Float = 0.0F
    var luck: Boolean = false
    var magellit: Boolean = false
    var sick: Boolean = false
    var healthInsurance: Boolean = false
    var accommodation: Boolean = false
    var union: Union = Union(id)
    var insurance: Insurance = Insurance(id,"Olycksfallsförsäkring")
    var house: House = House(id, "")
    var professions: List<Profession> = emptyList()
    var employees: List<Employee> = emptyList()
    var accounts: List<Account> = emptyList()
    var countWorkMonth: Int = 0
    var loan: Boolean = false
    var blancoLoan: Loan = Loan(id, "Blanco")

    fun showPersonAccomodation(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyList = storyList.plus(
            Message(
                storyId,
                "----------------------- Årlig summering boende ------------------------",
                "deepskyblue",
                ""
            )
        )
        storyId += 1

        if (this.house.houseAmount.toInt() > 0) {
            message = Message(
                storyId,
                "Värde: ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.actualHouseAmount = this.house.houseAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        }

        if (this.house.loan) {
            message = Message(
                storyId,
                "Lån: ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.actualLoanAmount = this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "Låneränta: ${this.house.houseLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                    "",
                    ""
                )
            )

            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "Avbetalning lån: ${this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                    "",
                    ""
                )
            )
            storyId += 1
        }

        if (this.house.houseType == "hyresrätt") {
            message = Message(
                storyId,
                "Hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.actualHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        } else {
            message = Message(
                storyId,
                "Månadskostnad: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.actualHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        }

        return storyList
    }

    fun showPersonGetHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du behöver ta ett lån på ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK 😅",
                "",
                "blinkingRed"
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Låneränta: ${this.house.houseLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "hotpink",
                ""
            )
        )
        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Avbetalning lån: ${this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()}SEK.",
                "hotpink",
                ""
            )
        )

        return storyList
    }
    fun showPersonLoanReady(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Wow!! Du har betalt av ditt lån.",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showPersonGetBlancoLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du tar ett blancolån på ${this.blancoLoan.loanAmount.toInt().formatDecimalSeparator()} SEK 😅",
                "lavender",
                ""
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Låneränta: ${this.blancoLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "lavender",
                ""
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Avbetalning lån: ${this.blancoLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "lavender",
                ""
            )
        )

        return storyList
    }

    fun showPersonLuck(messageList: List<Message>, messageId: Int, eventText: String): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Härligt! " + eventText,
                "",
                "blinking"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Njut av dom små stunderna och glöm sorger och besvär 😇",
                "",
                "blinking"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Så håller du dig frisk! 🍀",
                "",
                "blinking"
            )
        )

        return storyList
    }

    fun showPersonMagellit(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Wow!! Du är träffad av en magellit 🚀 då blir du inte varslad.",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showPersonAccomodationSold(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Dags att sälja det gamla boendet! du får ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du måste skuldsanera och säljer ditt boende! du får ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showPersonHouseBought(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Huset kostar: ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Månadskostnad hus ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showPersonDepartmentBought(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Bostadsrätten kostar ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showPersonAccomodationHire(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        return storyList
    }

    fun showPersonAccomodationShift(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du byter hyresrätt.",
                "",
                "blinking"
            )
        )

        return storyList
    }

    fun showWorkingLife(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message = Message(
            storyId,
            "----------------------- Du är ${age} år ------------------------",
            "deepskyblue",
            ""
        )
        message.age = age
        storyList = storyList.plus(message)

        storyId += 1

        if (age == this.age) {

            storyList = storyList.plus(
                Message(
                    storyId,
                    "Programmet använder genomsnittskostnader, ",
                    "",
                    ""
                )
            )
            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "för mat är ca 5000 - 10000, skatt 30% och",
                    "",
                    ""
                )
            )
            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "för löneökning används ca 0.5% - 9,8% ",
                    "",
                    ""
                )
            )
            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "------------------------------------------------------------------------",
                    "deepskyblue",
                    ""
                )
            )
        }

        return storyList
    }

    fun registerPerson()
    {
        //Insert in database
    }

    fun updatePerson()
    {
        //Update in database
    }

    fun getPerson(){
        //Select person status
    }
}