import kotlinx.serialization.Serializable

@Serializable
data class Person (val id: Int) {
    var name: String = ""
    var age: Int = 0
    var startWorkingAge: Int = 0
    var pensionAge: Int = 0
    var pension: Float = 0.0F
    var union: Union = Union(id)
    var insurance: Insurance = Insurance(id,"Olycksfallsförsäkring")
    var house: House = House(id, "")
    var professions: List<Profession> = emptyList()
    var employees: List<Employee> = emptyList()
    var accounts: List<Account> = emptyList()
    var events: List<Event> = emptyList()
    var countWorkMonth: Int = 0
    var blancoLoan: Loan = Loan(id, "Blanco")
    var isMortgage: Boolean = false
    var isHappy: Boolean = false
    var isMagellit: Boolean = false
    var isSick: Boolean = false
    var isHealthInsurance: Boolean = false
    var isAccommodation: Boolean = false
    var isDepressed: Boolean = false

    var countCats: Int = 0
    var countStrong: Int = 0
    var countFriends: Int = 0
    var countAlone: Int = 0
    var countDogs: Int = 0
    var countFishes: Int = 0
    var countParties: Int = 0
    var countHorses: Int = 0
    var countMoney: Int = 0
    var countCars: Int = 0
    var countBikes: Int = 0

    fun costHobbies(): Float {
        var cost: Float = 0.0F

        for (hobby in 1..this.countCats) {
             cost += 1000.0F * 12.0F
        }
        for (hobby in 1..this.countDogs) {
            cost += 2000.0F * 12.0F
        }
        for (hobby in 1..this.countParties) {
            cost += 3000.0F * 12.0F
        }
        for (hobby in 1..this.countHorses) {
            cost += 6000.0F * 12.0F
        }
        for (hobby in 1..this.countCars) {
            cost += 5000.0F * 12.0F
        }
        for (hobby in 1..this.countBikes) {
            cost += 3000.0F * 12.0F
        }
        return cost
    }
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
            message.status.houseAmount = this.house.houseAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        }

        if (this.house.isMortgage) {
            message = Message(
                storyId,
                "Lån: ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.status.houseLoanAmount = this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
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
            message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
        } else {
            message = Message(
                storyId,
                "Månadskostnad: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
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

    fun showPersonNoHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Ditt lån blev avslaget så det blev inget boende 🤥",
                "",
                "blinkingRed"
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

    fun showPersonLuck(messageList: List<Message>, messageId: Int, event: Event): List<Message> {
        var storyList = messageList
        var storyId = messageId

        val message = Message(
            storyId,
            "Härligt! " + event.eventText,
            "",
            "blinking"
        )
        when (event.objectType) {
            "cat" -> { message.status.countCats = this.countCats.toString() }
            "dog" -> { message.status.countDogs = this.countDogs.toString() }
            "horse"  -> { message.status.countHorses = this.countHorses.toString() }
            "car"  -> { message.status.countCars = this.countCars.toString() }
            "bike"  -> { message.status.countBikes = this.countBikes.toString() }
            "strong"  -> { message.status.countStrong = this.countStrong.toString() }
            "alone"  -> { message.status.countAlone = this.countAlone.toString() }
            "money"  -> { message.status.countMoney = this.countMoney.toString() }
            "party"  -> { message.status.countParties = this.countParties.toString() }
            "friend"  -> { message.status.countFriends = this.countFriends.toString() }
            "fish"  -> { message.status.countFishes = this.countFishes.toString() }

        }

        storyList = storyList.plus(message)
/*
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


 */
        return storyList
    }

    fun showPersonMagellit(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                "Wow!! Du är träffad av en magellit 🚀 då blir du inte varslad.",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showPersonAccomodationSold(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                "Dags att sälja det gamla boendet! du får ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )
        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
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

        storyList = storyList.plus(
            Message(
                messageId,
                "Hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        return storyList
    }

    fun showPersonAccomodationShift(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
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
        message.status.age = age.toString()
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