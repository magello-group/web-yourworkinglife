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
    var luckEvents: List<Event> = emptyList()
    var countWorkMonth: Int = 0
    private var blancoLoan: Loan = Loan(id, "Blanco")
    //var isMortgage: Boolean = false
    var isHappy: Boolean = false
    var isMagellit: Boolean = false
    var isSick: Boolean = false
    var isHealthInsurance: Boolean = false
    var isAccommodation: Boolean = false
    //var isDepressed: Boolean = false

    var cats: List<Hobby> = emptyList()
    var dogs: List<Hobby> = emptyList()
    var horses: List<Hobby> = emptyList()
    var cars: List<Hobby> = emptyList()
    var bikes: List<Hobby> = emptyList()
    var parties: List<Hobby> = emptyList()
    var boats: List<Hobby> = emptyList()
    var countFriends: Int = 0
    var countWalking: Int = 0
    var countStrong: Int = 0
    var countFishing: Int = 0
    var countLove: Int = 0
    var isLove: Boolean = false

    fun findLuck(hobby: String): Boolean {
        var love = false

        for(luck in this.luckEvents) {
            if (hobby == luck.objectType)
                love = true
        }

        return love
    }

    fun costHobbies(): Float {
        var cost = 0.0F

        if (this.cats.isNotEmpty()) {
            cost += this.cats.size * this.cats[0].costHobby() * 12.0F
        }

        if (this.dogs.isNotEmpty()) {
            cost += this.dogs.size * this.dogs[0].costHobby() * 12.0F
        }

        if (this.horses.isNotEmpty()) {
            cost += this.horses.size * this.horses[0].costHobby() * 12.0F
        }

        if (this.bikes.isNotEmpty()) {
            cost += this.bikes.size * this.bikes[0].costHobby() * 12.0F
        }

        if (this.cars.isNotEmpty()) {
            cost += this.cars.size * this.cars[0].costHobby() * 12.0F
        }

        if (this.boats.isNotEmpty()) {
            cost += this.boats.size * this.boats[0].costHobby() * 12.0F
        }

        if (this.parties.isNotEmpty()) {
            cost += this.parties.size * this.parties[0].costHobby() * 12.0F
        }

        return cost
    }

    fun isHappyPerson(): Boolean {
        var isLuck = false

        for (event in this.luckEvents) {
            when (event.objectType) {
                "cat" -> {
                    if (this.cats.isNotEmpty())
                        isLuck = true
                }

                "strong" -> {
                    if (this.countStrong > 0)
                        isLuck = true
                }

                "friend" -> {
                    if (this.countFriends > 0)
                        isLuck = true
                }

                "walk" -> {
                    if (this.countWalking > 0)
                        isLuck = true
                }

                "dog" -> {
                    if (this.dogs.isNotEmpty())
                        isLuck = true
                }

                "fish" -> {
                    if (this.countFishing > 0)
                        isLuck = true
                }

                "party" -> {
                    if (this.parties.isNotEmpty())
                        isLuck = true
                }

                "horse" -> {
                    if (this.horses.isNotEmpty())
                        isLuck = true
                }

                "boat" -> {
                    if (this.boats.isNotEmpty())
                        isLuck = true
                }

                "car" -> {
                    if (this.cars.isNotEmpty())
                        isLuck = true
                }

                "bike" -> {
                    if (this.bikes.isNotEmpty())
                        isLuck = true
                }
                "love" -> {
                    if (this.countLove > 0)
                        isLuck = true
                }
            }
        }

        return isLuck
    }

    fun ShowDeadHobbies(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        for (cat in this.cats) {
            cat.age += 1
            if (cat.age > cat.maxAge) {
                this.cats = this.cats.minus(cat)

                storyId += 1
                message = Message(
                storyId,
                "Sorgligt, en av dina katter är gammal och fick avlivas",
                "",
                "blinking"
                )
                message.status.countCats = this.cats.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (dog in this.dogs) {
            dog.age += 1
            if (dog.age > dog.maxAge) {
                dogs = dogs.minus(dog)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, en av dina hundar är gammal och fick avlivas",
                    "",
                    "blinking"
                )
                message.status.countDogs = this.dogs.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (horse in this.horses) {
            horse.age += 1
            if (horse.age > horse.maxAge) {
                horses = horses.minus(horse)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, en av dina hästar är gammal och fick avlivas",
                    "",
                    "blinking"
                )
                message.status.countHorses = this.horses.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (car in this.cars) {
            car.age += 1
            if (car.age > car.maxAge) {
                cars = cars.minus(car)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, en av dina bilar måste skrotas",
                    "",
                    "blinking"
                )
                message.status.countCars = this.cars.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (bike in this.bikes) {
            bike.age += 1
            if (bike.age > bike.maxAge) {
                bikes = bikes.minus(bike)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, en av dina motorcyklar måste skrotas",
                    "",
                    "blinking"
                )
                message.status.countBikes = this.bikes.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (boat in this.boats) {
            boat.age += 1
            if (boat.age > boat.maxAge) {
                boats = boats.minus(boat)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, en av dina segelbåtar sjönk ned i havet",
                    "",
                    "blinking"
                )
                message.status.countBoats = this.boats.size.toString()
                storyList = storyList.plus(message)
            }
        }

        for (party in this.parties) {
            party.age += 1
            if (party.age > party.maxAge) {
                parties = parties.minus(party)

                storyId += 1
                message = Message(
                    storyId,
                    "Sorgligt, festen tog slut",
                    "",
                    "blinking"
                )
                message.status.countParties = this.parties.size.toString()
                storyList = storyList.plus(message)
            }
        }

        return storyList
    }

    fun showPersonAccomodation(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "----------------------- Årlig Summering boende ------------------------",
                "deepskyblue",
                ""
            )
        )

        if (this.house.houseAmount.toInt() > 0) {
            storyId += 1
            message = Message(
                storyId,
                "Värde: ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.status.houseAmount = this.house.houseAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        if (this.house.isMortgage) {
            storyId += 1
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
            message = Message(
                storyId,
                "Räntebelopp per månad: ${this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()} SEK.",
                "",
                ""
            )
            message.status.interestMonthPayment = this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
            message = Message(
                storyId,
                "Avbetalning lån per månad: ${
                    this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
                } SEK.",
                "",
                ""
            )
            message.status.loanMonthPayment = this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        storyId += 1
        message = Message(
            storyId,
            "Månadskostnad/Hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
            "",
            ""
        )
        message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)


        return storyList
    }

    fun showTherapist(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du är deprimerad och sitter med en samtalsterapeut.",
                "",
                "blinkingPink"
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Vad blir du glad av? frågar hon.",
                "",
                "blinkingPink"
            )
        )

        return storyList
    }

    fun showPersonGetHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Du behöver ta ett lån på ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK 😅",
            "",
            "blinkingPink"
        )
        message.status.houseLoanAmount = this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

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
        message = Message(
            storyId,
            "Räntebelopp per månad: ${this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()}} SEK.",
            "",
            "hotpink"
        )
        message.status.interestMonthPayment = this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Avbetalning lån: ${this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()}SEK.",
            "hotpink",
            ""
        )
        message.status.loanMonthPayment = this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)


        return storyList
    }

    fun showPersonNoHouseLoan(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Ditt lån blev avslaget så det blev inget boende 🤥",
            "",
            "blinking"
        )
        message.status.houseLoanAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Du får låna en soffa att sova på 🤥",
            "",
            "blinking"
        )
        message.status.interestMonthPayment = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Avbetalning lån per månad: 0 SEK.",
            "",
            ""
        )
        message.status.loanMonthPayment = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Värde hus: 0 SEK.",
            "hotpink",
            ""
        )
        message.status.houseAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Månadskostnad hus: 0 SEK.",
            "",
            ""
        )
        message.status.houseHireAmount = "0"
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonLoanReady(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Wow!! Du har betalt av ditt lån.",
            "",
            "blinking"
        )
        message.status.houseLoanAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Låneränta: 0%",
                "",
                ""
            )
        )

        storyId += 1
        message = Message(
            storyId,
            "Räntebelopp per månad: 0 SEK.",
            "",
            ""
        )
        message.status.interestMonthPayment = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Avbetalning lån per månad: 0 SEK.",
            "",
            ""
        )
        message.status.loanMonthPayment = "0"
        storyList = storyList.plus(message)


        return storyList
    }

    fun showPersonLuck(messageList: List<Message>, messageId: Int, event: Event): List<Message> {
        var storyList = messageList
        var storyId = messageId
        val message: Message

        if (event.objectType == "love" && this.countLove > 1) {
            storyId += 1
            message = Message(
                storyId,
                "Kärleken höll inte men du finner en ny 💕💕💕💕💕 love is in the air",
                "",
                "blinking"
            )

        } else {

            storyId += 1
            message = Message(
                storyId,
                event.eventText,
                "",
                "blinking"
            )
        }

        when (event.objectType) {
            "cat" -> { message.status.countCats = this.cats.size.toString() }
            "dog" -> { message.status.countDogs = this.dogs.size.toString() }
            "horse"  -> { message.status.countHorses = this.horses.size.toString() }
            "car"  -> { message.status.countCars = this.cars.size.toString() }
            "bike"  -> { message.status.countBikes = this.bikes.size.toString() }
            "boat"  -> { message.status.countBoats = this.boats.size.toString() }
            "party"  -> { message.status.countParties = this.parties.size.toString() }
            "friend"  -> { message.status.countFriends = this.countFriends.toString() }
            "fish"  -> { message.status.countFishing = this.countFishing.toString() }
            "strong"  -> { message.status.countStrong = this.countStrong.toString() }
            "walk"  -> { message.status.countWalking = this.countWalking.toString() }
            "love"  -> { message.status.countLoves = this.countLove.toString() }

        }
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonMagellit(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

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

    fun noMoneyToShop(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Åh nej! du har inga pengar att köpa ett nytt boende 😒",
                "",
                "blinking"
            )
        )
        return storyList
    }

    fun showPersonAccomodationSold(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Dags att sälja det gamla boendet! du får ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
            "hotpink",
            ""
        )
        message.status.houseAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Månadskostnad hus: 0 SEK.",
            "",
            ""
        )
        message.status.houseHireAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Lån: 0 SEK",
            "",
            ""
        )
        message.status.houseLoanAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Räntebelopp per månad: 0 SEK",
            "",
            ""
        )
        message.status.interestMonthPayment = "0"
        storyList = storyList.plus(message)

        storyId += 1
        message = Message(
            storyId,
            "Avbetalning lån per månad: 0 SEK.",
            "",
            ""
        )
        message.status.loanMonthPayment = "0"
        storyList = storyList.plus(message)

        return storyList
    }

    fun showSkuldsanering(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Du måste skuldsanera och säljer ditt boende! du får ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
            "hotpink",
            ""
        )
        message.status.houseAmount = "0"
        storyList = storyList.plus(message)

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Istället skaffar du en hyresrätt.",
                "hotpink",
                ""
            )
        )

        if (this.house.isMortgage) {
            storyId += 1
            message = Message(
                storyId,
                "Lån: 0 SEK.",
                "",
                ""
            )
            message.status.houseLoanAmount = "0"
            storyList = storyList.plus(message)

            storyId += 1
            message = Message(
                storyId,
                "Räntebelopp per månad: 0 SEK.",
                "",
                ""
            )
            message.status.interestMonthPayment = "0"
            storyList = storyList.plus(message)

            storyId += 1
            message = Message(
                storyId,
                "Avbetalning lån per månad: 0 SEK.",
                "",
                ""
            )
            message.status.loanMonthPayment = "0"
            storyList = storyList.plus(message)
        }

        storyId += 1
        message = Message(
            storyId,
            "Månadskostnad hus: 0 SEK.",
            "",
            ""
        )
        message.status.houseHireAmount = "0"
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonHouseBought(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        if (this.house.houseAmount.toInt() > 0) {
            storyId += 1
            message = Message(
                storyId,
                "Det nya huset kostar: ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.houseAmount = this.house.houseAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        if (this.house.isMortgage) {
            storyId += 1
            message = Message(
                storyId,
                "Lån: ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.houseLoanAmount = this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

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
            message = Message(
                storyId,
                "Räntebelopp per månad: ${this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.interestMonthPayment = this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
            message = Message(
                storyId,
                "Avbetalning lån per månad: ${
                    this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
                } SEK.",
                "hotpink",
                ""
            )
            message.status.loanMonthPayment = this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        storyId += 1
        message = Message(
            storyId,
            "Månadskostnad hus: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
            "hotpink",
            ""
        )
        message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonDepartmentBought(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        if (this.house.houseAmount.toInt() > 0) {
            storyId += 1
            message = Message(
                storyId,
                "Den nya bostadsrätten kostar:  ${this.house.houseAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.houseAmount = this.house.houseAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        if (this.house.isMortgage) {
            storyId += 1
            message = Message(
                storyId,
                "Lån: ${this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.houseLoanAmount = this.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

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
            message = Message(
                storyId,
                "Räntebelopp per månad: ${this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
            message.status.interestMonthPayment = this.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)

            storyId += 1
            message = Message(
                storyId,
                "Avbetalning lån per månad: ${
                    this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
                } SEK.",
                "hotpink",
                ""
            )
            message.status.loanMonthPayment = this.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
            storyList = storyList.plus(message)
        }

        storyId += 1
        message = Message(
            storyId,
            "Hyra bostadsrätt: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
            "hotpink",
            ""
        )
        message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonAccomodationHire(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId
        var message: Message

        storyId += 1
        message = Message(
            storyId,
            "Det nya boendet hyra: ${this.house.houseMonthPayment.toInt().formatDecimalSeparator()} SEK.",
            "hotpink",
            ""
        )
        message.status.houseHireAmount = this.house.houseMonthPayment.toInt().formatDecimalSeparator()
        storyList = storyList.plus(message)

        return storyList
    }

    fun showPersonAccomodationShift(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Du lämnar hyresrätten du har.",
                "",
                "blinking"
            )
        )

        return storyList
    }

    fun showWorkingLife(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        val message = Message(
            storyId,
            "----------------------- Du är ${age} år ------------------------",
            "deepskyblue",
            ""
        )
        message.status.age = age.toString()
        storyList = storyList.plus(message)

        if (age == this.age) {
            storyId += 1
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
}