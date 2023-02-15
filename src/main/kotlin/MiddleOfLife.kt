import react.FC
import react.Props
import kotlin.Float
import kotlin.random.Random
import kotlin.collections.List

external interface MiddleOfLifeProps : Props {
    var selectedProfession: Profession
    var selectedMessages: List<Message>
    var selectedLife: Life
    var selectedYear: Int

    var onSelectLife: (Profession, List<Message>, Life) -> Unit
}

val MiddleOfLife = FC<MiddleOfLifeProps> { props ->

    // Init person

    val person = props.selectedLife.person
    var profession = props.selectedProfession
    val allProfessions = profession.getAllProfession()
    var age = props.selectedLife.age
    val parent = props.selectedLife.parent
    var employee = props.selectedLife.employee
    var employeeId = employee.employeeId
    val accountPension = props.selectedLife.accountPension
    val accountSalary = props.selectedLife.accountSalary
    val accountDepot = props.selectedLife.accountDepot
    val accountTax = props.selectedLife.accountTax
    val accountNoAkassa = props.selectedLife.accountNoAkassa
    val union = person.union
    var isPandemi = props.selectedLife.isPandemi
    var isBoom = props.selectedLife.isBoom
    var isRecession = props.selectedLife.isRecession

    //Init life events

    //Init random chance to happen percentage
    val randomLifeValues: List<Int> = List(1) { Random.nextInt(0, 100) }
    var randomValues: List<Int>
    val event = Event(0)

    //Get random event in life
    val allEvents: List<Event> = event.getEvents()
    val randomEventValues: List<Int> = List(1) { Random.nextInt(0, allEvents.size) }

    //Get random event in life
    val allCostEvents: List<Event> = event.getCostEvents()
    val randomCostEventValues: List<Int> = List(1) { Random.nextInt(0, allCostEvents.size) }

    //Init story
    var messageList = props.selectedMessages
    var messageId = messageList.size
    val currentAmount: Float

    val year = props.selectedYear

    //Försörjningsstöd
    if (employee.currentSalary == 0.0F) employee.currentSalary = 4180.0F

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
            if (randomLifeValues[year - 1] < 15 || isPandemi) {

                when (allEvents[randomEventValues[0]].objectType) {
                    "burnedout" -> {
                        if (!person.isHappy) {
                            person.isSick = true

                            //How many months are you sick?
                            randomValues = List(1) { Random.nextInt(12, 36) }
                            employee.countSickMonth = randomValues[0]
                        }
                    }

                    "heartattack" -> {
                        if (!person.isHappy) {
                            person.isSick = true

                            //How many months are you sick?
                            randomValues = List(1) { Random.nextInt(1, 12) }
                            employee.countSickMonth = randomValues[0]
                        }
                    }

                    "golf" -> {
                        person.isSick = true

                        //How many months are you sick?
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        employee.countSickMonth = randomValues[0]
                    }

                    "shot" -> {
                        person.isSick = true

                        //How many months are you sick?
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        employee.countSickMonth = randomValues[0]
                    }

                    "depressed" -> {
                        if (!person.isHappy) {
                            person.isSick = true
                            person.isDepressed = true

                            //How many months are you sick?
                            randomValues = List(1) { Random.nextInt(12, 24) }
                            employee.countSickMonth = randomValues[0]
                        }
                    }
                }

                if (person.isSick) {
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
                person.isHappy = true

                messageList = person.showPersonLuck(messageList, messageId, allEvents[randomEventValues[0]].eventText)
                messageId = messageList.size
            }
        }

        "unemployed" -> {
            if ((randomLifeValues[year - 1] < 40 && !person.isMagellit) || isRecession) {
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
                    if (union.isIncomeInsurance && person.countWorkMonth >= 12) {

                        union.unEmployedSalaryAmount =
                            union.getIncomeInsurance(employee.currentSalary.toDouble())
                        messageList = union.showIncomeInsurance(messageList, messageId)
                        messageId = messageList.size

                        union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                        messageList = union.showNoAkassa(messageList, messageId)
                        messageId = messageList.size

                    } else if (union.isAkassa && person.countWorkMonth >= 12) {

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
                person.isMagellit = true

                messageList = person.showPersonMagellit(messageList, messageId)
                messageId = messageList.size
            }
        }

        "parent" -> {
            if (randomLifeValues[year - 1] < 50 && age <= 50) {
                //Babies
                parent.countBabies += 1
                parent.familySalary = parent.getIncome(employee.currentSalary)
                if (person.isMagellit) parent.familySalary += 5000.0F

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

                if (person.isMagellit)
                    parent.familySalary += 5000.0F

                messageList = parent.showVAB(messageList, messageId)
                messageId = messageList.size
            }
        }
    }

    //Get cost event in life

    when (allCostEvents[randomCostEventValues[0]].eventType) {
        "home" -> {
            if (randomLifeValues[year - 1] > 85 || !person.isAccommodation) {
                messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId, "", "")
                messageId = messageList.size

                if (person.isAccommodation && person.house.houseAmount > 0.0F) {
                    // Säljer det boendet du har
                    messageList = person.showPersonAccomodationSold(messageList, messageId)
                    messageId = messageList.size

                    if (person.house.isMortgage) {
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
                } else if (person.isAccommodation && person.house.houseType == "hire") {
                    messageList = person.showPersonAccomodationShift(messageList, messageId)
                    messageId = messageList.size
                }

                person.isAccommodation = true
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

                person.house.isMortgage = false
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
                        person.house.isMortgage = true
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
                person.isMagellit = false
                when (allCostEvents[randomCostEventValues[0]].objectType) {
                    "depot" -> {
                        isRecession = true
                        if (accountDepot.amount > 0.0F) {

                            randomValues = List(1) { Random.nextInt(1, 50) }
                            accountDepot.amount -= accountDepot.amount * randomValues[0].toFloat() / 100.0F

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
                            messageId = messageList.size
                        }
                    }

                    "home" -> {
                        isRecession = true
                        if (person.isAccommodation) {

                            randomValues = List(1) { Random.nextInt(1, 7) }
                            person.house.houseMonthPayment += person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
                            messageId = messageList.size

                        }
                    }

                    "loan" -> {
                        isRecession = true
                        if (person.house.isMortgage) {
                            randomValues = List(1) { Random.nextInt(1, 7) }
                            person.house.houseLoan.loanInterest += randomValues[0].toFloat() / 100.0F

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
                            messageId = messageList.size
                        }
                    }

                    "sick" -> {
                        person.isHappy = false
                        isPandemi = true

                        messageList = allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId, "", ".")
                        messageId = messageList.size
                    }
                }
            }
        }

        "happening" -> {
            if (randomLifeValues[year - 1] > 85) {
                isPandemi = false
                isBoom = true

                when (allCostEvents[randomCostEventValues[0]].objectType) {
                    "depot" -> {
                        if (accountDepot.amount > 0.0F) {

                            randomValues = List(1) { Random.nextInt(1, 50) }
                            accountDepot.amount += accountDepot.amount * randomValues[0].toFloat() / 100.0F

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
                            messageId = messageList.size
                        }
                    }

                    "home" -> {
                        if (person.isAccommodation) {

                            randomValues = List(1) { Random.nextInt(1, 7) }
                            person.house.houseMonthPayment -= person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
                            messageId = messageList.size
                        }
                    }

                    "loan" -> {
                        if (person.house.isMortgage) {
                            randomValues = List(1) { Random.nextInt(1, 2) }
                            person.house.houseLoan.loanInterest -= randomValues[0].toFloat() / 100.0F

                            messageList = allCostEvents[randomCostEventValues[0]].showEvent(
                                messageList,
                                messageId,
                                "",
                                " med ${randomValues[0].formatDecimalSeparator()}%."
                            )
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

    messageList = person.showWorkingLife(age, messageList, messageId)
    messageId = messageList.size

    //Löneökning
    if (employee.currentSalary != 4180.0F &&
        (employee.countSickMonth + union.countUnEmployeeMonth + parent.countFamilyMonth == 0)
    ) {

        randomValues = if (isBoom) {
            List(1) { Random.nextInt(40, 98) }
        } else if (isRecession) {
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
    if (person.isAccommodation) {

        accountSalary.amount -= person.house.houseMonthPayment
        accountNoAkassa.amount -= person.house.houseMonthPayment

        if (person.house.isMortgage) {
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
                    person.house.isMortgage = false
                    messageList = person.showPersonLoanReady(messageList, messageId)
                    messageId = messageList.size
                }
            }
        }

        //Årlig höjning av hyran
        randomValues = List(1) { Random.nextInt(1, 4) }
        person.house.houseMonthPayment -= person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)
    }

    if (person.isMortgage) {
        accountSalary.amount -= ((person.blancoLoan.loanAmount * (person.blancoLoan.loanInterest / 100.0F))
                / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
        accountSalary.amount -= person.blancoLoan.loanMonthPayment * 12.0F

        accountNoAkassa.amount -= ((person.blancoLoan.loanAmount * (person.blancoLoan.loanInterest / 100.0F))
                / ((profession.pensionAge - age) * 12).toFloat()) * 12.0F
        accountNoAkassa.amount -= person.blancoLoan.loanMonthPayment * 12.0F

        person.blancoLoan.loanAmount -= person.blancoLoan.loanMonthPayment * 12.0F
    }

    messageList = accountSalary.showAccountCost(messageList, messageId)
    messageId = messageList.size

    if (accountSalary.amount != accountNoAkassa.amount) {
        messageList = accountNoAkassa.showAccountCost(messageList, messageId)
        messageId = messageList.size
    }

    if (person.isAccommodation) {
        messageList = person.showPersonAccomodation(messageList, messageId)
        messageId = messageList.size

        if (accountSalary.amount <= -500000.0F && person.house.houseAmount > 0.0F) {
            // Skuldsanering

            messageList = person.showSkuldsanering(messageList, messageId)
            messageId = messageList.size

            if (person.house.isMortgage) {
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

            person.isAccommodation = true

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













