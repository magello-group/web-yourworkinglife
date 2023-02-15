import csstype.*
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

external interface StartMiddleOfLifeProps : Props {
    var selectedView: View
    var selectedProfession: Profession
    var selectedPerson: Person
    var selectedMessages: List<Message>
    var selectedHistory: List<Message>
    var selectedStatus: Status
    var selectedLife: Life

    var onSelectMessages: (View, List<Message>, Profession, Person, List<Message>, Status, Life) -> Unit
}

val StartMiddleOfLife = FC<StartMiddleOfLifeProps> { props ->

    // Init person
    var currentLife = props.selectedLife
    var reloadView: View = props.selectedView.getNextView()
    var life: Life = props.selectedLife
    var person: Person = props.selectedPerson
    var profession: Profession = props.selectedProfession

    //Init story
    var messageList: List<Message> = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var historyMessages: List<Message> = props.selectedHistory
    var backupMessages: List<Message> = emptyList()
    val currentStatus: Status = props.selectedStatus
    var messageId = 0
    val maxMessages = 6
    val isDebugOn = true

    div {
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
        if (isDebugOn) {
            ShowMessage {
                selectedMessage = Message(
                    1,
                    "Nu startar vi steg 1",
                    "",
                    ""
                )
            }
        }

        if (life.year == 0) {
            // starting your workingstory
            if (isDebugOn) {
                ShowMessage {
                    selectedMessage = Message(
                        2,
                        "Nu startar vi steg 2",
                        "",
                        ""
                    )
                }
            }
            life.age = person.age
            life.year = person.age
            person.professions = person.professions.plus(profession)
            life.employee.title = profession.title
            life.employee.firstSalary = profession.salary * person.age.toFloat()
            life.employee.currentSalary = profession.salary * person.age.toFloat()

            messageList = life.employee.showEmployeeSalary(0.0F, messageList, messageId)
            messageId = messageList.size

            messageList = person.showWorkingLife(life.age, messageList, messageId)
            messageId = messageList.size
        }

        if (isDebugOn) {
            ShowMessage {
                selectedMessage = Message(
                    3,
                    "Nu startar vi steg 3 year: ${life.year} person.age: ${person.age} pension: ${profession.pensionAge} ",
                    "",
                    ""
                )
            }
        }
    }

    if (life.year >= person.age && life.year <= profession.pensionAge) {
        //Loop all working years until a question
        div {
            css {
                display = Display.block
                position = Position.absolute
                top = 220.px
                left = 10.px

                color = NamedColor.green
                borderColor = NamedColor.white
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (isDebugOn) {
                ShowMessage {
                    selectedMessage = Message(
                        messageId,
                        "Nu startar vi steg 4",
                        "",
                        ""
                    )
                }
            }

            life.person = person
            /*
            currentLife = middleOfLife(life)

            profession = getProfession(currentLife.professionId)
            life.professionId = profession.id

            if (isDebugOn) {
                ShowMessage {
                    selectedMessage = Message(
                        messageId,
                        "Nu startar vi steg 5",
                        "",
                        ""
                    )
                }
            }

            if (currentLife.person.isDepressed) {

                if (isDebugOn) {
                    ShowMessage {
                        selectedMessage = Message(
                            messageId,
                            "Nu startar vi steg 6",
                            "",
                            ""
                        )
                    }
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

                        if (life.year <= profession.pensionAge) {
                            onClick = {
                                props.onSelectMessages(
                                    props.selectedView.getNewView("question"),
                                    leftMessages,
                                    profession,
                                    currentLife.person,
                                    historyMessages,
                                    currentStatus,
                                    currentLife
                                )
                            }
                            +"Gör ditt val"
                        }
                    }
                }
            }

             */
        }
    }

    if (life.year >= profession.pensionAge) {

        //Store story
        for (message in messageList) {
            historyMessages = historyMessages.plus(message)
        }

        //Show story
        if (messageList.isNotEmpty()) {

            div {
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
                        if (message.status.age != "") currentStatus.age = message.status.age
                        if (message.status.employeeSalary != "") currentStatus.employeeSalary =
                            message.status.employeeSalary
                        if (message.status.accountSalaryAmount != "") currentStatus.accountSalaryAmount =
                            message.status.accountSalaryAmount
                        if (message.status.accountDepotAmount != "") currentStatus.accountDepotAmount =
                            message.status.accountDepotAmount
                        if (message.status.accountPensionAmount != "") currentStatus.accountPensionAmount =
                            message.status.accountPensionAmount
                        if (message.status.houseAmount != "") currentStatus.houseAmount = message.status.houseAmount
                        if (message.status.houseHireAmount != "") currentStatus.houseHireAmount =
                            message.status.houseHireAmount
                        if (message.status.houseLoanAmount != "") currentStatus.houseLoanAmount =
                            message.status.houseLoanAmount
                        if (message.status.profession != "") currentStatus.profession = message.status.profession

                        ShowMessage {
                            selectedMessage = message
                        }

                    } else {
                        leftMessages = leftMessages.plus(message)
                    }
                }
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

                    //if (leftMessages.size >= maxMessages) {
                    if (life.year <= profession.pensionAge) {
                        onClick = {
                            props.onSelectMessages(
                                props.selectedView,
                                leftMessages,
                                props.selectedProfession,
                                currentLife.person,
                                historyMessages,
                                currentStatus,
                                currentLife
                            )
                        }
                        +props.selectedView.buttonText


                    } else {
                        //Pension life
                        currentLife.person.age = life.age
                        currentLife.person.professions = currentLife.person.professions.plus(profession)
                        currentLife.person.employees = currentLife.person.employees.plus(currentLife.employee)
                        currentLife.person.accounts = currentLife.person.accounts.plus(currentLife.accountSalary)
                        currentLife.person.accounts = currentLife.person.accounts.plus(currentLife.accountNoAkassa)
                        currentLife.person.accounts = currentLife.person.accounts.plus(currentLife.accountDepot)
                        currentLife.person.accounts = currentLife.person.accounts.plus(currentLife.accountPension)

                        onClick = {
                            props.onSelectMessages(
                                reloadView.getNextView(),
                                leftMessages,
                                props.selectedProfession,
                                currentLife.person,
                                historyMessages,
                                currentStatus,
                                currentLife
                            )
                        }
                        +reloadView.buttonText
                    }
                    +" ▶"
                }

                if (messageList.isNotEmpty()) {
                    if (historyMessages.isNotEmpty() && messageList[0].id >= (maxMessages + 1)) {
                        messageId = messageList[0].id - (maxMessages + 1)

                        for ((messageIndex, message) in historyMessages.withIndex()) {
                            if (messageIndex >= messageId) {
                                backupMessages = backupMessages.plus(message)
                            }
                        }

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
                                    currentLife.person,
                                    historyMessages,
                                    currentStatus,
                                    currentLife
                                )
                            }

                            +"◀ "
                        }

                    }
                }
            }

            if (currentStatus.age == "") currentStatus.age = currentLife.person.age.toString()
            if (currentStatus.profession == "") currentStatus.profession = props.selectedProfession.title
            ShowStatusRow {
                actualAge = currentStatus.age
                actualName = currentLife.person.name
                actualPension = (currentLife.person.pension * 100).toString()
                actualProfession = currentStatus.profession
                actualSalary = currentStatus.employeeSalary
                actualSalaryAmount = currentStatus.accountSalaryAmount
                actualDepotAmount = currentStatus.accountDepotAmount
                actualPensionAmount = currentStatus.accountPensionAmount
                actualHireAmount = currentStatus.houseHireAmount
                actualHouseAmount = currentStatus.houseAmount
                actualLoanAmount = currentStatus.houseLoanAmount
            }
        }
    }
}

