import csstype.*
import emotion.css.keyframes
import emotion.css.merge
import emotion.react.css
import kotlinx.js.n
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.key
import react.useState
import kotlin.Float
import kotlin.random.Random
import kotlin.collections.List
import kotlin.math.absoluteValue

external interface StartWorkingLifeProps : Props {
    var selectedView: View
    var selectedProfession: Profession
    var selectedPerson: Person
    var selectedMessages: List<Message>

    var onSelectMessages: (View, List<Message>, Profession, Person) -> Unit
}

val StartWorkingLife = FC<StartWorkingLifeProps> { props ->

    // Init person

    val reloadView: View = props.selectedView.getNextView(props.selectedView)
    val person = props.selectedPerson
    var profession = props.selectedProfession
    val allProfessions = profession.getAllProfession()
    var age: Int = person.age
    val pensionAge = profession.pensionAge
    val parent = Parent(person.id)
    var employeeId: Int = 1
    var employee = Employee(employeeId)
    val accountPension = Account(1, "pensionskonto")
    val accountSalary = Account(2, "lönekonto")
    val accountDepot = Account(3, "depå")
    val accountTax = Account(4, "skatt")
    val union = person.union

    person.professions = person.professions.plus(profession)
    employee.title = profession.title
    employee.firstSalary = profession.salary * person.age.toFloat()
    employee.currentSalary = profession.salary * person.age.toFloat()

    //Init random worklife values to pensionage
    val randomLifeValues = List(pensionAge) { Random.nextInt(0, 100) }
    var randomValues = List(1) { Random.nextInt(1, 12) }
    val event = Event(0)

    //Init life events
    val allEvents: List<Event> = event.getEvents()
    var randomEventValues = List(1) { Random.nextInt(0, 10) }
    val allCostEvents: List<Event> = event.getCostEvents()
    var randomCostEventValues = List(1) { Random.nextInt(0, 5) }

    //Init story
    var messageList: List<Message> = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var messageId = 0
    val maxMessages = 6
    val yearMessage: List<Int> = listOf(30, 35, 40, 45, 55, 50, 65, 75)
    var currentTitle = true
    var currentAmount:Float = 0.0F

    if (messageList.isEmpty()) {

        // starting your workingstory
        messageList = employee.showEmployeeSalary(age, 0.0F,messageList, messageId)
        messageId = messageList.size

        currentTitle = false

        //Loop all working years----------------------------------------------------
        for (year in person.age..profession.pensionAge) {

            //Init count month
            parent.countFamilyMonth = 0
            parent.familySalary = 0.0F
            employee.countSickMonth = 0
            employee.sickSalary = 0.0F
            union.countUnEmployeeMonth = 0
            person.sick = false

            //Försörjningsstöd
            if (employee.currentSalary == 0.0F) employee.currentSalary = 4180.0F

            //Get event in life
            randomEventValues = List(1) { Random.nextInt(0, allEvents.size-1) }

            when (allEvents[randomEventValues[0]].eventType) {
                "depot" -> {
                    //Bonus
                    if (randomLifeValues[year - 1] < 10 && person.countWorkMonth >= 12 && employee.currentSalary > 0.0) {

                        //Get value och financial instruments
                        randomValues = List(1) { Random.nextInt(10000, 50000) }
                        accountDepot.amount += randomValues[0].toFloat()

                        messageList = accountDepot.showDepotAmount(age,messageList,messageId)
                        messageId = messageList.size

                    }
                }

                "sick" -> {
                    //Sjuk
                    if (randomLifeValues[year - 1] < 10) {

                        when (allEvents[randomEventValues[0]].objectType) {
                            "burnedout" -> {
                                if (!person.luck) {
                                    person.sick = true

                                    //How many months are you sick?
                                    randomValues = List(1) { Random.nextInt(12, 36) }
                                    employee.countSickMonth = randomValues[0]
                                }
                            }

                            "heartattack" -> {
                                if (!person.luck) {
                                    person.sick = true

                                    //How many months are you sick?
                                    randomValues = List(1) { Random.nextInt(1, 12) }
                                    employee.countSickMonth = randomValues[0]
                                }
                            }

                            "golf" -> {
                                person.sick = true

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(1, 12) }
                                employee.countSickMonth = randomValues[0]
                            }

                            "shot" -> {
                                person.sick = true

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(1, 12) }
                                employee.countSickMonth = randomValues[0]
                            }

                            "depressed" -> {
                                if (!person.luck) {
                                    person.sick = true

                                    //How many months are you sick?
                                    randomValues = List(1) { Random.nextInt(12, 24) }
                                    employee.countSickMonth = randomValues[0]
                                }
                            }
                        }

                        if (person.sick) {
                            //Save event in story
                            messageList =
                                messageList.plus(
                                    Message(
                                        messageId,
                                        "Åh nej! " + allEvents[randomEventValues[0]].eventText,
                                        "",
                                        "blinking"
                                    )
                                )
                            messageId += 1

                            employee.sickSalary = person.insurance.getIncome(employee.currentSalary)

                            messageList = employee.showEmployeeSickSalary(messageList,messageId)
                            messageId = messageList.size

                            //Get chance to be approved by swedish authority
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < 15) {
                                //Din sjukskrivning blir avslagen
                                employee.sickSalary = 0.0F

                                messageList = employee.showEmployeeNoSickSalary(messageList, messageId)
                                messageId = messageList.size
                            }

                            //employee.countWorkMonth -= employee.countSickMonth
                            //accountSalary.amount += employee.sickSalary * employee.countSickMonth

                            messageList = employee.showEmployeeCountSickMonth(messageList,messageId)
                            messageId = messageList.size
                        }
                    }
                }

                "luck" -> {
                    //Lycklig
                    if (randomLifeValues[year - 1] < 10) {
                        person.luck = true
                        
                        messageList = person.showPersonLuck(age,messageList,messageId)
                        messageId = messageList.size
                    }
                }

                "unemployed" -> {
                    if (randomLifeValues[year - 1] < 35 && !person.magellit) {

                        if (allEvents[randomEventValues[0]].objectType == "unemployed") {
                            //Varslad
                            union.countUnEmployeeMonth = 0
                            union.unEmployedSalaryAmount = 0.0

                            //Chans till avgångsvederlag om du jobbat mer än 12 månader
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < 30 && person.countWorkMonth >= 12) {

                                //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                                accountSalary.amount = employee.currentSalary * (person.countWorkMonth / 12).toFloat()

                                messageList = accountSalary.showSeverancePay(age,messageList,messageId)
                                messageId = messageList.size
                            }

                            //Hur många dagar är du arbetslös, 22 dagar per månad får man ersättning
                            randomValues = List(1) { Random.nextInt(1, 300) }
                            union.countUnEmployeeMonth = randomValues[0] / 22

                            //Endast 300 dagar kan man få a-kassa
                            //employee.countWorkMonth -= union.countUnEmployeeMonth

                            messageList = union.showCountUnEmployeeMonth(age,messageList,messageId)
                            messageId = messageList.size

                            //För att få akassa behövs 12 månaders arbete
                            if (union.incomeInsurance && person.countWorkMonth >= 12) {

                                union.unEmployedSalaryAmount =
                                    union.getIncomeInsurance(employee.currentSalary.toDouble())
                                messageList = union.showIncomeInsurance(messageList, messageId)
                                messageId = messageList.size

                            } else if (union.akassa && person.countWorkMonth >= 12) {

                                union.unEmployedSalaryAmount = union.getAkassa(employee.currentSalary.toDouble())
                                messageList = union.showAkassa(messageList, messageId)
                                messageId = messageList.size

                            } else {
                                union.unEmployedSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                                messageList = union.showNoAkassa(messageList, messageId)
                                messageId = messageList.size

                            }

                            //accountSalary.amount += union.unEmployedSalaryAmount.toFloat()
                        }

                        //Dags att byta jobb och spara tidigare jobb
                        person.professions = person.professions.plus(profession)
                        person.employees = person.employees.plus(employee)

                        //Nytt jobb
                        randomValues = List(1) { Random.nextInt(0, allProfessions.size - 1) }
                        profession = allProfessions[randomValues[0]]

                        employeeId += 1
                        employee = Employee(employeeId)
                        employee.title = profession.title
                        employee.firstSalary = profession.salary * person.age
                        employee.currentSalary = profession.salary * person.age
                        employee.countWorkMonth = 0

                        messageList = profession.showNewProfession(messageList, messageId)
                        messageId = messageList.size

                        messageList = employee.showEmployeeSalary(age, 0.0F, messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "magellit" -> {
                    //Magellit
                    if (randomLifeValues[year - 1] < 10) {
                        person.magellit = true

                        messageList = person.showPersonMagellit(age, messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "parent" -> {
                    if (randomLifeValues[year - 1] < 35 && age <= 50) {
                        //Babies
                        parent.countBabies += 1
                        parent.familySalary = parent.getIncome(employee.currentSalary)
                        if (person.magellit) parent.familySalary += 5000.0F

                        parent.countFamilyMonth += parent.familyMonth

                        //employee.countWorkMonth -= parent.countFamilyMonth
                        //accountSalary.amount += parent.familySalary * parent.countFamilyMonth

                        messageList = parent.showParent(age, messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "vab" -> {
                    if (randomLifeValues[year - 1] < 100 && parent.countBabies > 0) {
                        //VAB

                        randomValues = List(1) { Random.nextInt(1, 12) }
                        parent.countFamilyMonth += randomValues[1]
                        parent.familySalary = parent.getIncome(employee.currentSalary)

                        if (person.magellit)
                            parent.familySalary += 5000.0F

                        //employee.countWorkMonth -= parent.countFamilyMonth
                        //accountSalary.amount += parent.familySalary * parent.countFamilyMonth.toFloat()

                        messageList = parent.showVAB(age, messageList, messageId)
                        messageId = messageList.size
                    }
                }
            }

            //Get cost event in life
            randomCostEventValues = List(1) { Random.nextInt(0, allCostEvents.size-1) }

            when (allCostEvents[randomCostEventValues[0]].eventType) {
                "home" -> {
                    if (randomLifeValues[year - 1] < 10 || !person.accommodation) {

                        messageList =
                            messageList.plus(
                                Message(
                                    messageId,
                                    "${allCostEvents[randomCostEventValues[0]].eventText}",
                                    "",
                                    "blinking"
                                )
                            )
                        messageId += 1

                        if (person.accommodation && person.house.houseAmount > 0.0F) {
                            // Säljer det boendet du har
                            messageList = person.showPersonAccomodationSold(messageList, messageId)
                            messageId = messageList.size

                            if (person.house.loan) {
                                //Betala av lånet
                                accountSalary.amount += (person.house.houseAmount - person.house.houseLoan.loanAmount)
                                person.house.houseLoan.loanAmount = 0.0F
                                person.house.houseLoan.loanInterest = 0.0F
                                person.house.houseLoan.loanMonthPayment = 0.0F
                            } else {
                                accountSalary.amount += person.house.houseAmount
                            }
                        } else if (person.accommodation && person.house.houseType == "hire") {
                            messageList = person.showPersonAccomodationShift(messageList, messageId)
                            messageId = messageList.size
                        }

                        person.accommodation = true
                        when (allCostEvents[randomCostEventValues[0]].objectType) {
                            "house" -> {
                                person.house = House(0, "hus")

                                randomValues = List(1) { Random.nextInt(2000000, 10000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(2000, 5000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonHouseBought(messageList, messageId)
                                messageId = messageList.size
                            }

                            "department" -> {
                                person.house = House(0, "bostadsrätt")

                                randomValues = List(1) { Random.nextInt(1000000, 10000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(1000, 7000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonDepartmentBought(messageList, messageId)
                                messageId = messageList.size
                            }

                            "hire" -> {
                                person.house = House(0, "hyreslägenhet")
                                person.house.houseAmount = 0.0F

                                randomValues = List(1) { Random.nextInt(5000, 30000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonAccomodationHire(messageList, messageId)
                                messageId = messageList.size
                            }
                        }

                        person.house.loan = false
                        if (person.house.houseAmount > 0.0F) {

                            // Betala eller ta lån?
                            if (accountSalary.amount >= person.house.houseAmount) {
                                accountSalary.amount -= person.house.houseAmount
                            } else if (accountDepot.amount >= person.house.houseAmount) {
                                accountDepot.amount -= person.house.houseAmount
                            } else if ((accountDepot.amount + accountSalary.amount) >= person.house.houseAmount) {
                                accountDepot.amount -= (person.house.houseAmount - accountSalary.amount)
                                accountSalary.amount = 0.0F
                            } else {
                                person.house.loan = true
                                person.house.houseLoan.loanAmount =
                                    person.house.houseAmount - (accountDepot.amount + accountSalary.amount)

                                accountSalary.amount = 0.0F
                                accountDepot.amount = 0.0F
                                randomValues = List(1) { Random.nextInt(1, 4) }
                                person.house.houseLoan.loanInterest = randomValues[0].toFloat()
                                person.house.houseLoan.loanMonthPayment =
                                    person.house.houseLoan.loanAmount / ((profession.pensionAge - age)*12)

                                messageList = person.showPersonGetHouseLoan(messageList, messageId)
                                messageId = messageList.size
                            }
                        }
                    }
                }
                "accident" -> {
                    if (randomLifeValues[year - 1] < 10) {

                        when (allCostEvents[randomCostEventValues[0]].objectType) {
                            "depot" -> {
                                if (accountDepot.amount > 0.0F) {

                                    randomValues = List(1) { Random.nextInt(1, 50) }
                                    accountDepot.amount -= accountDepot.amount * randomValues[0].toFloat()/100.0F

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0] }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1
                                }
                            }

                            "home" -> {
                                if (person.accommodation) {

                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseMonthPayment += person.house.houseMonthPayment * (randomValues[0].toFloat()/100.0F)

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0].formatDecimalSeparator() }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1

                                    // Betala eller ta lån?
                                    /*
                                    if (accountSalary.amount >= currentAmount) {
                                        accountSalary.amount -= currentAmount
                                    } else if (accountDepot.amount >= currentAmount) {
                                        accountDepot.amount -= currentAmount
                                    } else if ((accountDepot.amount + accountSalary.amount) >= currentAmount) {
                                        accountDepot.amount -= (currentAmount - accountSalary.amount)
                                        accountSalary.amount = 0.0F
                                    } else {
                                        person.loan = true
                                        person.blancoLoan.loanAmount =
                                            currentAmount - (accountDepot.amount + accountSalary.amount)

                                        randomValues = List(1) { Random.nextInt(1, 4) }
                                        person.blancoLoan.loanInterest = randomValues[0].toFloat()
                                        person.blancoLoan.loanMonthPayment =
                                            person.blancoLoan.loanAmount / ((profession.pensionAge - age) * 12)

                                        messageList = person.showPersonGetBlancoLoan(messageList, messageId)
                                        messageId = messageList.size
                                    }

                                     */
                                }
                            }

                            "loan" -> {
                                if (person.house.loan) {
                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseLoan.loanInterest += randomValues[0].toFloat()/100.0F

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0] }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1
                                }
                            }
                        }
                    }
                }
                "happening" -> {
                    if (randomLifeValues[year - 1] < 10) {

                        when (allCostEvents[randomCostEventValues[0]].objectType) {
                            "depot" -> {
                                if (accountDepot.amount > 0.0F) {

                                    randomValues = List(1) { Random.nextInt(1, 50) }
                                    accountDepot.amount += accountDepot.amount * randomValues[0].toFloat()/100.0F

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0] }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1
                                }
                            }

                            "home" -> {
                                if (person.accommodation) {

                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseMonthPayment -= person.house.houseMonthPayment * (randomValues[0].toFloat()/100.0F)

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0].formatDecimalSeparator() }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1

                                    // Betala eller ta lån?
                                    /*
                                    if (accountSalary.amount >= currentAmount) {
                                        accountSalary.amount -= currentAmount
                                    } else if (accountDepot.amount >= currentAmount) {
                                        accountDepot.amount -= currentAmount
                                    } else if ((accountDepot.amount + accountSalary.amount) >= currentAmount) {
                                        accountDepot.amount -= (currentAmount - accountSalary.amount)
                                        accountSalary.amount = 0.0F
                                    } else {
                                        person.loan = true
                                        person.blancoLoan.loanAmount =
                                            currentAmount - (accountDepot.amount + accountSalary.amount)

                                        randomValues = List(1) { Random.nextInt(1, 4) }
                                        person.blancoLoan.loanInterest = randomValues[0].toFloat()
                                        person.blancoLoan.loanMonthPayment =
                                            person.blancoLoan.loanAmount / ((profession.pensionAge - age) * 12)

                                        messageList = person.showPersonGetBlancoLoan(messageList, messageId)
                                        messageId = messageList.size
                                    }

                                     */
                                }
                            }

                            "loan" -> {
                                if (person.house.loan) {
                                    randomValues = List(1) { Random.nextInt(1, 2) }
                                    person.house.houseLoan.loanInterest -= randomValues[0].toFloat()/100.0F

                                    messageList =
                                        messageList.plus(
                                            Message(
                                                messageId,
                                                "${allCostEvents[randomCostEventValues[0]].eventText} med ${ randomValues[0] }%.",
                                                "",
                                                "blinking"
                                            )
                                        )
                                    messageId += 1
                                }
                            }
                        }
                    }
                }
            }


            // Nu har det gått ett år
            // Du fyller år
            age += 1
            person.countWorkMonth += 12

            messageId += 1
            messageList = person.showWorkingLife(year, messageList, messageId)
            messageId = messageList.size

            //Löneökning
            if (employee.currentSalary != 4180.0F) {
                randomValues = List(1) { Random.nextInt(5, 98) }
                employee.currentSalary += (employee.currentSalary * (randomValues[0].toFloat() / 1000.0F))
            }

            messageList = employee.showEmployeeSalary(age,(randomValues[0].toFloat() / 10.0F), messageList, messageId)
            messageId = messageList.size

            //12 arbetsmånader har gått men har du jobbat
            employee.countWorkMonth += 12

            //sick
            employee.countWorkMonth -= employee.countSickMonth
            accountSalary.amount += employee.sickSalary * employee.countSickMonth.toFloat()

            //varslad
            employee.countWorkMonth -= union.countUnEmployeeMonth
            accountSalary.amount += union.unEmployedSalaryAmount.toFloat()

            //parent
            employee.countWorkMonth -= parent.countFamilyMonth
            accountSalary.amount += parent.familySalary * parent.countFamilyMonth.toFloat()

            //Lägg på inkomst
            if (employee.countWorkMonth > 0) {
                currentAmount = (employee.currentSalary - (employee.currentSalary * person.pension)) * employee.countWorkMonth.toFloat()
                accountTax.amount += currentAmount * 0.3F //Skatt
                accountSalary.amount += currentAmount - (currentAmount * 0.3F)
                accountPension.amount += (employee.currentSalary * person.pension) * employee.countWorkMonth.toFloat()

                messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
                messageId = messageList.size

                employee.countWorkMonth = 0
            } else {
                messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
                messageId = messageList.size
            }

            messageList = accountSalary.showAccountAmount((year-person.age+1), messageList, messageId)
            messageId = messageList.size

            if (accountDepot.amount > 0) {
                messageList = accountDepot.showAccountAmount((year-person.age+1), messageList, messageId)
                messageId = messageList.size
            }

            if (age >= 50) {
                messageList = accountPension.showAccountAmount((year-person.age+1), messageList, messageId)
                messageId = messageList.size

                messageList = accountTax.showAccountAmount((year-person.age+1), messageList, messageId)
                messageId = messageList.size
            }

            //Dra av diverse levnadskostnader för mat är 3000 genomsnittet
            randomValues = List(1) { Random.nextInt(5000, 10000) }
            accountSalary.amount -= randomValues[0].toFloat() * 12.0F

            //Barn kostar pengar
            for (baby in 1..parent.countBabies) {
                accountSalary.amount -= 2500.0F * 12.0F //Enligt SEB
            }

            //Dra av kostnad för boendet
            if (person.accommodation) {
                accountSalary.amount -= person.house.houseMonthPayment
                if (person.house.loan) {
                    accountSalary.amount -= ((person.house.houseLoan.loanAmount * (person.house.houseLoan.loanInterest/100.0F)) / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
                    accountSalary.amount -= (person.house.houseLoan.loanMonthPayment * 12.0F)
                    person.house.houseLoan.loanAmount -= (person.house.houseLoan.loanMonthPayment * 12.0F)
                }
            }

            if (person.loan) {
                accountSalary.amount -= ((person.blancoLoan.loanAmount * (person.blancoLoan.loanInterest / 100.0F)) / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
                accountSalary.amount -= person.blancoLoan.loanMonthPayment * 12.0F
                person.blancoLoan.loanAmount -= person.blancoLoan.loanMonthPayment * 12.0F
            }

            messageList = accountSalary.showAccountCost(messageList, messageId)
            messageId = messageList.size

            if (person.accommodation) {
                messageList = person.showPersonAccomodation(messageList, messageId)
                messageId = messageList.size

                if (person.house.loan) {
                    messageList = person.showPersonHouseLoan(messageList, messageId)
                    messageId = messageList.size
                }
            }
        }

        person.age = age
        person.professions = person.professions.plus(profession)
        person.employees = person.employees.plus(employee)
        person.accounts = person.accounts.plus(accountSalary)
        person.accounts = person.accounts.plus(accountDepot)
        person.accounts = person.accounts.plus(accountPension)
    }

    if (messageList.isNotEmpty()) {

        div {
            val blinking: AnimationName = keyframes {
                0.pct {
                    color = NamedColor.darkgoldenrod
                }
                10.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                20.pct {
                    color = NamedColor.darkgoldenrod
                }
                30.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                40.pct {
                    color = NamedColor.darkgoldenrod
                }
                50.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                60.pct {
                    color = NamedColor.darkgoldenrod
                }
                70.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                80.pct {
                    color = NamedColor.darkgoldenrod
                }
                90.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                100.pct {
                    color = NamedColor.darkgoldenrod
                }
            }
            css {
                display = Display.block
                position = Position.absolute
                top = 90.px
                left = 10.px

                color = NamedColor.green
                borderColor = NamedColor.white
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }

            for ((messageIndex, message) in messageList.withIndex()) {
                if (messageIndex == maxMessages && message.messageText.contains("Det har gått")) {
                    leftMessages = leftMessages.plus(message)
                } else if (messageIndex <= maxMessages) {

                    if (message.animation != "") {
                        when (message.animation) {
                            "blinking" -> {
                                p {

                                    css {
                                        animationDuration = 3.s
                                        animationName = blinking
                                        animationFillMode = AnimationFillMode.both
                                        color = NamedColor.gold
                                    }
                                    +message.messageText
                                }
                            }
                        }
                    } else {
                        when (message.color) {
                            "deepskyblue" -> {
                                p {
                                    css {
                                        color = NamedColor.deepskyblue
                                    }
                                    +message.messageText
                                }
                            }
                            "lavender" -> {
                                p {
                                    css {
                                        color = NamedColor.blueviolet
                                    }
                                    +message.messageText
                                }
                            }
                            "hotpink" -> {
                                p {
                                    css {
                                        color = NamedColor.hotpink
                                    }
                                    +message.messageText
                                }
                            }
                            "orange" -> {
                                p {
                                    css {
                                        color = NamedColor.orange
                                    }
                                    +message.messageText
                                }
                            }
                            "" -> {
                                p {
                                    css {
                                        color = NamedColor.green
                                    }
                                    +message.messageText
                                }
                            }
                        }
                    }
                } else {
                    leftMessages = leftMessages.plus(message)
                }
            }
        }

        //Show animation
        div {
            ShowStreckPilot {}

            ShowStreck {}
        }

        p {
            button {

                key = messageList[0].id.toString()
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 10.px
                    left = 10.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                if (leftMessages.size >= maxMessages ) {
                    onClick = {
                        props.onSelectMessages(
                            props.selectedView,
                            leftMessages,
                            props.selectedProfession,
                            person
                        )
                    }
                    +props.selectedView.buttonText
                } else {
                    onClick = {
                        props.onSelectMessages(
                            reloadView.getNextView(reloadView),
                            leftMessages,
                            props.selectedProfession,
                            person
                        )
                    }
                    +reloadView.buttonText
                }
                +" ▶"
            }
        }
    }

/*
    ShowWorkingLife {
        actualProfession = props.selectedProfession
        actualPerson = person
        newTitle = currentTitle
    }

 */
}

fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
}










