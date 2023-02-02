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
                "Boendet är värt ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK och kostar i månaden ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
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
                "Du tar ett lån på ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK med ränta ${this.house.houseLoan.loanInterest.toInt().formatDecimalSeparator()}%",
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

    fun showPersonGetBlancoLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du tar ett blancolån på ${this.blancoLoan.loanAmount.toInt().formatDecimalSeparator()} SEK med ränta ${this.blancoLoan.loanInterest.toInt().formatDecimalSeparator()}%",
                "",
                ""
            )
        )
        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Du betalar av blancolånet med ${this.blancoLoan.loanMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden.",
                "",
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
                "När du är $age år är du lycklig!",
                "",
                ""
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
                "När du är $age år blir du träffad av en Magellit!",
                "",
                ""
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
                "Du säljer det gamla boendet för ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK",
                "",
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
                "Du köper ett hus som kostar ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK",
                "",
                ""
            )
        )

        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Huset kostar i månaden ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK",
                "",
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
                "Du köper en bostadsrätt som kostar ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK",
                "",
                ""
            )
        )

        storyId += 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Hyran är på ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} i månaden",
                "",
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
                "Hyran är ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK i månaden",
                "",
                ""
            )
        )

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