fun getProfession(professionId: Int): Profession {

    var selectedProfession = Profession(0)
    val professions: List<Profession> = selectedProfession.getAllProfession()

    for (profession in professions) {
        if (profession.id == professionId) {
            selectedProfession = profession
        }
    }

    return selectedProfession
}

fun middleOfLife(life: Life): Life {
    val currentLife = life
    val person = life.person
    var profession = getProfession(life.professionId)
    val allProfessions = profession.getAllProfession()
    var age = life.age
    var year = life.year
    val parent = life.parent
    var employee = life.employee
    var employeeId = employee.employeeId
    val accountPension = life.accountPension
    val accountSalary = life.accountSalary
    val accountDepot = life.accountDepot
    val accountTax = life.accountTax
    val accountNoAkassa = life.accountNoAkassa
    val union = person.union
    var isPandemi = life.isPandemi
    var isBoom = life.isBoom
    var isRecession = life.isRecession

    //Init story
    var messageList = life.messageList
    var messageId = messageList.size

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

    var currentAmount: Float
    currentLife.isQuestion = false

    while (!currentLife.isQuestion) {
        //Init count month
        parent.countFamilyMonth = 0
        parent.familySalary = 0.0F
        employee.countSickMonth = 0
        employee.sickSalary = 0.0F
        person.union.countUnEmployeeMonth = 0
        person.isSick = false

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
                                currentLife.isQuestion = true

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

                    messageList =
                        person.showPersonLuck(messageList, messageId, allEvents[randomEventValues[0]].eventText)
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

                            messageList =
                                allCostEvents[randomCostEventValues[0]].showEvent(messageList, messageId, "", ".")
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
            }
        }
    }

    currentLife.year += 1
    currentLife.age = age
    currentLife.person = life.person
    currentLife.professionId = profession.id
    currentLife.parent = life.parent
    currentLife.employee = life.employee
    currentLife.accountPension = life.accountPension
    currentLife.accountSalary = life.accountSalary
    currentLife.accountDepot = life.accountDepot
    currentLife.accountTax = life.accountTax
    currentLife.accountNoAkassa = life.accountNoAkassa
    currentLife.person.union = person.union
    currentLife.isPandemi = life.isPandemi
    currentLife.isBoom = life.isBoom
    currentLife.isRecession = life.isRecession

    //Init story
    currentLife.messageList = life.messageList
    currentLife.year = life.year

    return currentLife
}













