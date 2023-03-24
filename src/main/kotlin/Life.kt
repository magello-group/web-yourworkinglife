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
    var luck: String = ""
    var luckPoint: Int = 0

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
            this.countPoint += this.parent.countBabies * 3

            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Nu kan du umgås med barn och barnbarn, lyckopoäng: ${this.countPoint} ",
                    "",
                    ""
                )
            )
        }

        if (this.person.isLove) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Din livskärlek finns med dig 💕💕💕💕💕.",
                    "",
                    "blinking"
                )
            )
            this.countPoint += 1
        }

        if (this.person.houses.size > 1) {
            if (this.person.houses[this.person.houses.size - 1].houseMonthPayment > 0.0F) {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du fann dig till ro i ditt bo, ${this.person.houses[this.person.houses.size - 1].description}",
                        "",
                        ""
                    )
                )
                this.countPoint += 1
            } else {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "Du lånar en soffa att bo på.",
                        "",
                        ""
                    )
                )
            }
        }

        when (this.person.professions[0].objectType) {
            "salary" -> {
                // Question(4,"Maxa lönen", "lön", "salary"),

                if (this.employee.currentSalary >= 100000) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa lönen: ${this.employee.currentSalary.toInt().formatDecimalSeparator()}, vilket du gjorde!",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa lönen: ${this.employee.currentSalary.toInt().formatDecimalSeparator()}, du kom inte helt i mål.",
                            "",
                            ""
                        )
                    )
                }
            }

            "pension" -> {
                // Question(5, "Maxa pensionen", "pension", "pension"),

                if (this.accountPension.amount >= 5000000) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa pensionen: ${
                                this.accountPension.amount.toInt().formatDecimalSeparator()
                            }, vilket du gjorde!",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa pensionen: ${
                                this.accountPension.amount.toInt().formatDecimalSeparator()
                            }, du kom inte helt i mål.",
                            "",
                            ""
                        )
                    )
                }
            }

            "adventure" -> {
                // Question(0,"Maxa spänningen", "spänning", "adventure"),

                if (this.person.countSickMonth >= 12) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa spänningen, vilket du gjorde med antal sjukmånader: ${this.person.countSickMonth}.",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa spänningen, du kom inte helt i mål då du verkar oskadd :).",
                            "",
                            ""
                        )
                    )
                }
            }

            "vacation" -> {
                // Question(2, "Maxa semesterdagarna", "semester", "vacation"),

                if (this.age <= 60) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa semestern, vilket du gjorde med att gå i pension tidigt.",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att maxa semestern, du kom inte helt i mål då du går i pension sent.",
                            "",
                            ""
                        )
                    )
                }
            }

            "family" -> {
                // Question(1, "Göra samhällsnytta och skillnad", "familj", "family",),

                if (this.employee.currentSalary <= 45000) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Du vill göra samhällsnytta och skillnad, du gick på ditt kall även om lönen var låg: ${
                                this.employee.currentSalary.toInt().formatDecimalSeparator()
                            }",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Du vill göra samhällsnytta och skillnad, du gick inte helt på ditt kall och accepterade låg lön",
                            "",
                            ""
                        )
                    )
                }
            }

            "chilla" -> {
                // Question(3, "Chilla", "chilla", "chilla"),

                if ((this.accountSalary.amount + this.accountDepot.amount) < 0) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att chilla, som du klarade det och tjänade inte så mycket: ${
                                this.accountSalary.amount.toInt().formatDecimalSeparator()
                            }",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att chilla, du kom inte helt i mål då du verkar ha jobbat :)",
                            "",
                            ""
                        )
                    )
                }
            }

            "fun" -> {
                // Question(6, "Bara ha kul", "kul", "fun")

                if (this.person.countSickMonth <= 1) {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Ditt mål var att ha kul. Livet var så kul och du var sällan sjuk.",
                            "",
                            "blinking"
                        )
                    )
                    this.countPoint += 5
                } else {
                    storyId += 1
                    storyList = storyList.plus(
                        Message(
                            storyId,
                            "Livet var inte alltid så kul, du blev sjuk i ${this.person.countSickMonth} månader",
                            "",
                            ""
                        )
                    )
                }
            }
        }

        if (this.person.professions.size <= 1) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du stannade på samma arbetsplats hela livet.",
                    "",
                    ""
                )
            )
        } else {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du fann den arbetsplats som förstod ditt värde.",
                    "",
                    ""
                )
            )
            this.countPoint += 1
        }

        //Summa konton
        currentAmount = this.accountDepot.amount + this.accountSalary.amount
        //+ Summa pension - skatt
        currentAmount += this.accountPension.amount - (this.accountPension.amount * 0.30F)

        //Summa kostnader per månad + kostnad mat
        if (this.person.isLove)
            currentCost = (this.person.house.houseMonthPayment / 2) + (person.costHobbies() / 12) + 3000
        else
            currentCost = this.person.house.houseMonthPayment + (person.costHobbies() / 12) + 3000

        sumPensionMonth = currentAmount / currentCost

        if (currentAmount <= 0.0F) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du ligger back: ${currentAmount.toInt().formatDecimalSeparator()} SEK.",
                    "",
                    ""
                )
            )
        } else {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du har sparat totalt (-skatt): ${currentAmount.toInt().formatDecimalSeparator()} SEK",
                    "",
                    ""
                )
            )
        }
        if ((this.accountNoAkassa.amount - this.accountSalary.amount) < 0) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Med a-kassa (inkomstförsäkring och tilläggsförsäkring) tjänade du: ${
                        (this.accountSalary.amount - this.accountNoAkassa.amount).toInt().formatDecimalSeparator() 
                    } SEK",
                    "",
                    ""
                )
            )
        }

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Totalt i kostnader per månad: ${currentCost.toInt().formatDecimalSeparator()} SEK",
                "",
                ""
            )
        )

        if (sumPensionMonth > 0.0F) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Det gör att du klara dig tills du är: ${(this.age + (sumPensionMonth / 12).toInt()).formatDecimalSeparator()} år",
                    "",
                    ""
                )
            )
        } else {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du får jobba några år till.",
                    "",
                    ""
                )
            )
        }

        //Ett poäng för varje år du klara
        if ((this.age + (sumPensionMonth / 12)) > 100) {
            this.countPoint += (100 - this.age)
        } else {
            this.countPoint += (sumPensionMonth / 12).toInt()
        }

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Lycka är när pengarna räcker livet ut, lyckopoäng: ${this.countPoint}",
                "",
                ""
            )
        )

        //Beräkna antal lyckliga poäng
        this.countPoint += this.luckPoint

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "Din sammanlagda lyckopoäng: ${this.countPoint}",
                "",
                "blinking"
            )
        )

        if (this.countPoint > 50) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du har haft ett väldigt lyckligt arbetsliv.",
                    "",
                    "blinking"
                )
            )
        } else if (this.countPoint > 25) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du har haft ett lyckligt arbetsliv.",
                    "",
                    "blinking"
                )
            )
        } else {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "Du bara jobbade och jobbade.",
                    "",
                    "blinking"
                )
            )
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
