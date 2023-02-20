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
    var selectedEvent: Event

    var onSelectMessages: (View, List<Message>, Profession, Person, List<Message>, Status, Life) -> Unit
}

val StartMiddleOfLife = FC<StartMiddleOfLifeProps> { props ->

    // Init person life
    var life: Life = props.selectedLife
    var currentLife: Life
    val person: Person = props.selectedPerson
    var profession: Profession = props.selectedProfession

    //Init story
    var messageList: List<Message> = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var historyMessages: List<Message> = props.selectedHistory
    var backupMessages: List<Message> = emptyList()
    val currentStatus: Status = props.selectedStatus
    var messageId = 0
    val maxMessages = 6
    val isDebugOn = false

    if (life.age == 0) {
        // starting your workingstory

        life.age = person.age
        person.professions = person.professions.plus(profession)
        life.employee.title = profession.title
        life.employee.firstSalary = profession.salary * person.age.toFloat()
        life.employee.currentSalary = profession.salary * person.age.toFloat()

        messageList = life.employee.showEmployeeSalary(0.0F, messageList, messageId)
        messageId = messageList.size

        messageList = person.showWorkingLife(life.age, messageList, messageId)
        messageId = messageList.size
    }

    if (life.age >= person.age && life.age <= profession.pensionAge) {

        //Init life
        life.person = person
        life.professionId = profession.id
        life.messageList = messageList

        //Loop middleOfLife until question
        currentLife = middleOfLife(life, props.selectedEvent)

        //Get what happened in your life
        life = currentLife
        profession = getProfession(currentLife.professionId)
        messageList = currentLife.messageList
        messageId = messageList.size

        if (life.isQuestion) {
            life.isQuestion = false
            div {

                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = 100.px
                        left = 10.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +"Du är deprimerad och sitter med en samtalsterapeut, vad blir du glad av? frågar hon."
                }

                p {
                    button {

                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = 150.px
                            left = 10.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = 18.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("luck"),
                                messageList,
                                profession,
                                life.person,
                                historyMessages,
                                currentStatus,
                                life
                            )
                        }
                        +"Vad svarar du?"
                        +" ▶"
                    }
                }
            }

            ShowStatusRow {
                actualAge = life.person.age.toString()
                actualName = life.person.name
                actualPension = (life.person.pension * 100).toString()
                actualProfession = props.selectedProfession.title
                firstSalary = life.employee.firstSalary.toInt().formatDecimalSeparator()
                actualSalary = ""
                actualSalaryAmount = ""
                actualDepotAmount = ""
                actualPensionAmount = ""
                actualHireAmount = ""
                actualHouseAmount = ""
                actualLoanAmount = ""
            }
        }

        if (life.isNewProfession) {
            life.isNewProfession = false
            div {

                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = 100.px
                        left = 10.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +"Dags att fixa nytt jobb! tänk på ditt mål:"
                }

                p {
                    button {

                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = 150.px
                            left = 10.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = 18.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("question"),
                                messageList,
                                profession,
                                life.person,
                                historyMessages,
                                currentStatus,
                                life
                            )
                        }
                        +"Vilket yrke väljer du?"
                        +" ▶"
                    }
                }
            }

            ShowStatusRow {
                actualAge = life.person.age.toString()
                actualName = life.person.name
                actualPension = (life.person.pension * 100).toString()
                actualProfession = props.selectedProfession.title
                firstSalary = life.employee.firstSalary.toInt().formatDecimalSeparator()
                actualSalary = ""
                actualSalaryAmount = ""
                actualDepotAmount = ""
                actualPensionAmount = ""
                actualHireAmount = ""
                actualHouseAmount = ""
                actualLoanAmount = ""
            }
        }
    }

    if (isDebugOn) {
        div {
            css {
                display = Display.block
                position = Position.absolute
                top = 800.px
                left = 50.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }

            ShowMessage {
                selectedMessage = Message(
                    5,
                    "Nu startar vi steg 5 life.age: ${life.age} pension: ${profession.pensionAge} ",
                    "",
                    ""
                )
            }
        }
    }

    if (life.age >= profession.pensionAge) {
        life.person.pensionAge = life.age

        if (isDebugOn) {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 850.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 6 age life.age: ${life.age} pension: ${life.person.pensionAge} ",
                        "",
                        ""
                    )
                }
            }
        }

        if (historyMessages.isEmpty()) {
            //Store story
            for (message in messageList) {
                historyMessages = historyMessages.plus(message)
            }
        }

        //Show story
        if (messageList.isNotEmpty()) {
            if (isDebugOn) {
                div {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = 900.px
                        left = 50.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }

                    ShowMessage {
                        selectedMessage = Message(
                            5,
                            "Nu startar vi steg 7 message: ${messageList[0].messageText} ",
                            "",
                            ""
                        )
                    }
                }
            }

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
                        if (message.status.countCats != "") currentStatus.countCats = message.status.countCats
                        if (message.status.countDogs != "") currentStatus.countDogs = message.status.countDogs
                        if (message.status.countHorses != "") currentStatus.countHorses = message.status.countHorses
                        if (message.status.countCars != "") currentStatus.countCars = message.status.countCars
                        if (message.status.countBikes != "") currentStatus.countBikes = message.status.countBikes
                        if (message.status.countParties != "") currentStatus.countParties = message.status.countParties
                        if (message.status.countAlone != "") currentStatus.countAlone = message.status.countAlone
                        if (message.status.countFishes != "") currentStatus.countFishes = message.status.countFishes
                        if (message.status.countFriends != "") currentStatus.countFriends = message.status.countFriends
                        if (message.status.countBabies != "") currentStatus.countBabies = message.status.countBabies
                        if (message.status.countMoney != "") currentStatus.countMoney = message.status.countMoney
                        if (message.status.countStrong != "") currentStatus.countStrong = message.status.countStrong

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

                    if (leftMessages.size >= maxMessages) {
                    //if (life.year <= profession.pensionAge) {
                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("reload"),
                                leftMessages,
                                props.selectedProfession,
                                life.person,
                                historyMessages,
                                currentStatus,
                                life
                            )
                        }
                        +props.selectedView.getNewView("reload").buttonText


                    } else {
                        //Pension life
                        life.person.age = life.age
                        life.person.professions = life.person.professions.plus(profession)
                        life.person.employees = life.person.employees.plus(life.employee)
                        life.person.accounts = life.person.accounts.plus(life.accountSalary)
                        life.person.accounts = life.person.accounts.plus(life.accountNoAkassa)
                        life.person.accounts = life.person.accounts.plus(life.accountDepot)
                        life.person.accounts = life.person.accounts.plus(life.accountPension)

                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("pension"),
                                leftMessages,
                                props.selectedProfession,
                                life.person,
                                historyMessages,
                                currentStatus,
                                life
                            )
                        }
                        +props.selectedView.getNewView("pension").buttonText
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
                                    life.person,
                                    historyMessages,
                                    currentStatus,
                                    life
                                )
                            }
                            +"◀ "
                        }

                    }
                }
            }

            if (currentStatus.age == "") currentStatus.age = life.person.age.toString()
            if (currentStatus.profession == "") currentStatus.profession = props.selectedProfession.title
            ShowStatusRow {
                actualAge = currentStatus.age
                actualName = life.person.name
                actualPension = (life.person.pension * 100).toString()
                actualProfession = currentStatus.profession
                firstSalary = life.employee.firstSalary.toInt().formatDecimalSeparator()
                actualSalary = currentStatus.employeeSalary
                actualSalaryAmount = currentStatus.accountSalaryAmount
                actualDepotAmount = currentStatus.accountDepotAmount
                actualPensionAmount = currentStatus.accountPensionAmount
                actualHireAmount = currentStatus.houseHireAmount
                actualHouseAmount = currentStatus.houseAmount
                actualLoanAmount = currentStatus.houseLoanAmount
                actualCats = currentStatus.countCats
                actualDogs = currentStatus.countDogs
                actualHorses = currentStatus.countHorses
                actualCars = currentStatus.countCars
                actualBabies = currentStatus.countBabies
                actualBikes = currentStatus.countBikes
                actualParties = currentStatus.countParties
                actualAlone = currentStatus.countAlone
                actualFishes = currentStatus.countFishes
                actualFriends = currentStatus.countFriends
                actualMoney = currentStatus.countMoney
                actualStrong = currentStatus.countStrong
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

fun middleOfLife(life: Life, selectedEvent: Event): Life {
    val currentLife = life
    val person = life.person
    var profession = getProfession(life.professionId)
    val allProfessions = profession.getAllProfession()
    var age = life.age
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
    var lifeChance: Int
    val randomLifeValues: List<Int> = List(100) { Random.nextInt(0, 100) }
    var randomValues: List<Int>

    //Get random event in life
    var event = selectedEvent
    val allEvents: List<Event> = event.getEvents()
    var randomEventValues: List<Int>

    //Get random event in life
    var costevent = Event(0)
    val allCostEvents: List<Event> = costevent.getCostEvents()
    var randomCostEventValues: List<Int>

    var currentAmount: Float

    while (age < profession.pensionAge && !currentLife.isQuestion && !currentLife.isNewProfession) {
        //Init count month
        parent.countFamilyMonth = 0
        parent.familySalary = 0.0F
        employee.countSickMonth = 0
        employee.sickSalary = 0.0F
        person.union.countUnEmployeeMonth = 0
        person.isSick = false
        lifeChance = randomLifeValues[age - 1]
        currentLife.isQuestion = false
        currentLife.isNewProfession = false

        //Försörjningsstöd
        if (employee.currentSalary == 0.0F) employee.currentSalary = 4180.0F

        if (!event.isSelected) {
            randomEventValues = List(1) { Random.nextInt(0, allEvents.size) }
            event = allEvents[randomEventValues[0]]
        } else {
            person.events = person.events.plus(event)
        }

        when (event.eventType) {
            "depot" -> {
                //Bonus
                if (lifeChance < profession.randomBonus && person.countWorkMonth >= 12 && employee.currentSalary > 0.0) {

                    //Get value och financial instruments
                    randomValues = List(1) { Random.nextInt(10000, 50000) }
                    accountDepot.amount += randomValues[0].toFloat()

                    messageList = accountDepot.showDepotAmount(messageList, messageId)
                    messageId = messageList.size

                }
            }

            "sick" -> {
                //Sjuk
                if (lifeChance < profession.randomSick ||
                    (isPandemi && event.objectType == "pandemi")
                ) {

                    when (event.objectType) {
                        "pandemi" -> {
                            if (isPandemi) {
                                person.isSick = true
                                person.isHappy = false
                                isPandemi = false

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(12, 36) }
                                employee.countSickMonth = randomValues[0]
                            }
                        }

                        "burnedout" -> {
                            if (!person.isHappy) {
                                person.isSick = true
                                currentLife.isQuestion = true

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
                                currentLife.isQuestion = true

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(12, 24) }
                                employee.countSickMonth = randomValues[0]
                            }
                        }
                    }

                    if (person.isSick) {
                        //Save event in story
                        messageList = event.showEvent(messageList, messageId, "Åh nej! ", "")
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
                if (lifeChance < profession.randomLuck || event.isSelected) {
                    event.isSelected = false
                    person.isHappy = true
                    when (event.objectType) {
                        "cat" -> {
                            person.countCats += 1
                        }

                        "strong" -> {
                            person.countStrong += 1
                        }

                        "friend" -> {
                            person.countFriends += 1
                        }

                        "alone" -> {
                            person.countAlone += 1
                        }

                        "dog" -> {
                            person.countDogs += 1
                        }

                        "fish" -> {
                            person.countFishes += 1
                        }

                        "party" -> {
                            person.countParties += 1
                        }

                        "horse" -> {
                            person.countHorses += 1
                        }

                        "money" -> {
                            person.countMoney += 1
                        }

                        "car" -> {
                            person.countCars += 1
                        }

                        "bike" -> {
                            person.countBikes += 1
                        }
                    }

                    messageList = person.showPersonLuck(messageList, messageId, event)
                    messageId = messageList.size
                }
            }

            "unemployed" -> {
                if ((lifeChance < profession.randomUnemployed && !person.isMagellit) || isRecession || event.isSelected) {
                    union.countUnEmployeeMonth = 0
                    union.unEmployedSalaryAmount = 0.0
                    union.noAkassaSalaryAmount = 0.0
                    event.isSelected = false

                    when (event.objectType) {
                        "unemployed" -> {
                            //Varslad
                            currentLife.isNewProfession = true

                            //Chans till avgångsvederlag om du jobbat mer än 12 månader
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < 30 && person.countWorkMonth >= 12) {

                                //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                                currentAmount = employee.currentSalary * (person.countWorkMonth / 12).toFloat()
                                accountSalary.amount += currentAmount
                                accountNoAkassa.amount += currentAmount

                                messageList = employee.showSeverancePay(currentAmount, messageList, messageId)
                                messageId = messageList.size
                            }

                            //Hur många dagar är du arbetslös, 22 dagar per månad får man ersättning
                            randomValues = List(1) { Random.nextInt(22, 300) }
                            union.countUnEmployeeMonth = randomValues[0] / 22

                            //Endast 300 dagar kan man få a-kassa

                            messageList = union.showCountUnEmployeeMonth(messageList, messageId)
                            messageId = messageList.size

                            //Vilken försäkring kan du få
                            if (employee.currentSalary >= 60000.0F) {
                                //Arbetslöshetssersättning = procent av lönen i 200 dagar
                                union.isIncomeInsurance = true
                                union.isExtraInsurance = true
                            } else if (employee.currentSalary >= union.maxSalaryAkassa100) {
                                //Arbetslöshetssersättning = procent av lönen max 60000 i lön
                                union.isIncomeInsurance = true
                            } else {
                                union.isAkassa = true
                            }

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

                        "employed" -> {
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
                }
            }

            "magellit" -> {
                //Magellit
                if (lifeChance < 10) {
                    person.isMagellit = true

                    messageList = person.showPersonMagellit(messageList, messageId)
                    messageId = messageList.size
                }
            }

            "parent" -> {
                if (lifeChance < 50 && age <= 50) {
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
        randomCostEventValues = List(1) { Random.nextInt(0, allCostEvents.size) }
        costevent = allCostEvents[randomCostEventValues[0]]

        when (costevent.eventType) {
            "home" -> {
                if (lifeChance > 85 || !person.isAccommodation) {
                    messageList = costevent.showEvent(messageList, messageId, "", "")
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
                    when (costevent.objectType) {
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
                            // Ta lån?
                            person.house.isMortgage = true
                            person.house.houseLoan.loanAmount =
                                person.house.houseAmount - (accountDepot.amount + accountSalary.amount)

                            randomValues = List(1) { Random.nextInt(1, 4) } //Ränta
                            person.house.houseLoan.loanInterest = randomValues[0].toFloat()
                            person.house.houseLoan.ageWhenPayed = profession.pensionAge - age
                            person.house.houseLoan.loanMonthPayment = person.house.houseLoan.loanAmount /
                                    ( person.house.houseLoan.ageWhenPayed.toFloat() * 12.0F )

                            if ((employee.currentSalary - 5000.0F) < person.house.houseLoan.loanMonthPayment) {
                                //No loan
                                person.house.isMortgage = false
                                person.house.houseLoan.loanAmount = 0.0F
                                person.house.houseLoan.loanMonthPayment = 0.0F
                                person.house.houseLoan.loanInterest  = 0.0F

                                person.isAccommodation = false
                                person.house.houseAmount = 0.0F
                                person.house.houseMonthPayment = 0.0F

                                messageList = person.showPersonNoHouseLoan(messageList, messageId)
                                messageId = messageList.size
                            } else {
                                // loan
                                accountSalary.amount = 0.0F
                                accountNoAkassa.amount = 0.0F
                                accountDepot.amount = 0.0F

                                messageList = person.showPersonGetHouseLoan(messageList, messageId)
                                messageId = messageList.size
                            }
                        }
                    }
                }
            }

            "accident" -> {
                if (lifeChance > 85) {
                    person.isMagellit = false
                    when (costevent.objectType) {
                        "depot" -> {
                            isRecession = true
                            if (accountDepot.amount > 0.0F) {

                                randomValues = List(1) { Random.nextInt(1, 50) }
                                accountDepot.amount -= accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                messageList = costevent.showEvent(
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

                                messageList = costevent.showEvent(
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

                                messageList = costevent.showEvent(
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

                            messageList = costevent.showEvent(messageList, messageId, "", ".")
                            messageId = messageList.size
                        }
                    }
                }
            }

            "happening" -> {
                if (lifeChance > 85) {
                    isPandemi = false
                    isBoom = true

                    when (costevent.objectType) {
                        "depot" -> {
                            if (accountDepot.amount > 0.0F) {

                                randomValues = List(1) { Random.nextInt(1, 50) }
                                accountDepot.amount += accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                messageList = costevent.showEvent(
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

                                messageList = costevent.showEvent(
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

                                messageList = costevent.showEvent(
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
            (employee.countSickMonth + union.countUnEmployeeMonth + parent.countFamilyMonth == 0)) {

            randomValues = if (isBoom) {
                List(1) { Random.nextInt(40, 98) }
            } else {
                List(1) { Random.nextInt(10, 30) }
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

        messageList = accountSalary.showAccountAmount((age - person.age + 1), messageList, messageId)
        messageId = messageList.size

        if (accountDepot.amount > 0.0F) {
            if (accountSalary.amount < 0.0F) {
                messageList = accountDepot.showSkuldsanering(messageList, messageId)
                messageId = messageList.size

                accountSalary.amount += accountDepot.amount
                accountDepot.amount = 0.0F
            }
            messageList = accountDepot.showAccountAmount((age - person.age + 1), messageList, messageId)
            messageId = messageList.size
        }

        if (age >= 50) {
            messageList = accountPension.showAccountAmount((age - person.age + 1), messageList, messageId)
            messageId = messageList.size

            messageList = accountTax.showAccountAmount((age - person.age + 1), messageList, messageId)
            messageId = messageList.size
        }

        //Dra av diverse levnadskostnader för mat är 3000 genomsnittet
        randomValues = List(1) { Random.nextInt(5000, 10000) }
        accountSalary.amount -= randomValues[0].toFloat() * 12.0F
        accountNoAkassa.amount -= randomValues[0].toFloat() * 12.0F

        //Barn kostar pengar
        accountSalary.amount -= parent.costBabies()
        accountNoAkassa.amount  -= parent.costBabies()

        //Hobbies kostar pengar
        accountSalary.amount -= person.costHobbies()
        accountNoAkassa.amount  -= person.costHobbies()

        //Dra av kostnad för boendet
        if (person.isAccommodation) {

            accountSalary.amount -= person.house.houseMonthPayment
            accountNoAkassa.amount -= person.house.houseMonthPayment

            if (person.house.isMortgage) {
                for (month in 1..12) {
                    if (person.house.houseLoan.loanAmount >= person.house.houseLoan.loanMonthPayment) {
                        //Ränta
                        accountSalary.amount -= person.house.houseLoan.calculateInterest()
                        //Avbetalning
                        accountSalary.amount -= person.house.houseLoan.loanMonthPayment

                        //No A-kassa example
                        accountNoAkassa.amount -= person.house.houseLoan.calculateInterest()
                        accountNoAkassa.amount -= person.house.houseLoan.loanMonthPayment

                        person.house.houseLoan.loanAmount -= person.house.houseLoan.loanMonthPayment
                    } else {
                        //Avbetalning
                        accountSalary.amount -= person.house.houseLoan.loanAmount
                        accountNoAkassa.amount -= person.house.houseLoan.loanAmount
                        person.house.houseLoan.loanAmount = 0.0F
                    }
                }

                if (person.house.houseLoan.loanAmount == 0.0F) {
                    person.house.isMortgage = false
                    messageList = person.showPersonLoanReady(messageList, messageId)
                    messageId = messageList.size
                }
            }

            //Årlig höjning av hyran
            person.house.houseMonthPayment += person.house.raiseTheRent()
        }

        //Blancolån används ej ännu
        if (person.isMortgage) {
            for (month in 1..12) {
                if (person.blancoLoan.loanAmount >= person.blancoLoan.loanMonthPayment) {
                    accountSalary.amount -= person.blancoLoan.calculateInterest()
                    accountSalary.amount -= person.blancoLoan.loanMonthPayment

                    accountNoAkassa.amount -= person.blancoLoan.calculateInterest()
                    accountNoAkassa.amount -= person.blancoLoan.loanMonthPayment

                    person.blancoLoan.loanAmount -= person.blancoLoan.loanMonthPayment

                } else {
                    //Avbetalning
                    accountSalary.amount -= person.blancoLoan.loanAmount
                    accountNoAkassa.amount -= person.blancoLoan.loanAmount
                    person.blancoLoan.loanAmount = 0.0F

                }
            }
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

    //Store story
    currentLife.age = age
    currentLife.person = person
    currentLife.professionId = profession.id
    currentLife.parent = parent
    currentLife.employee = employee
    currentLife.accountPension = accountPension
    currentLife.accountSalary = accountSalary
    currentLife.accountDepot = accountDepot
    currentLife.accountTax = accountTax
    currentLife.accountNoAkassa = accountNoAkassa
    currentLife.person.union = union
    currentLife.isPandemi = isPandemi
    currentLife.isBoom = isBoom
    currentLife.isRecession = isRecession
    currentLife.messageList = messageList

    return currentLife
}













