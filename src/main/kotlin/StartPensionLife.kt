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

external interface StartPensionLifeProps : Props {
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

val StartPensionLife = FC<StartPensionLifeProps> { props ->

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
    val maxMessages = 8
    val isDebugOn = false
    var topPX = 930


    if (!life.pensionStep || props.selectedHistory.isNotEmpty()) {
        life.pensionStep = true

        // starting your pension life
        life.messageList = emptyList()
        life.lastMessageId = 0

        currentLife = pensionLife(life, props.selectedEvent)

        //Get what happened in your life
        life = currentLife
        messageList = currentLife.messageList
        messageId = currentLife.lastMessageId

    }

    if (historyMessages.isEmpty()) {
        //Store story
        for (message in messageList) {
            historyMessages = historyMessages.plus(message)
        }
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
                if (messageIndex < maxMessages) {
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

                } else {
                    leftMessages = leftMessages.plus(message)
                }

                if (historyMessages[historyMessages.size - 1].id < message.id)
                    historyMessages = historyMessages.plus(message)
            }
        }

        if (messageList.isNotEmpty()) {
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
                }

                if (historyMessages.isNotEmpty()) {

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
                                props.selectedHistory,
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
        }
    }
}

fun pensionLife(life: Life, selectedEvent: Event): Life {
    val currentLife = life

    //Init story
    var messageList = life.messageList
    var messageId = life.lastMessageId

    // Du är pensionär
    messageList = life.showPensionLife(messageList, messageId)
    messageId = messageList[messageList.size - 1].id

    messageList = life.accountSalary.showAccountAmount(life.age, messageList, messageId)
    messageId = messageList[messageList.size - 1].id

    messageList = life.accountDepot.showAccountAmount(life.age, messageList, messageId)
    messageId = messageList[messageList.size - 1].id

    messageList = life.accountPension.showAccountAmount(life.age, messageList, messageId)
    messageId = messageList[messageList.size - 1].id

    messageList = life.accountTax.showAccountAmount(life.age, messageList, messageId)
    messageId = messageList[messageList.size - 1].id
/*
    //Dra av diverse levnadskostnader för mat är 3000 genomsnittet
    randomValues = List(1) { Random.nextInt(5000, 10000) }
    sumCosts += randomValues[0].toFloat() * 12.0F

    //Hobbies kostar pengar
    sumCosts += person.costHobbies()

    //Dra av kostnad för boendet
    if (person.isAccommodation) {
        if (person.isLove) {
            //Sambo att dela hyra med
            sumCosts += (person.house.houseMonthPayment * 12.0F) / 2.0F
        } else {
            sumCosts += person.house.houseMonthPayment * 12.0F
        }

        //Årlig höjning av hyran
        //person.house.houseMonthPayment += person.house.raiseTheRent()
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
    currentLife.lastMessageId = messageId
*/
    currentLife.messageList = messageList
    currentLife.lastMessageId = messageId

    return currentLife
}














