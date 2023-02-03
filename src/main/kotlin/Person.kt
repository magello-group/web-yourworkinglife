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

        storyList = storyList.plus(
            Message(
                storyId,
                "Ditt boendet är värt ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Ditt boendet kostar i månaden ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
        )

        return storyList
    }

    fun showPersonHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du har ett lån på ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK med ränta ${this.house.houseLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "",
                ""
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Du betalar av lånet med ${this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden.",
                "",
                ""
            )
        )

        return storyList
    }

    fun showPersonGetHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du behöver ta ett lån på ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK med ränta ${this.house.houseLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "",
                "blinking"
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Varje månad avbetalar du ${this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden.",
                "hotpink",
                ""
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
                "Du tar ett blancolån på ${this.blancoLoan.loanAmount.toInt().formatDecimalSeparator()} SEK med ränta ${this.blancoLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "lavender",
                ""
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Du betalar av blancolånet med ${this.blancoLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden.",
                "lavender",
                ""
            )
        )

        return storyList
    }

    fun showPersonLuck(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Grattis du känner dig lycklig!",
                "",
                "blinking"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Njut av dom små stunderna och glöm sorger och besvär.",
                "",
                "blinking"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Så håller du dig frisk!.",
                "",
                "blinking"
            )
        )

        return storyList
    }

    fun showPersonMagellit(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Wow!! Du är träffad av en magellit! då finns det liten chans att bli varslad.",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showPersonAccomodationSold(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

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

    fun showPersonHouseBought(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Huset kostar ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Huset kostar i månaden ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
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
                "Hyran är på ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} i månaden.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showPersonAccomodationHire(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Hyran är på ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden.",
                "hotpink",
                ""
            )
        )

        return storyList
    }

    fun showPersonAccomodationShift(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

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
/*
        storyList = storyList.plus(
            Message(
                storyId,
                "Ny rad",
                "Break",
                ""
            )
        )

        storyId += 1

 */
        storyList = storyList.plus(
            Message(
                storyId,
                "--------------------------- Det går ${age - this.age + 1} år ----------------------------",
                "deepskyblue",
                ""
            )
        )
        storyId += 1

        if (age == this.age) {

            storyList = storyList.plus(
                Message(
                    storyId,
                    "Genomsnittskostnad för mat är ca 3000 SEK per månad (slump 5000 - 10000).",
                    "",
                    ""
                )
            )
            storyId += 1

            storyList = storyList.plus(
                Message(
                    storyId,
                    "Löneökning är i genomsnitt 4.9% (slump 0.5% - 9,8%).",
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