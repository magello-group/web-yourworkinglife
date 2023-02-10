import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key
import kotlin.Float
import kotlin.random.Random
import kotlin.collections.List

external interface StartWorkingLifeProps : Props {
    var selectedView: View
    var selectedProfession: Profession
    var selectedPerson: Person
    var selectedMessages: List<Message>
    var selectedHistory: List<Message>
    var selectedStatus: Status

    var onSelectMessages: (View, List<Message>, Profession, Person, List<Message>, Status) -> Unit
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
    var employeeId = 1
    var employee = Employee(employeeId)
    val accountPension = Account(1, "pensionskonto")
    val accountSalary = Account(2, "lönekonto")
    val accountDepot = Account(3, "depå")
    val accountTax = Account(4, "skatt")
    val accountNoAkassa = Account(2, "noakassa")
    val union = person.union

    person.professions = person.professions.plus(profession)
    employee.title = profession.title
    employee.firstSalary = profession.salary * person.age.toFloat()
    employee.currentSalary = profession.salary * person.age.toFloat()

    //Init random worklife values to pensionage
    val randomLifeValues = List(pensionAge) { Random.nextInt(0, 100) }
    var randomValues: List<Int>
    val event = Event(0)

    //Init life events
    val allEvents: List<Event> = event.getEvents()
    var randomEventValues = List(1) { Random.nextInt(0, 10) }
    val allCostEvents: List<Event> = event.getCostEvents()
    var randomCostEventValues: List<Int>

    //Init story
    var messageList = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var historyMessages = props.selectedHistory
    var backupMessages: List<Message> = emptyList()
    var actualStatus = props.selectedStatus
    var messageId = 0
    val maxMessages = 6
    var currentAmount: Float
    var pandemi = false
    var boom = false
    var recession = false
    val debugIsOn = false

    if (messageList.isEmpty()) {

        // starting your workingstory
        messageList = employee.showEmployeeSalary(0.0F,messageList, messageId)
        messageId = messageList.size


        //Loop all working years----------------------------------------------------
        for (year in person.age..profession.pensionAge) {

            if (debugIsOn) {

                messageList = messageList.plus(
                    Message(
                        messageId,
                        "Event: ${allEvents[randomEventValues[0]].eventType}, Object:  ${allEvents[randomEventValues[0]].objectType} ",
                        "grey",
                        ""
                    )
                )
                messageId += 1
            }

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
            randomEventValues = List(1) { Random.nextInt(0, allEvents.size) }

            when (allEvents[randomEventValues[0]].eventType) {
                "depot" -> {
                    //Bonus
                    if (randomLifeValues[year - 1] < 10 && person.countWorkMonth >= 12 && employee.currentSalary > 0.0) {

                        //Get value och financial instruments
                        randomValues = List(1) { Random.nextInt(10000, 50000) }
                        accountDepot.amount += randomValues[0].toFloat()

                        messageList = accountDepot.showDepotAmount(messageList, messageId)
                        messageId = messageList.size

                    }
                }

                "sick" -> {
                    //Sjuk
                    if (randomLifeValues[year - 1] < 15 || pandemi) {

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
                            messageList = allEvents[randomEventValues[0]].showEvent(messageList, messageId, "Åh nej! ", "")
                            messageId = messageList.size

                            employee.sickSalary = person.insurance.getIncome(employee.currentSalary)

                            messageList = employee.showEmployeeSickSalary(messageList, messageId)
                            messageId = messageList.size

                            //Get chance to be approved by swedish authority
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < 15) {
                                //Din sjukskrivning blir avslagen
                                employee.sickSalary = 0.0F

                                messageList = employee.showEmployeeNoSickSalary(messageList, messageId)
                                messageId = messageList.size
                            }

                            messageList = employee.showEmployeeCountSickMonth(messageList, messageId)
                            messageId = messageList.size
                        }
                    }
                }

                "luck" -> {
                    //Lycklig
                    if (randomLifeValues[year - 1] < 10) {
                        person.luck = true

                        messageList = person.showPersonLuck(messageList, messageId, allEvents[randomEventValues[0]].eventText)
                        messageId = messageList.size
                    }
                }

                "unemployed" -> {
                    if ((randomLifeValues[year - 1] < 40 && !person.magellit) || recession) {
                        union.countUnEmployeeMonth = 0
                        union.unEmployedSalaryAmount = 0.0
                        union.noAkassaSalaryAmount = 0.0

                        if (allEvents[randomEventValues[0]].objectType == "unemployed") {
                            //Varslad

                            //Chans till avgångsvederlag om du jobbat mer än 12 månader
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < 30 && person.countWorkMonth >= 12) {

                                //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                                accountSalary.amount = employee.currentSalary * (person.countWorkMonth / 12).toFloat()
                                accountNoAkassa.amount = employee.currentSalary * (person.countWorkMonth / 12).toFloat()

                                messageList = accountSalary.showSeverancePay(messageList, messageId)
                                messageId = messageList.size
                            }

                            //Hur många dagar är du arbetslös, 22 dagar per månad får man ersättning
                            randomValues = List(1) { Random.nextInt(22, 300) }
                            union.countUnEmployeeMonth = randomValues[0] / 22

                            //Endast 300 dagar kan man få a-kassa

                            messageList = union.showCountUnEmployeeMonth(messageList, messageId)
                            messageId = messageList.size

                            //För att få akassa behövs 12 månaders arbete
                            if (union.incomeInsurance && person.countWorkMonth >= 12) {

                                union.unEmployedSalaryAmount =
                                    union.getIncomeInsurance(employee.currentSalary.toDouble())
                                messageList = union.showIncomeInsurance(messageList, messageId)
                                messageId = messageList.size

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())

                            } else if (union.akassa && person.countWorkMonth >= 12) {

                                union.unEmployedSalaryAmount = union.getAkassa(employee.currentSalary.toDouble())
                                messageList = union.showAkassa(messageList, messageId)
                                messageId = messageList.size

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())

                            } else if (person.countWorkMonth >= 12) {
                                union.unEmployedSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                                messageList = union.showNoAkassa(messageList, messageId)
                                messageId = messageList.size

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                            } else {
                                union.unEmployedSalaryAmount = 0.0
                                union.noAkassaSalaryAmount = 0.0
                            }
                        }


                        //Dags att byta jobb och spara tidigare jobb
                        person.professions = person.professions.plus(profession)
                        person.employees = person.employees.plus(employee)

                        //Nytt jobb
                        randomValues = List(1) { Random.nextInt(0, allProfessions.size) }
                        profession = allProfessions[randomValues[0]]

                        employeeId += 1
                        employee = Employee(employeeId)
                        employee.title = profession.title
                        employee.firstSalary = profession.salary * person.age
                        employee.currentSalary = profession.salary * person.age
                        employee.countWorkMonth = 0

                        messageList = profession.showNewProfession(messageList, messageId)
                        messageId = messageList.size

                        messageList = employee.showEmployeeSalary(0.0F, messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "magellit" -> {
                    //Magellit
                    if (randomLifeValues[year - 1] < 10) {
                        person.magellit = true

                        messageList = person.showPersonMagellit(messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "parent" -> {
                    if (randomLifeValues[year - 1] < 50 && age <= 50) {
                        //Babies
                        parent.countBabies += 1
                        parent.familySalary = parent.getIncome(employee.currentSalary)
                        if (person.magellit) parent.familySalary += 5000.0F

                        parent.countFamilyMonth += parent.familyMonth

                        messageList = parent.showParent(messageList, messageId)
                        messageId = messageList.size
                    }
                }

                "vab" -> {
                    if (parent.countBabies > 0) {
                        //VAB

                        randomValues = List(1) { Random.nextInt(1, 12) }
                        parent.countFamilyMonth += randomValues[0]
                        parent.familySalary = parent.getIncome(employee.currentSalary)

                        if (person.magellit)
                            parent.familySalary += 5000.0F

                        messageList = parent.showVAB(messageList, messageId)
                        messageId = messageList.size
                    }
                }
            }

            //Get cost event in life
            randomCostEventValues = List(1) { Random.nextInt(0, allCostEvents.size) }

            when (allCostEvents[randomCostEventValues[0]].eventType) {
                "home" -> {
                    if (randomLifeValues[year - 1] > 85 || !person.accommodation) {
                        messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId, "", "")
                        messageId = messageList.size

                        if (person.accommodation && person.house.houseAmount > 0.0F) {
                            // Säljer det boendet du har
                            messageList = person.showPersonAccomodationSold(messageList, messageId)
                            messageId = messageList.size

                            if (person.house.loan) {
                                //Betala av lånet
                                accountSalary.amount += (person.house.houseAmount - person.house.houseLoan.loanAmount)
                                accountNoAkassa.amount += (person.house.houseAmount - person.house.houseLoan.loanAmount)
                                person.house.houseLoan.loanAmount = 0.0F
                                person.house.houseLoan.loanInterest = 0.0F
                                person.house.houseLoan.loanMonthPayment = 0.0F
                            } else {
                                accountSalary.amount += person.house.houseAmount
                                accountNoAkassa.amount += person.house.houseAmount
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
                                person.house = House(0, "hyresrätt")
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
                                accountNoAkassa.amount -= person.house.houseAmount

                            } else if (accountDepot.amount >= person.house.houseAmount) {
                                accountDepot.amount -= person.house.houseAmount

                            } else if ((accountDepot.amount + accountSalary.amount) >= person.house.houseAmount) {
                                accountDepot.amount -= (person.house.houseAmount - accountSalary.amount)
                                accountSalary.amount = 0.0F
                                accountNoAkassa.amount = 0.0F

                            } else {
                                person.house.loan = true
                                person.house.houseLoan.loanAmount =
                                    person.house.houseAmount - (accountDepot.amount + accountSalary.amount)

                                accountSalary.amount = 0.0F
                                accountNoAkassa.amount = 0.0F
                                accountDepot.amount = 0.0F
                                randomValues = List(1) { Random.nextInt(1, 4) }
                                person.house.houseLoan.loanInterest = randomValues[0].toFloat()
                                person.house.houseLoan.loanMonthPayment =
                                    person.house.houseLoan.loanAmount / ((profession.pensionAge - age) * 12)

                                messageList = person.showPersonGetHouseLoan(messageList, messageId)
                                messageId = messageList.size
                            }
                        }
                    }
                }

                "accident" -> {
                    if (randomLifeValues[year - 1] > 85) {
                        person.magellit = false
                        when (allCostEvents[randomCostEventValues[0]].objectType) {
                            "depot" -> {
                                recession = true
                                if (accountDepot.amount > 0.0F) {

                                    randomValues = List(1) { Random.nextInt(1, 50) }
                                    accountDepot.amount -= accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size
                                }
                            }

                            "home" -> {
                                recession = true
                                if (person.accommodation) {

                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseMonthPayment += person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size

                                }
                            }

                            "loan" -> {
                                recession = true
                                if (person.house.loan) {
                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseLoan.loanInterest += randomValues[0].toFloat() / 100.0F

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size
                                }
                            }

                            "sick" -> {
                                person.luck = false
                                pandemi = true

                                messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  ".")
                                messageId = messageList.size
                            }
                        }
                    }
                }

                "happening" -> {
                    if (randomLifeValues[year - 1] > 85) {
                        pandemi = false
                        boom = true

                        when (allCostEvents[randomCostEventValues[0]].objectType) {
                            "depot" -> {
                                if (accountDepot.amount > 0.0F) {

                                    randomValues = List(1) { Random.nextInt(1, 50) }
                                    accountDepot.amount += accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size
                                }
                            }

                            "home" -> {
                                if (person.accommodation) {

                                    randomValues = List(1) { Random.nextInt(1, 7) }
                                    person.house.houseMonthPayment -= person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size
                                }
                            }

                            "loan" -> {
                                if (person.house.loan) {
                                    randomValues = List(1) { Random.nextInt(1, 2) }
                                    person.house.houseLoan.loanInterest -= randomValues[0].toFloat() / 100.0F

                                    messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId,"",  " med ${randomValues[0].formatDecimalSeparator()}%.")
                                    messageId = messageList.size
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
            if (employee.currentSalary != 4180.0F &&
                (employee.countSickMonth + union.countUnEmployeeMonth + parent.countFamilyMonth == 0)
            ) {

                randomValues = if (boom) {
                    List(1) { Random.nextInt(40, 98) }
                } else if (recession) {
                    List(1) { Random.nextInt(5, 10) }
                } else {
                    List(1) { Random.nextInt(5, 30) }
                }

                employee.currentSalary += (employee.currentSalary * (randomValues[0].toFloat() / 1000.0F))

                messageList =
                    employee.showEmployeeSalary((randomValues[0].toFloat() / 10.0F), messageList, messageId)
                messageId = messageList.size
            } else {
                messageList =
                    employee.showEmployeeSalary(0.0F, messageList, messageId)
                messageId = messageList.size
            }

            //12 arbetsmånader har gått men har du jobbat
            employee.countWorkMonth += 12

            //sick
            employee.countWorkMonth -= employee.countSickMonth
            accountSalary.amount += employee.sickSalary * employee.countSickMonth.toFloat()
            accountNoAkassa.amount += employee.sickSalary * employee.countSickMonth.toFloat()

            //varslad
            employee.countWorkMonth -= union.countUnEmployeeMonth
            accountSalary.amount += union.unEmployedSalaryAmount.toFloat()
            accountNoAkassa.amount += union.noAkassaSalaryAmount.toFloat()

            //parent
            employee.countWorkMonth -= parent.countFamilyMonth
            accountSalary.amount += parent.familySalary * parent.countFamilyMonth.toFloat()
            accountNoAkassa.amount += parent.familySalary * parent.countFamilyMonth.toFloat()

            //Lägg på inkomst
            if (employee.countWorkMonth > 0) {
                currentAmount =
                    (employee.currentSalary - (employee.currentSalary * person.pension)) * employee.countWorkMonth.toFloat()
                accountTax.amount += currentAmount * 0.3F //Skatt
                accountSalary.amount += currentAmount - (currentAmount * 0.3F)
                accountNoAkassa.amount += currentAmount - (currentAmount * 0.3F)
                accountPension.amount += (employee.currentSalary * person.pension) * employee.countWorkMonth.toFloat()

                messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
                messageId = messageList.size

                employee.countWorkMonth = 0

            } else {
                messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
                messageId = messageList.size
            }

            messageList = accountSalary.showAccountAmount((year - person.age + 1), messageList, messageId)
            messageId = messageList.size

            if (accountDepot.amount > 0.0F) {
                if (accountSalary.amount < 0.0F) {
                    messageList = accountDepot.showSkuldsanering(messageList, messageId)
                    messageId = messageList.size

                    accountSalary.amount += accountDepot.amount
                    accountDepot.amount = 0.0F
                }
                messageList = accountDepot.showAccountAmount((year - person.age + 1), messageList, messageId)
                messageId = messageList.size
            }

            if (age >= 50) {
                messageList = accountPension.showAccountAmount((year - person.age + 1), messageList, messageId)
                messageId = messageList.size

                messageList = accountTax.showAccountAmount((year - person.age + 1), messageList, messageId)
                messageId = messageList.size
            }

            //Dra av diverse levnadskostnader för mat är 3000 genomsnittet
            randomValues = List(1) { Random.nextInt(5000, 10000) }
            accountSalary.amount -= randomValues[0].toFloat() * 12.0F
            accountNoAkassa.amount -= randomValues[0].toFloat() * 12.0F

            //Barn kostar pengar
            for (baby in 1..parent.countBabies) {
                accountSalary.amount -= 2500.0F * 12.0F
                accountNoAkassa.amount -= 2500.0F * 12.0F
            }

            //Dra av kostnad för boendet
            if (person.accommodation) {

                accountSalary.amount -= person.house.houseMonthPayment
                accountNoAkassa.amount -= person.house.houseMonthPayment

                if (person.house.loan) {
                    for (month in 1..12) {
                        if (person.house.houseLoan.loanAmount >= person.house.houseLoan.loanMonthPayment) {
                            accountSalary.amount -= ((person.house.houseLoan.loanAmount * (person.house.houseLoan.loanInterest / 100.0F))
                                    / (profession.pensionAge - age).toFloat())
                            accountSalary.amount -= person.house.houseLoan.loanMonthPayment

                            accountNoAkassa.amount -= ((person.house.houseLoan.loanAmount * (person.house.houseLoan.loanInterest / 100.0F))
                                    / (profession.pensionAge - age).toFloat())
                            accountNoAkassa.amount -= person.house.houseLoan.loanMonthPayment

                            person.house.houseLoan.loanAmount -= person.house.houseLoan.loanMonthPayment
                        } else {
                            person.house.loan = false
                            messageList = person.showPersonLoanReady(messageList, messageId)
                            messageId = messageList.size
                        }
                    }
                }

                //Årlig höjning av hyran
                randomValues = List(1) { Random.nextInt(1, 4) }
                person.house.houseMonthPayment -= person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)
            }

            if (person.loan) {
                accountSalary.amount -= ((person.blancoLoan.loanAmount *(person.blancoLoan.loanInterest / 100.0F))
                        / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
                accountSalary.amount -= person.blancoLoan.loanMonthPayment * 12.0F

                accountNoAkassa.amount -= ((person.blancoLoan.loanAmount * (person.blancoLoan.loanInterest / 100.0F))
                        / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
                accountNoAkassa.amount -= person.blancoLoan.loanMonthPayment * 12.0F

                person.blancoLoan.loanAmount -= person.blancoLoan.loanMonthPayment * 12.0F
            }

            messageList = accountSalary.showAccountCost(messageList, messageId)
            messageId = messageList.size

            if ( accountSalary.amount != accountNoAkassa.amount ) {
                messageList = accountNoAkassa.showAccountCost(messageList, messageId)
                messageId = messageList.size
            }

            if (person.accommodation) {
                messageList = person.showPersonAccomodation(messageList, messageId)
                messageId = messageList.size

                if (accountSalary.amount <= -500000.0F && person.house.houseAmount > 0.0F) {
                    // Skuldsanering

                    messageList = person.showSkuldsanering(messageList, messageId)
                    messageId = messageList.size

                    if (person.house.loan) {
                        //Betala av lånet
                        accountSalary.amount += (person.house.houseAmount - person.house.houseLoan.loanAmount)
                        accountNoAkassa.amount += (person.house.houseAmount - person.house.houseLoan.loanAmount)
                        person.house.houseLoan.loanAmount = 0.0F
                        person.house.houseLoan.loanInterest = 0.0F
                        person.house.houseLoan.loanMonthPayment = 0.0F
                    } else {
                        accountSalary.amount += person.house.houseAmount
                        accountNoAkassa.amount += person.house.houseAmount
                    }

                    person.accommodation = true

                    //Du skaffar dig en hyresrätt istället
                    person.house = House(0, "hyresrätt")
                    person.house.houseAmount = 0.0F

                    randomValues = List(1) { Random.nextInt(5000, 30000) }
                    person.house.houseMonthPayment = randomValues[0].toFloat()

                    messageList = person.showPersonAccomodationHire(messageList, messageId)
                    messageId = messageList.size
                }
            }
        }

        person.age = age
        person.professions = person.professions.plus(profession)
        person.employees = person.employees.plus(employee)
        person.accounts = person.accounts.plus(accountSalary)
        person.accounts = person.accounts.plus(accountNoAkassa)
        person.accounts = person.accounts.plus(accountDepot)
        person.accounts = person.accounts.plus(accountPension)

        //Store story
        for (message in messageList) {
            historyMessages = historyMessages.plus(message)
        }
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
            val blinkingRed: AnimationName = keyframes {
                0.pct {
                    color = NamedColor.hotpink
                }
                10.pct {
                    color = NamedColor.lightpink
                }
                20.pct {
                    color = NamedColor.hotpink
                }
                30.pct {
                    color = NamedColor.lightpink
                }
                40.pct {
                    color = NamedColor.hotpink
                }
                50.pct {
                    color = NamedColor.lightpink
                }
                60.pct {
                    color = NamedColor.hotpink
                }
                70.pct {
                    color = NamedColor.lightpink
                }
                80.pct {
                    color = NamedColor.hotpink
                }
                90.pct {
                    color = NamedColor.lightpink
                }
                100.pct {
                    color = NamedColor.hotpink
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
                    //Update status row
                    if (message.status.age != "") actualStatus.age = message.status.age
                    if (message.status.profession != "") actualStatus.profession = message.status.profession
                    if (message.status.employeeSalary != "") actualStatus.employeeSalary = message.status.employeeSalary
                    if (message.status.accountSalaryAmount != "") actualStatus.accountSalaryAmount = message.status.accountSalaryAmount
                    if (message.status.accountDepotAmount != "") actualStatus.accountDepotAmount = message.status.accountDepotAmount
                    if (message.status.accountPensionAmount != "") actualStatus.accountPensionAmount = message.status.accountPensionAmount
                    if (message.status.houseAmount != "") actualStatus.houseAmount = message.status.houseAmount
                    if (message.status.houseHireAmount != "") actualStatus.houseHireAmount = message.status.houseHireAmount
                    if (message.status.houseLoanAmount != "") actualStatus.houseLoanAmount = message.status.houseLoanAmount

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
                            "blinkingRed" -> {
                                p {

                                    css {
                                        animationDuration = 3.s
                                        animationName = blinkingRed
                                        animationFillMode = AnimationFillMode.both
                                        color = NamedColor.red
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
                            "grey" -> {
                                p {
                                    css {
                                        color = NamedColor.yellowgreen
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

        ShowProfessionAnimation {
            actualProfession = props.selectedProfession
        }

        p {
            button {

                key = messageList[0].id.toString()
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 10.px
                    left = 40.px

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
                            person,
                            historyMessages,
                            actualStatus
                        )
                    }
                    +props.selectedView.buttonText
                } else {
                    onClick = {
                        props.onSelectMessages(
                            reloadView.getNextView(reloadView),
                            leftMessages,
                            props.selectedProfession,
                            person,
                            historyMessages,
                            actualStatus
                        )
                    }
                    +reloadView.buttonText
                }
                +" ▶"
            }

            if (props.selectedHistory.isNotEmpty() && messageList.isNotEmpty()) {
                messageId = messageList[0].id - maxMessages

                for (mess in props.selectedHistory) {

                    if (mess.id > messageId) {
                        backupMessages = backupMessages.plus(mess)
                    }
                }
                if (messageId > 1) {
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


                        onClick = {
                            props.onSelectMessages(
                                props.selectedView,
                                backupMessages,
                                props.selectedProfession,
                                person,
                                props.selectedHistory,
                                actualStatus
                            )
                        }
                        +"◀ "
                    }
                }
            }
        }
    }
}












