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
    var accountWorkPension = Account(6, "jobbpensionskonto")
    var accountTaxPension = Account(7, "skattpensionskonto")
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
    var urlPension =
        "https://www.pensionsmyndigheten.se/forsta-din-pension/sa-fungerar-pensionen/sa-tjanar-du-in-till-din-pension"
    var countPoint: Int = 0
    var currentAmount: Float = 0.0F
    var currentCost: Float = 0.0F
    var sumPensionMonth: Float = 0.0F

    fun showPensionLife(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        val message = Message(
            storyId,
            "----------------------- Du är ${this.age} år ------------------------",
            "deepskyblue",
            ""
        )
        storyList = storyList.plus(message)

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du har nu gått i pension, det är nu livet börjar.",
                "",
                ""
            )
        )

        if (this.parent.countBabies > 0) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Nu kan du umgås med barn och barnbarn.",
                    "",
                    ""
                )
            )
            this.countPoint += this.parent.countBabies * 3
        }

        //Beräkna antal lyckliga poäng
        this.countPoint += this.person.countPoints()

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Din sammanlagda lyckopoäng: ${this.countPoint}.",
                "",
                ""
            )
        )

        if (this.countPoint  > 10) {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du har haft ett väldigt lyckligt liv.",
                        "",
                        ""
                    )
                )
        } else if ( this.countPoint > 5) {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du har haft ett lyckligt liv.",
                        "",
                        ""
                    )
                )
        } else {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du bara jobbade och jobbade.",
                    "",
                    ""
                )
            )
        }

        //Summa konton
        currentAmount = this.accountDepot.amount + this.accountSalary.amount
        //+ Summa pension - skatt
        currentAmount += this.accountPension.amount - (this.accountPension.amount * 0.30F)

        //Summa kostnader per månad + kostnad mat
        currentCost = this.person.house.houseMonthPayment + person.costHobbies() + 3000

        sumPensionMonth = currentAmount / currentCost

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du har sparat totalt (-skatt): ${currentAmount.toInt().formatDecimalSeparator()} SEK",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Totalt i kostnader per månad: ${currentCost.toInt().formatDecimalSeparator()} SEK",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Det gör att du klara dig i: ${sumPensionMonth.toInt().formatDecimalSeparator()} månader",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Tills du är: ${(this.age + (sumPensionMonth/12).toInt()).formatDecimalSeparator()} år",
                "",
                ""
            )
        )

        //Ett poäng för varje år du klara
        this.countPoint += (sumPensionMonth/12).toInt()

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Lycka är när pengarna räcker tills månaden är slut.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du får 1 poäng per år som pengarna räcker. Dina lyckopoäng: ${this.countPoint}",
                "",
                ""
            )
        )

        if ((this.age + (sumPensionMonth/12)).toInt() > 100) {
            storyList = this.showRichLife(storyList, storyId)
            storyId = storyList[storyList.size - 1].id
        }else if ((this.age + (sumPensionMonth/12)).toInt() >= 75) {
            storyList = this.showRichLife(storyList, storyId)
            storyId = storyList[storyList.size - 1].id
        } else {
            storyList = this.showLifeValue(storyList, storyId)
            storyId = storyList[storyList.size - 1].id
        }

        return storyList
    }

    fun showLifeValue(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "------------------------------------------------------------------------",
                "deepskyblue",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Jag vill berätta att du inte är värd något om du inte är på rätt plats.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Om du inte är uppskattad, var inte arg, det betyder att du är på fel ställe.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Stanna inte på en plats där ingen ser ditt värde.",
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


        return storyList
    }

    fun showRichLife(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "------------------------------------------------------------------------",
                "deepskyblue",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Oavsett om kör en dyr eller billig bil, är vägen och sträckan densamma.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Oavsett om huset vi bor i är 100 eller 1000 kvm är ensamheten densamma.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Oavsett om du flyger första klass eller ekonomiklass, går du under om planet kraschar.",
                "",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Du kan anlita någon som tjänar pengar åt dig, men inte någon som dör åt dig.",
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

        return storyList
    }
}