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
    var selectedStyle: Style

    var onSelectMessages: (View, List<Message>, Profession, Person, List<Message>, Status, Life) -> Unit
}

val StartMiddleOfLife = FC<StartMiddleOfLifeProps> { props ->

    // Init person life
    var life: Life = props.selectedLife
    val currentLife: Life
    val person: Person = props.selectedPerson
    var profession: Profession = props.selectedProfession

    //Init story
    var messageList: List<Message> = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var historyMessages: List<Message> = props.selectedHistory
    var backupMessages: List<Message> = emptyList()
    val currentStatus: Status = props.selectedStatus
    var messageId = props.selectedLife.lastMessageId
    var lastDisplayedMessageId = 0
    val maxMessages = 10
    val isDebugOn = false
    var topPX = 930


    if (life.firstStep || historyMessages.isEmpty()) {
        life.firstStep = false
        // starting your workingstory

        life.age = person.age
        person.isAccommodation = false
        life.employee.title = profession.title
        life.employee.firstSalary = profession.salary * person.age.toFloat()
        life.employee.currentSalary = profession.salary * person.age.toFloat()
        life.firstSalary = profession.salary * person.age.toFloat()

        //Store start of woking life story
        messageId = 0
        messageList = life.employee.showEmployeeSalary(0.0F, messageList, messageId)
        messageId = messageList[messageList.size-1].id

        messageList = person.showWorkingLife(life.age, messageList, messageId)
        messageId = messageList[messageList.size-1].id

        if (isDebugOn) {
            ShowDebugInfo {
                selectedDebugMessage = "steg1"
                selectedStyle = props.selectedStyle
                selectedLife = life
                selectedPerson = person
            }
        }
    }

    if (life.age >= person.age && life.age <= profession.pensionAge) {

        //Init life
        life.person = person
        life.professionId = profession.id
        life.messageList = messageList
        life.lastMessageId = messageId

        if (isDebugOn) {
            ShowDebugInfo {
                selectedDebugMessage = "steg2"
                selectedStyle = props.selectedStyle
                selectedLife = life
                selectedPerson = person
                selectedEvent = props.selectedEvent
            }
        }

        //Loop middleOfLife until question
        currentLife = middleOfLife(life, props.selectedEvent)

        //Get what happened in your life
        life = currentLife
        profession = getProfession(currentLife.professionId)
        messageList = currentLife.messageList
        messageId = currentLife.lastMessageId

    }

    if (isDebugOn) {
        ShowDebugInfo {
            selectedDebugMessage = "steg3"
            selectedStyle = props.selectedStyle
            selectedLife = life
            selectedPerson = person
            selectedEvent = props.selectedEvent
            selectedProfession = profession
        }
    }

    if (life.age >= profession.pensionAge) {
        life.person.pensionAge = life.age
    }
    if (isDebugOn) {
        ShowDebugInfo {
            selectedDebugMessage = "steg4"
            selectedStyle = props.selectedStyle
            selectedLife = life
            selectedPerson = life.person
            selectedEvent = props.selectedEvent
            selectedProfession = profession
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
            ShowDebugInfo {
                selectedDebugMessage = "steg5"
                selectedStyle = props.selectedStyle
                selectedLife = life
                selectedPerson = life.person
                selectedEvent = props.selectedEvent
                selectedProfession = profession
                selectedMessage = messageList[0]
            }
        }

        div {
            css {
                display = Display.block
                position = Position.absolute
                top = props.selectedStyle.topPXStory01.px
                left = props.selectedStyle.leftPXStory01.px

                color = NamedColor.green
                borderColor = NamedColor.white
                fontSize = props.selectedStyle.fontMedium.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
                width = 600.px
            }

            for ((messageIndex, message) in messageList.withIndex()) {
                if ((life.isQuestion && message.id > life.questionMessageId) ||
                    (life.isNewProfession && message.id > life.professionMessageId)
                ) {
                    leftMessages = leftMessages.plus(message)
                } else if (messageIndex < maxMessages) {
                    //Update status row
                    if (message.status.age != "")
                        currentStatus.age = message.status.age

                    if (message.status.employeeSalary != "")
                        currentStatus.employeeSalary = message.status.employeeSalary

                    if (message.status.accountSalaryAmount != "")
                        currentStatus.accountSalaryAmount = message.status.accountSalaryAmount

                    if (message.status.accountDepotAmount != "")
                        currentStatus.accountDepotAmount = message.status.accountDepotAmount

                    if (message.status.accountPensionAmount != "")
                        currentStatus.accountPensionAmount = message.status.accountPensionAmount

                    if (message.status.houseAmount != "") currentStatus.houseAmount = message.status.houseAmount

                    if (message.status.houseHireAmount != "")
                        currentStatus.houseHireAmount = message.status.houseHireAmount

                    if (message.status.houseLoanAmount != "")
                        currentStatus.houseLoanAmount = message.status.houseLoanAmount
                    if (message.status.loanMonthPayment != "")
                        currentStatus.loanMonthPayment = message.status.loanMonthPayment
                    if (message.status.interestMonthPayment != "")
                        currentStatus.interestMonthPayment = message.status.interestMonthPayment

                    if (message.status.profession != "") currentStatus.profession = message.status.profession
                    if (message.status.countCats != "") currentStatus.countCats = message.status.countCats
                    if (message.status.countDogs != "") currentStatus.countDogs = message.status.countDogs
                    if (message.status.countHorses != "") currentStatus.countHorses = message.status.countHorses
                    if (message.status.countCars != "") currentStatus.countCars = message.status.countCars
                    if (message.status.countBikes != "") currentStatus.countBikes = message.status.countBikes
                    if (message.status.countParties != "") currentStatus.countParties = message.status.countParties
                    if (message.status.countWalking != "") currentStatus.countWalking = message.status.countWalking
                    if (message.status.countFishing != "") currentStatus.countFishing = message.status.countFishing
                    if (message.status.countFriends != "") currentStatus.countFriends = message.status.countFriends
                    if (message.status.countBabies != "") currentStatus.countBabies = message.status.countBabies
                    if (message.status.countBoats != "") currentStatus.countBoats = message.status.countBoats
                    if (message.status.countStrong != "") currentStatus.countStrong = message.status.countStrong
                    if (message.status.countLoves != "") currentStatus.countLoves = message.status.countLoves

                    ShowMessage {
                        selectedMessage = message
                    }

                    lastDisplayedMessageId = message.id

                    if (isDebugOn) {
                        ShowDebugInfo {
                            selectedDebugMessage = "steg6"
                            selectedStyle = props.selectedStyle
                            selectedLife = life
                            selectedPerson = life.person
                            selectedEvent = props.selectedEvent
                            selectedProfession = profession
                            selectedTopPX = topPX
                            selectedlastDisplayedMessageId =  lastDisplayedMessageId
                            selectedMessageIndex = messageIndex
                        }
                        topPX += 50
                    }

                } else {
                    leftMessages = leftMessages.plus(message)
                }

                if (historyMessages[historyMessages.size - 1].id < message.id)
                    historyMessages = historyMessages.plus(message)
            } // end for
        } // end div

        if (isDebugOn) {
            topPX += 50
            ShowDebugInfo {
                selectedDebugMessage = "steg7"
                selectedStyle = props.selectedStyle
                selectedLife = life
                selectedPerson = life.person
                selectedEvent = props.selectedEvent
                selectedProfession = profession
                selectedTopPX = topPX
                selectedMessageList = messageList
                selectedHistoryMessages = historyMessages
            }
        }

        if (life.isQuestion && lastDisplayedMessageId >= life.questionMessageId) {
            life.isQuestion = false
            life.questionMessageId = 0
            div {

                p {
                    button {

                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = props.selectedStyle.topPXbutton01.px
                            left = props.selectedStyle.leftPXbutton01.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = props.selectedStyle.fontMedium.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("depressed"),
                                leftMessages,
                                props.selectedProfession,
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

        } else if (life.isNewProfession && lastDisplayedMessageId >= life.professionMessageId) {
            life.isNewProfession = false
            life.professionMessageId = 0
            div {

                p {
                    button {

                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = props.selectedStyle.topPXbutton01.px
                            left = props.selectedStyle.leftPXbutton01.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = props.selectedStyle.fontMedium.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            props.onSelectMessages(
                                props.selectedView.getNewView("question"),
                                leftMessages,
                                props.selectedProfession,
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
        } else {

            p {
                button {
                    if (leftMessages.size >= maxMessages) {
                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = props.selectedStyle.topPXbutton01.px
                            left = props.selectedStyle.leftPXbutton01.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = props.selectedStyle.fontMedium.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

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
                        +" ▶"
                    } else {
                        //Pension life
                        life.person.age = life.age
                        life.person.professions = life.person.professions.plus(profession)
                        life.person.employees = life.person.employees.plus(life.employee)
                        life.person.accounts = life.person.accounts.plus(life.accountSalary)
                        life.person.accounts = life.person.accounts.plus(life.accountNoAkassa)
                        life.person.accounts = life.person.accounts.plus(life.accountDepot)
                        life.person.accounts = life.person.accounts.plus(life.accountPension)
                        life.person.houses = life.person.houses.plus(life.person.house)

                        key = messageList[0].id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = props.selectedStyle.topPXbutton01.px
                            left = props.selectedStyle.leftPXbutton01.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = props.selectedStyle.fontMedium.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

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
                        +" ▶"
                    }
                }

                if (messageList.isNotEmpty()) {

                    if (isDebugOn) {
                        topPX += 150
                        ShowDebugInfo {
                            selectedDebugMessage = "steg8"
                            selectedStyle = props.selectedStyle
                            selectedLife = life
                            selectedPerson = life.person
                            selectedEvent = props.selectedEvent
                            selectedProfession = profession
                            selectedTopPX = topPX
                            selectedMessageList = messageList
                            selectedHistoryMessages = historyMessages
                            selectedMaxMessages = maxMessages
                        }
                    }

                    if (historyMessages.isNotEmpty() && messageList[0].id >= maxMessages) {
                        messageId = messageList[0].id - maxMessages

                        for (message in historyMessages) {
                            if (message.id >= messageId) {
                                backupMessages = backupMessages.plus(message)
                            }
                        }

                        button {

                            key = messageList[0].id.toString()
                            css {
                                display = Display.block
                                position = Position.absolute
                                top = props.selectedStyle.topPX01.px
                                left = props.selectedStyle.leftPX01.px

                                color = NamedColor.green
                                borderColor = NamedColor.white
                                fontSize = props.selectedStyle.fontMedium.px
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

                        if (isDebugOn) {
                            topPX += 50
                            ShowDebugInfo {
                                selectedDebugMessage = "steg9"
                                selectedStyle = props.selectedStyle
                                selectedLife = life
                                selectedPerson = life.person
                                selectedEvent = props.selectedEvent
                                selectedProfession = profession
                                selectedTopPX = topPX
                                selectedMessageList = backupMessages
                                selectedHistoryMessages = historyMessages
                                selectedMaxMessages = maxMessages
                            }
                        }
                    }
                }
            }
        }

        if (currentStatus.age == "") currentStatus.age = life.person.age.toString()
        if (currentStatus.profession == "") currentStatus.profession = props.selectedProfession.title
        ShowStatusRow {
            actualPerson = life.person
            actualAge = currentStatus.age
            actualName = life.person.name
            actualPension = (life.person.pension * 100.0F).toInt().formatDecimalSeparator()
            actualProfession = currentStatus.profession
            firstSalary = life.firstSalary.toInt().formatDecimalSeparator()
            actualSalary = currentStatus.employeeSalary
            actualSalaryAmount = currentStatus.accountSalaryAmount
            actualDepotAmount = currentStatus.accountDepotAmount
            actualPensionAmount = currentStatus.accountPensionAmount
            actualHireAmount = currentStatus.houseHireAmount
            actualHouseAmount = currentStatus.houseAmount
            actualLoanAmount = currentStatus.houseLoanAmount
            actualLoanMonthPayment = currentStatus.loanMonthPayment
            actualInterestMonthPayment = currentStatus.interestMonthPayment
            actualCats = currentStatus.countCats
            actualDogs = currentStatus.countDogs
            actualHorses = currentStatus.countHorses
            actualCars = currentStatus.countCars
            actualBabies = currentStatus.countBabies
            actualBikes = currentStatus.countBikes
            actualParties = currentStatus.countParties
            actualWalking = currentStatus.countWalking
            actualFishing = currentStatus.countFishing
            actualFriends = currentStatus.countFriends
            actualBoats = currentStatus.countBoats
            actualLoves = currentStatus.countLoves
            actualStrong = currentStatus.countStrong
            actualStyle = props.selectedStyle
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
    val profession = getProfession(life.professionId)
    var age = life.age
    val parent = life.parent
    var employee = life.employee
    var employeeId = employee.employeeId
    val accountPension = life.accountPension
    val accountSalary = life.accountSalary
    val accountDepot = life.accountDepot
    val accountTax = life.accountTax
    val accountNoAkassa = life.accountNoAkassa
    val accountWorkPension = life.accountWorkPension
    val accountTaxPension = life.accountTaxPension
    val union = person.union
    var isPandemi = life.isPandemi
    var isBoom = life.isBoom
    var isRecession = life.isRecession
    val hobby = Hobby("")

    //Init story
    var messageList = life.messageList
    var messageId = life.lastMessageId

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
    var sumCosts: Float
    var maxPension = 599250.0F //Max pensionsgrundad inkomst

    while (age < profession.pensionAge && !currentLife.isQuestion && !currentLife.isNewProfession) {
        //Init count month
        parent.countFamilyMonth = 0
        parent.familySalary = 0.0F
        employee.countSickMonth = 0
        employee.sickSalary = 0.0F
        person.union.countUnemployeeMonth = 0
        person.isSick = false
        lifeChance = randomLifeValues[age - 1]
        union.unemployedSalaryAmount = 0.0
        union.noAkassaSalaryAmount = 0.0

        //Försörjningsstöd
        if (employee.currentSalary == 0.0F) employee.currentSalary = 4180.0F

        if (!event.isSelected) {
            //randomEventValues = List(1) { Random.nextInt(0, 23) }
            randomEventValues = List(1) { Random.nextInt(0, allEvents.size) }
            event = allEvents[randomEventValues[0]]
        } else {
            person.luckEvents = person.luckEvents.plus(event)
        }

        when (event.eventType) {

            "depot" -> {
                // Event(0, "du får bonus i form av värdepapper 🤑", "depot", "depot"),

                if (lifeChance < profession.randomBonus && person.countWorkMonth >= 12 && employee.currentSalary > 0.0) {

                    //Get value och financial instruments
                    randomValues = List(1) { Random.nextInt(10000, 50000) }

                    accountDepot.amount += randomValues[0].toFloat()

                    messageList = accountDepot.showDepotAmount(randomValues[0].toFloat(), messageList, messageId)
                    messageId = messageList[messageList.size - 1].id
                }
            }


            "sick" -> {

                if (lifeChance < profession.randomSick ||
                    (isPandemi && event.objectType == "pandemi")
                ) {

                    when (event.objectType) {
                        "pandemi" -> {
                            //Event(5, "du får en pandemisk sjukdom 😱", "pandemi", "sick"),

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
                            //Event(1, "du blir utbränd 😔", "burnedout", "sick"),

                            if (!person.isHappy) {
                                person.isSick = true
                                currentLife.isQuestion = true

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(12, 36) }
                                employee.countSickMonth = randomValues[0]
                            }
                        }

                        "heartattack" -> {
                            //Event(2, "du får en hjärtattack 😬", "heartattack", "sick"),

                            if (!person.isHappy) {
                                person.isSick = true

                                //How many months are you sick?
                                randomValues = List(1) { Random.nextInt(1, 12) }
                                employee.countSickMonth = randomValues[0]
                            }
                        }

                        "golf" -> {
                            //Event(3, "du får en golfboll i huvudet 😨", "golf", "sick"),

                            person.isSick = true

                            //How many months are you sick?
                            randomValues = List(1) { Random.nextInt(1, 12) }
                            employee.countSickMonth = randomValues[0]
                        }

                        "shot" -> {
                            //Event(4, "du blir skjuten 😱", "shot", "sick"),

                            person.isSick = true

                            //How many months are you sick?
                            randomValues = List(1) { Random.nextInt(1, 12) }
                            employee.countSickMonth = randomValues[0]
                        }

                        "depressed" -> {
                            //Event(6, "du blir deprimerad.", "depressed", "sick"),

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
                        messageList = event.showEvent(0.0F, messageList, messageId, "Åh nej! ", "")
                        messageId = messageList[messageList.size - 1].id

                        if (person.isMagellit)
                            employee.sickSalary = employee.currentSalary * 0.90F //90% av lönen
                        else
                            employee.sickSalary = person.insurance.getIncome(employee.currentSalary)

                        messageList = employee.showEmployeeSickSalary(messageList, messageId)
                        messageId = messageList[messageList.size - 1].id

                        //Get chance to be approved by swedish authority
                        randomValues = List(1) { Random.nextInt(0, 100) }
                        if (randomValues[0] < 15) {
                            //Din sjukskrivning blir avslagen
                            employee.sickSalary = 0.0F

                            messageList = employee.showEmployeeNoSickSalary(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }

                        messageList = employee.showEmployeeCountSickMonth(messageList, messageId)
                        messageId = messageList[messageList.size - 1].id

                        if (currentLife.isQuestion) {
                            messageList = person.showTherapist(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

                            currentLife.questionMessageId = messageId
                        }
                    }
                }
            }

            "luck" -> {

                if (lifeChance < profession.randomLuck || event.isSelected) {
                    event.isSelected = false
                    currentLife.isQuestion = false
                    person.isHappy = true
                    when (event.objectType) {
                        "cat" -> {
                            //Event(7, "Du skaffar katt och livet känns härligt 🐱😍", "cat", "luck"),
                            person.cats = person.cats.plus(hobby.getHobby("cat"))
                        }

                        "strong" -> {
                            //Event(8, "Du börjar träna och du känner dig stark 💪 och lycklig", "strong", "luck"),
                            person.countStrong += 1
                        }

                        "friend" -> {
                            //Event(9, "Du finner en vän att prata med och livet vänder 🤗", "friend", "luck"),
                            person.countFriends += 1
                        }

                        "walk" -> {
                            //Event(10, "Du drar iväg på en lång vandring själv 🚶 du känner dig fri 😍", "walk", "luck"),
                            person.countWalking += 1
                        }

                        "dog" -> {
                            //Event(11, "Du skaffar hund och du känner dig både lycklig och stark 🦖😍", "dog", "luck"),
                            person.dogs = person.dogs.plus(hobby.getHobby("dog"))
                        }

                        "fish" -> {
                            //Event(12, "Du drar ut och fiskar 🐬 och känner hur du fylls med lycka 😍", "fish", "luck"),
                            person.countFishing += 1
                        }

                        "party" -> {
                            //Event(13, "Du festar järnet och känner hur du fylls med glädje 🤸", "party", "luck"),
                            person.parties = person.parties.plus(hobby.getHobby("party"))
                        }

                        "horse" -> {
                            //Event(14, "Du skaffar häst och du drar iväg i en härlig galopp 🦄", "horse", "luck"),
                            person.horses = person.horses.plus(hobby.getHobby("horse"))
                        }

                        "boat" -> {
                            //Event(15, "Du köper en segelbåt, havet gör dig lycklig ⛵", "boat", "luck"),
                            person.boats = person.boats.plus(hobby.getHobby("boat"))
                        }

                        "car" -> {
                            //Event(16, "Du köper en bil och du känner dig fri 🚗", "car", "luck"),
                            person.cars = person.cars.plus(hobby.getHobby("car"))
                        }

                        "bike" -> {
                            //Event(17, "Du köper en motorcykel och det mullrar när du drar iväg 🛵", "bike", "luck"),
                            person.bikes = person.bikes.plus(hobby.getHobby("bike"))
                        }

                        "love" -> {
                            //Event(18, "Du finner din kärlekspartner 💕💕💕💕💕 love is in the air", "love", "luck"),
                            person.countLove += 1
                            person.isLove = true
                        }
                    }

                    messageList = person.showPersonLuck(messageList, messageId, event)
                    messageId = messageList[messageList.size - 1].id
                }


            }

            "unemployed" -> {

                if ((lifeChance < profession.randomUnemployed && !person.isMagellit) || isRecession || event.isSelected) {


                    when (event.objectType) {
                        "unemployed" -> {
                            //Event(19, "du blir varslad 😢", "unemployed", "unemployed"),

                            union.countUnemployeeMonth = 0
                            union.unemployedSalaryAmount = 0.0
                            union.noAkassaSalaryAmount = 0.0

                            //Chans till avgångsvederlag om du jobbat mer än 12 månader
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            if (randomValues[0] < profession.randomSeverancePay && person.countWorkMonth >= 12) {

                                //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                                currentAmount = employee.currentSalary * (person.countWorkMonth / 12).toFloat()
                                accountSalary.amount += currentAmount
                                accountNoAkassa.amount += currentAmount

                                messageList = employee.showSeverancePay(currentAmount, messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            }

                            //Hur många dagar är du arbetslös, 22 dagar per månad får man ersättning
                            randomValues = List(1) { Random.nextInt(22, 300) }
                            union.countUnemployeeMonth = randomValues[0] / 22

                            //Endast 300 dagar kan man få a-kassa

                            messageList = union.showCountUnemployeeMonth(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

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

                                union.unemployedSalaryAmount =
                                    union.getIncomeInsurance(employee.currentSalary.toDouble())
                                messageList =
                                    union.showIncomeInsurance(messageList, messageId, union.unemployedSalaryAmount)
                                messageId = messageList[messageList.size - 1].id

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                                messageList = union.showNoAkassa(messageList, messageId, union.noAkassaSalaryAmount)
                                messageId = messageList[messageList.size - 1].id

                            } else if (union.isAkassa && person.countWorkMonth >= 12) {

                                union.unemployedSalaryAmount = union.getAkassa(employee.currentSalary.toDouble())
                                messageList = union.showAkassa(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                                messageList = union.showNoAkassa(messageList, messageId, union.noAkassaSalaryAmount)
                                messageId = messageList[messageList.size - 1].id

                            } else if (person.countWorkMonth >= 12) {
                                union.unemployedSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                                messageList = union.showNoAkassa(messageList, messageId, union.unemployedSalaryAmount)
                                messageId = messageList[messageList.size - 1].id

                                union.noAkassaSalaryAmount = union.getNoAkassa(employee.currentSalary.toDouble())
                            } else {
                                union.unemployedSalaryAmount = 0.0
                                union.noAkassaSalaryAmount = 0.0
                            }

                            //Dags att byta jobb och spara tidigare jobb
                            event.isSelected = false
                            person.professions = person.professions.plus(profession)
                            person.employees = person.employees.plus(employee)

                            messageList = profession.showSearchProfession(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

                            currentLife.isNewProfession = true
                            currentLife.professionMessageId = messageId
                        }

                        "employed" -> {
                            //Event(20, "du byter jobb.", "employed", "unemployed"),

                            if (event.isSelected) {

                                event.isSelected = false
                                currentLife.isNewProfession = false
                                currentLife.professionMessageId = 0

                                employeeId += 1
                                employee = Employee(employeeId)
                                employee.title = profession.title
                                employee.firstSalary = profession.salary * person.age
                                employee.currentSalary = profession.salary * person.age
                                employee.countWorkMonth = 0

                                messageList = profession.showNewProfession(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id

                                messageList = employee.showEmployeeSalary(0.0F, messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            }
                        }
                    }
                }
            }

            "magellit" -> {

                // Event(21, "du blir träffad av en Magellit.", "magellit", "magellit"),
                if (lifeChance < 60 && profession.professionType == "programmer") {
                    person.isMagellit = true

                    messageList = person.showPersonMagellit(messageList, messageId)
                    messageId = messageList[messageList.size - 1].id
                }
            }

            "parent" -> {
                // Event(22, "du får barn 👶", "parent", "parent"),

                if (lifeChance < 50 && age <= 50) {
                    //Babies
                    parent.countBabies += 1
                    parent.familySalary = parent.getIncome(employee.currentSalary)
                    if (person.isMagellit) parent.familySalary += 5000.0F

                    parent.countFamilyMonth += parent.familyMonth

                    messageList = parent.showParent(messageList, messageId)
                    messageId = messageList[messageList.size - 1].id
                }
            }

            "vab" -> {
                // Event(23, "du VAB:ar.", "vab", "vab")
                if (parent.countBabies > 0) {
                    //VAB

                    randomValues = List(1) { Random.nextInt(1, 12) }
                    parent.countFamilyMonth += randomValues[0]
                    parent.familySalary = parent.getIncome(employee.currentSalary)

                    if (person.isMagellit)
                        parent.familySalary += 5000.0F

                    messageList = parent.showVAB(messageList, messageId)
                    messageId = messageList[messageList.size - 1].id
                }
            }
        }

        //Get cost event in life
        randomCostEventValues = List(1) { Random.nextInt(0, allCostEvents.size) }
        costevent = allCostEvents[randomCostEventValues[0]]

        when (costevent.eventType) {

            "home" -> {
                if (lifeChance < 25 || !person.isAccommodation) {
                    messageList = costevent.showEvent(0.0F, messageList, messageId, "", "")
                    messageId = messageList[messageList.size - 1].id

                    if (person.isAccommodation && person.house.houseAmount > 0.0F) {
                        // Säljer det boendet du har
                        person.houses = person.houses.plus(person.house)

                        messageList = person.showPersonAccomodationSold(messageList, messageId)
                        messageId = messageList[messageList.size - 1].id

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

                        messageList = accountSalary.showAccountSoldHouse(messageList, messageId)
                        messageId = messageList[messageList.size - 1].id

                        if (accountSalary.amount != accountNoAkassa.amount) {
                            messageList = accountNoAkassa.showAccountSoldHouse(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }

                        person.house.houseAmount = 0.0F
                        person.house.houseMonthPayment = 0.0F

                    } else if (person.isAccommodation && (
                                person.house.houseType == "hire" ||
                                        person.house.houseType == "hirecity" ||
                                        person.house.houseType == "hirehouse" ||
                                        person.house.houseType == "hiredepartment")
                    ) {
                        // Du lämnar hyresrätteb
                        messageList = person.showPersonAccomodationShift(messageList, messageId)
                        messageId = messageList[messageList.size - 1].id

                        person.houses = person.houses.plus(person.house)

                        person.house.houseMonthPayment = 0.0F
                    }

                    person.isAccommodation = true
                    when (costevent.objectType) {

                        "rosehouse" -> {
                            //Event(0, "Kul! du köper ett hus på landet med doftande rosor.","rosehouse","home"),

                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(0, "rosehouse")
                                person.house.description = "ett hus på landet med doftande rosor"

                                randomValues = List(1) { Random.nextInt(1000000, 5000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(2000, 5000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonHouseBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "castel" -> {
                            //Event(1, "Kul! du köper ett slott med tinar och torn.","castel","home"),
                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(0, "castel")
                                person.house.description = "ett slott med tinar och torn"

                                randomValues = List(1) { Random.nextInt(5000000, 30000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(2000, 5000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonHouseBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "house" -> {
                            //Event(2, "Kul! du köper ett minimalistiskt hus med raka linjer.","house","home"),

                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(0, "house")
                                person.house.description = "ett minimalistiskt hus med raka linjer"

                                randomValues = List(1) { Random.nextInt(2000000, 10000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(2000, 5000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonHouseBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "koja" -> {
                            //Event(3, "Kul! du köper en koja i skogen.","koja","home"),
                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(0, "koja")
                                person.house.description = "en koja i skogen"

                                randomValues = List(1) { Random.nextInt(500000, 1000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(1000, 3000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonHouseBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "department" -> {
                            //Event(5, "Kul! du köper ett bostadsrättsradhus i en förort.","department","home"),
                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(0, "department")
                                person.house.description = "ett bostadsrättsradhus i en förort"

                                randomValues = List(1) { Random.nextInt(1000000, 5000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(1000, 7000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonDepartmentBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "departmentcity" -> {
                            //Event(4, "Kul! du köper en bostadsrätt mitt i staden.","departmentcity","home"),
                            if (accountSalary.amount > 0.0F || accountDepot.amount > 0.0F) {
                                person.house = House(person.id, "departmentcity")
                                person.house.description = "en bostadsrätt mitt i staden"

                                randomValues = List(1) { Random.nextInt(5000000, 15000000) }
                                person.house.houseAmount = randomValues[0].toFloat()

                                randomValues = List(1) { Random.nextInt(1000, 7000) }
                                person.house.houseMonthPayment = randomValues[0].toFloat()

                                messageList = person.showPersonDepartmentBought(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                messageList = person.noMoneyToShop(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                                person.isAccommodation = false
                            }
                        }

                        "hirecity" -> {
                            //Event(6, "Kul! du skaffar dig en hyresrätt mitt i staden.","hirecity","home"),

                            person.house = House(0, "hirecity")
                            person.house.houseAmount = 0.0F
                            person.house.description = "en hyresrätt mitt i staden"

                            currentAmount = employee.currentSalary / 2
                            randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                            person.house.houseMonthPayment = randomValues[0].toFloat()

                            messageList = person.showPersonAccomodationHire(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }

                        "hire" -> {
                            //Event(7, "Kul! du skaffar dig en hyresrätt i en förort.","hire","home"),

                            person.house = House(0, "hire")
                            person.house.houseAmount = 0.0F
                            person.house.description = "en hyresrätt i en förort"

                            currentAmount = employee.currentSalary / 2
                            randomValues = List(1) { Random.nextInt(4000, currentAmount.toInt()) }
                            person.house.houseMonthPayment = randomValues[0].toFloat()

                            messageList = person.showPersonAccomodationHire(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }

                        "hirehouse" -> {
                            //Event(8, "Kul! du hyr i andra hand ett hus på landet.","hirehouse","home"),

                            person.house = House(0, "hirehouse")
                            person.house.houseAmount = 0.0F
                            person.house.description = "hyr i andra hand ett hus på landet"

                            currentAmount = employee.currentSalary / 2
                            randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                            person.house.houseMonthPayment = randomValues[0].toFloat()

                            messageList = person.showPersonAccomodationHire(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }

                        "hiredepartment" -> {
                            //Event(9, "Kul! du hyr i andra hand ett lägenhet mitt i staden.","hiredepartment","home"),

                            person.house = House(0, "hiredepartment")
                            person.house.houseAmount = 0.0F
                            person.house.description = " hyr i andra hand en lägenhet mitt i staden"

                            currentAmount = employee.currentSalary / 2
                            randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                            person.house.houseMonthPayment = randomValues[0].toFloat()

                            messageList = person.showPersonAccomodationHire(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id
                        }
                    }

                    person.house.isMortgage = false
                    if (person.house.houseAmount > 0.0F) {

                        // Betala eller ta lån?
                        if (accountSalary.amount >= person.house.houseAmount) {
                            accountSalary.amount -= person.house.houseAmount
                            accountNoAkassa.amount -= person.house.houseAmount

                            messageList = accountSalary.showAccountBoughtHouse(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

                            if (accountSalary.amount != accountNoAkassa.amount) {
                                messageList = accountNoAkassa.showAccountBoughtHouse(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            }

                        } else if (accountDepot.amount >= person.house.houseAmount) {
                            accountDepot.amount -= person.house.houseAmount

                            messageList = accountDepot.showAccountBoughtHouse(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

                        } else if ((accountDepot.amount + accountSalary.amount) >= person.house.houseAmount) {
                            accountDepot.amount -= (person.house.houseAmount - accountSalary.amount)
                            accountNoAkassa.amount -= accountSalary.amount
                            accountSalary.amount = 0.0F

                            messageList = accountSalary.showAccountBoughtHouse(messageList, messageId)
                            messageId = messageList[messageList.size - 1].id

                            if (accountSalary.amount != accountNoAkassa.amount) {
                                messageList = accountNoAkassa.showAccountBoughtHouse(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            }

                        } else {
                            // Ta lån?
                            person.house.isMortgage = true
                            person.house.houseLoan.loanAmount =
                                person.house.houseAmount - (accountDepot.amount + accountSalary.amount)

                            randomValues = List(1) { Random.nextInt(1, 5) } //Ränta
                            person.house.houseLoan.loanInterest = randomValues[0].toFloat()
                            person.house.houseLoan.ageWhenPayed = profession.pensionAge - age
                            person.house.houseLoan.loanMonthPayment = person.house.houseLoan.loanAmount /
                                    (person.house.houseLoan.ageWhenPayed.toFloat() * 12.0F)

                            if ((employee.currentSalary - 5000.0F) < person.house.houseLoan.loanMonthPayment) {
                                //No loan
                                person.house.isMortgage = false
                                person.house.houseLoan.loanAmount = 0.0F
                                person.house.houseLoan.loanMonthPayment = 0.0F
                                person.house.houseLoan.loanInterest = 0.0F

                                person.isAccommodation = false
                                person.house.houseAmount = 0.0F
                                person.house.houseMonthPayment = 0.0F

                                messageList = person.showPersonNoHouseLoan(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id
                            } else {
                                // loan
                                accountNoAkassa.amount -= accountSalary.amount
                                accountSalary.amount = 0.0F
                                accountDepot.amount = 0.0F

                                messageList = person.showPersonGetHouseLoan(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id

                                messageList = accountSalary.showAccountBoughtHouse(messageList, messageId)
                                messageId = messageList[messageList.size - 1].id

                                if (accountSalary.amount != accountNoAkassa.amount) {
                                    messageList = accountNoAkassa.showAccountBoughtHouse(messageList, messageId)
                                    messageId = messageList[messageList.size - 1].id
                                }
                            }
                        }
                    }
                }
            }

            "accident" -> {

                if (lifeChance < 15) {
                    isBoom = false
                    person.isMagellit = false
                    when (costevent.objectType) {

                        "depot" -> {
                            // Event(10, "Lågkonjuktur! ditt sparande rasar i värde ","depot","accident"),
                            isRecession = true
                            if (accountDepot.amount > 0.0F) {

                                randomValues = List(1) { Random.nextInt(1, 50) }
                                accountDepot.amount -= accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                messageList = costevent.showEvent(
                                    accountDepot.amount,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id
                            }
                        }


                        "home" -> {
                            //Event(11, "Lågkonjuktur! din hyra höjs på ditt boende ","home","accident"),

                            isRecession = true
                            if (person.isAccommodation) {

                                randomValues = List(1) { Random.nextInt(1, 5) }
                                person.house.houseMonthPayment += person.house.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)

                                messageList = costevent.showEvent(
                                    person.house.houseMonthPayment,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id

                            }
                        }

                        "loan" -> {
                            //Event(12, "Lågkonjuktur! din räntan höjs på ditt lån ","loan","accident"),

                            isRecession = true
                            if (person.house.isMortgage) {
                                randomValues = List(1) { Random.nextInt(1, 5) }
                                person.house.houseLoan.loanInterest += randomValues[0].toFloat()

                                messageList = costevent.showEvent(
                                    person.house.houseLoan.loanInterest,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id
                            }
                        }


                        "sick" -> {
                            //Event(13, "Pandemi! risk att du blir sjuk","sick","accident"),

                            person.isHappy = false
                            isPandemi = true

                            messageList = costevent.showEvent(0.0F, messageList, messageId, "", ".")
                            messageId = messageList[messageList.size - 1].id
                        }

                        "unluck" -> {
                            //Event(14, "Din sambo lämnar dig, nu får du betala hyran själv", "unluck", "accident"),

                            if (person.isLove) {
                                person.isLove = false

                                messageList = costevent.showEvent(0.0F, messageList, messageId, "", ".")
                                messageId = messageList[messageList.size - 1].id
                            }
                        }
                    }
                }
            }

            "happening" -> {

                if (lifeChance > 85) {
                    isPandemi = false
                    isBoom = true
                    isRecession = false

                    when (costevent.objectType) {
                        "depot" -> {
                            //Event(15, "Högkonjuktur! ditt sparande ökar i värde ","depot","happening"),

                            if (accountDepot.amount > 0.0F) {

                                randomValues = List(1) { Random.nextInt(1, 50) }
                                accountDepot.amount += accountDepot.amount * randomValues[0].toFloat() / 100.0F

                                messageList = costevent.showEvent(
                                    accountDepot.amount,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id
                            }
                        }

                        "home" -> {
                            //Event(16, "Högkonjuktur! värdet på din bostad höjs ","home","happening"),

                            if (person.isAccommodation) {

                                randomValues = List(1) { Random.nextInt(1, 20) }
                                person.house.houseAmount += person.house.houseAmount * (randomValues[0].toFloat() / 100.0F)

                                messageList = costevent.showEvent(
                                    person.house.houseAmount,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id
                            }
                        }

                        "loan" -> {
                            //Event(17, "Högkonjuktur! räntan sänks på ditt lån ","loan","happening")

                            if (person.house.isMortgage) {
                                randomValues = List(1) { Random.nextInt(1, 2) }
                                person.house.houseLoan.loanInterest -= randomValues[0].toFloat() / 100.0F

                                messageList = costevent.showEvent(
                                    person.house.houseLoan.loanInterest,
                                    messageList,
                                    messageId,
                                    "",
                                    " med ${randomValues[0].formatDecimalSeparator()}%."
                                )
                                messageId = messageList[messageList.size - 1].id
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
        //Initiera kostnader
        sumCosts = 0.0F

        messageList = person.showWorkingLife(age, messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        if (!currentLife.isNewProfession) {
            messageList = profession.showCurrentProfession(messageList, messageId)
            messageId = messageList[messageList.size - 1].id
        }

        //Löneökning
        if (employee.currentSalary != 4180.0F &&
            ((employee.countSickMonth + union.countUnemployeeMonth + parent.countFamilyMonth) == 0)
        ) {
            currentAmount = employee.raiseTheSalary(isBoom, age)

            if (profession.maxSalary == 0.0F || (employee.currentSalary + currentAmount) <= profession.maxSalary) {
                messageList =
                    employee.showEmployeeSalary((currentAmount / employee.currentSalary), messageList, messageId)
                messageId = messageList[messageList.size - 1].id

                employee.currentSalary += currentAmount
            } else {
                messageList =
                    employee.showEmployeeSalary(0.0F, messageList, messageId)
                messageId = messageList[messageList.size - 1].id
            }
        } else {
            messageList =
                employee.showEmployeeSalary(0.0F, messageList, messageId)
            messageId = messageList[messageList.size - 1].id
        }

        //12 arbetsmånader har gått men har du jobbat
        employee.countWorkMonth += 12

        //Sick -------------------------------------------------------------------------------
        employee.countWorkMonth -= employee.countSickMonth
        person.countSickMonth += employee.countSickMonth

        //Sjuk inkomst
        currentAmount = employee.sickSalary * employee.countSickMonth.toFloat()

        //Summera skatt
        accountTax.amount += currentAmount * 0.3F

        //Summera statlig inkomstpension på pensionsgrundad inkomst
        if (currentAmount <= maxPension) {
            accountPension.amount += currentAmount * 0.18F
            accountTaxPension.amount += currentAmount * 0.18F
        } else {
            accountPension.amount += maxPension * 0.18F
            accountTaxPension.amount += maxPension * 0.18F
        }

        //Dra av skatt på inkomsten
        accountSalary.amount += currentAmount - (currentAmount * 0.3F)
        accountNoAkassa.amount += currentAmount - (currentAmount * 0.3F)
        sumCosts += currentAmount * 0.3F

        //Varslad ---------------------------------------------------------------------------------
        employee.countWorkMonth -= union.countUnemployeeMonth
        person.countUnemployedMonth += union.countUnemployeeMonth

        //arbetslöshetsersättning
        currentAmount = union.unemployedSalaryAmount.toFloat()

        //Summera skatt
        accountTax.amount += currentAmount * 0.3F

        //Summera statlig inkomstpension på pensionsgrundad inkomst
        if (currentAmount <= maxPension) {
            accountPension.amount += currentAmount * 0.18F
            accountTaxPension.amount += currentAmount * 0.18F
        } else {
            accountPension.amount += maxPension * 0.18F
            accountTaxPension.amount += maxPension * 0.18F
        }

        //Dra av skatt på inkomsten
        accountSalary.amount += currentAmount - (currentAmount * 0.3F)
        sumCosts += currentAmount * 0.3F

        currentAmount = union.noAkassaSalaryAmount.toFloat()
        accountNoAkassa.amount += currentAmount - (currentAmount * 0.3F)

        //Parent -------------------------------------------------------------------------------------
        employee.countWorkMonth -= parent.countFamilyMonth
        person.countParentMonth += parent.countFamilyMonth

        //föräldrarpenning
        currentAmount = parent.familySalary * parent.countFamilyMonth.toFloat()

        //Summera skatt
        accountTax.amount += currentAmount * 0.3F

        //Summera statlig inkomstpension på pensionsgrundad inkomst
        if (currentAmount <= maxPension) {
            accountPension.amount += currentAmount * 0.18F
            accountTaxPension.amount += currentAmount * 0.18F
        } else {
            accountPension.amount += maxPension * 0.18F
            accountTaxPension.amount += maxPension * 0.18F
        }

        accountSalary.amount += currentAmount - (currentAmount * 0.3F)
        accountNoAkassa.amount += currentAmount - (currentAmount * 0.3F)
        sumCosts += currentAmount * 0.3F

        //privat pension ----------------------------------------------------------------------------
        currentAmount = employee.currentSalary * person.pension * 12.0F
        accountSalary.amount -= currentAmount
        accountNoAkassa.amount -= currentAmount
        accountPension.amount += currentAmount

        sumCosts += employee.currentSalary * person.pension * 12.0F

        //Beräkna inkomst, skatt och pension --------------------------------------------------------
        if (employee.countWorkMonth > 0) {

            //Dra bort tjänstepension innan skatt
            currentAmount =
                (employee.currentSalary - (employee.currentSalary * profession.pension)) * employee.countWorkMonth.toFloat()

            //Summera skatt
            accountTax.amount += currentAmount * 0.3F

            //Summera inkomstpension är 18% av skatten vi betalar
            if (currentAmount <= maxPension) {
                accountPension.amount += currentAmount * 0.18F
                accountTaxPension.amount += currentAmount * 0.18F
            } else {
                accountPension.amount += maxPension * 0.18F
                accountTaxPension.amount += maxPension * 0.18F
            }

            //Dra av skatt
            accountSalary.amount += currentAmount - (currentAmount * 0.3F)
            accountNoAkassa.amount += currentAmount - (currentAmount * 0.3F)
            sumCosts += currentAmount * 0.3F

            //Summera tjänstepension
            accountPension.amount += (employee.currentSalary * profession.pension) * employee.countWorkMonth.toFloat()
            accountWorkPension.amount += (employee.currentSalary * profession.pension) * employee.countWorkMonth.toFloat()
            sumCosts += (employee.currentSalary * profession.pension) * employee.countWorkMonth.toFloat()

            messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
            messageId = messageList[messageList.size - 1].id

            employee.countWorkMonth = 0

        } else {
            messageList = employee.showEmployeeCountWorkMonth(messageList, messageId)
            messageId = messageList[messageList.size - 1].id
        }

        if (accountDepot.amount > 0.0F) {
            //Skuldsanering om lönekonto tom men pengar i depå
            if (accountSalary.amount < 0.0F) {
                messageList = accountDepot.showSkuldsanering(messageList, messageId)
                messageId = messageList[messageList.size - 1].id

                accountSalary.amount += accountDepot.amount
                accountNoAkassa.amount += accountDepot.amount
                accountDepot.amount = 0.0F
            }
        }

        //Visa konton
        messageList = accountSalary.showAccountAmount((age - person.age + 1), messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        messageList = accountDepot.showAccountAmount((age - person.age + 1), messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        messageList = accountPension.showAccountAmount((age - person.age + 1), messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        messageList = accountTax.showAccountAmount((age - person.age + 1), messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        //Dra av diverse levnadskostnader för mat är 3000 genomsnittet
        randomValues = List(1) { Random.nextInt(5000, 10000) }
        accountSalary.amount -= randomValues[0].toFloat() * 12.0F
        accountNoAkassa.amount -= randomValues[0].toFloat() * 12.0F
        sumCosts += randomValues[0].toFloat() * 12.0F

        //Barn kostar pengar
        accountSalary.amount -= parent.costBabies()
        accountNoAkassa.amount -= parent.costBabies()
        sumCosts += parent.costBabies()

        //Hobbies kostar pengar
        accountSalary.amount -= person.costHobbies()
        accountNoAkassa.amount -= person.costHobbies()
        sumCosts += person.costHobbies()

        //Dra av kostnad för boendet
        if (person.isAccommodation) {
            if (person.isLove) {
                //Sambo att dela hyra med
                accountSalary.amount -= (person.house.houseMonthPayment * 12.0F) / 2.0F
                accountNoAkassa.amount -= (person.house.houseMonthPayment * 12.0F) / 2.0F
                sumCosts += (person.house.houseMonthPayment * 12.0F) / 2.0F
            } else {
                accountSalary.amount -= person.house.houseMonthPayment * 12.0F
                accountNoAkassa.amount -= person.house.houseMonthPayment * 12.0F
                sumCosts += person.house.houseMonthPayment * 12.0F
            }

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

                        sumCosts += person.house.houseLoan.calculateInterest()
                        sumCosts += person.house.houseLoan.loanMonthPayment

                        person.house.houseLoan.loanAmount -= person.house.houseLoan.loanMonthPayment
                    } else {
                        //Avbetalning
                        accountSalary.amount -= person.house.houseLoan.loanAmount
                        accountNoAkassa.amount -= person.house.houseLoan.loanAmount
                        sumCosts += person.house.houseLoan.loanAmount

                        person.house.houseLoan.loanAmount = 0.0F
                    }
                }

                if (person.house.houseLoan.loanAmount == 0.0F) {
                    person.house.isMortgage = false
                    messageList = person.showPersonLoanReady(messageList, messageId)
                    messageId = messageList[messageList.size - 1].id
                }
            }

            //Årlig höjning av hyran
            person.house.houseMonthPayment += person.house.raiseTheRent()
        }

        //Valutan blir mindre värd på kontot
        if (isRecession) {
            randomValues = List(1) { Random.nextInt(1, 2) }

            accountSalary.amount -= (accountSalary.amount * (randomValues[0].toFloat() / 100.0F))
            accountNoAkassa.amount -= (accountNoAkassa.amount * (randomValues[0].toFloat() / 100.0F))

        }

        messageList = accountSalary.showAccountCost(messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        if (accountSalary.amount != accountNoAkassa.amount) {
            messageList = accountNoAkassa.showAccountCost(messageList, messageId)
            messageId = messageList[messageList.size - 1].id
        }

        sumCosts = sumCosts / 12.0F //per månad
        messageList = accountSalary.showSumAccountCost(messageList, messageId, sumCosts)
        messageId = messageList[messageList.size - 1].id

        if (person.isAccommodation) {
            // Visa boende
            messageList = person.showPersonAccomodation(messageList, messageId)
            messageId = messageList[messageList.size - 1].id

            if (accountSalary.amount <= -500000.0F && person.house.houseAmount > 0.0F) {
                // Skuldsanering om boende ger pengar
                messageList = person.showSkuldsanering(messageList, messageId)
                messageId = messageList[messageList.size - 1].id

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
                messageList = accountSalary.showAccountAmount((age - person.age + 1), messageList, messageId)
                messageId = messageList[messageList.size - 1].id

                person.house.houseAmount = 0.0F
                person.house.houseMonthPayment = 0.0F

                //Du skaffar dig en hyresrätt istället
                person.isAccommodation = true
                person.house = House(0, "hire")

                randomValues = List(1) { Random.nextInt(5000, 13000) }
                person.house.houseMonthPayment = randomValues[0].toFloat()

                messageList = person.showPersonAccomodationHire(messageList, messageId)
                messageId = messageList[messageList.size - 1].id
            }
        }

        //Djur, fordon och fester dör ut, så också lyckan

        messageList = person.ShowDeadHobbies(messageList, messageId)
        messageId = messageList[messageList.size - 1].id

        person.isHappy = person.isHappyPerson()
    }

    //Store story
    currentLife.age = age
    currentLife.person = person
    currentLife.professionId = profession.id
    currentLife.parent = parent
    currentLife.employee = employee
    currentLife.accountPension = accountPension
    currentLife.accountWorkPension = accountWorkPension
    currentLife.accountTaxPension = accountTaxPension
    currentLife.accountSalary = accountSalary
    currentLife.accountDepot = accountDepot
    currentLife.accountTax = accountTax
    currentLife.accountNoAkassa = accountNoAkassa
    currentLife.person.union = union
    currentLife.isPandemi = isPandemi
    currentLife.isBoom = isBoom
    currentLife.isRecession = isRecession
    currentLife.messageList = messageList
    currentLife.lastMessageId = messageId

    return currentLife
}














