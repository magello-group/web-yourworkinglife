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
    var selectedMessages: List<Message>
    var selectedLifeHistory: List<Message>
    var selectedHistory: List<Message>
    var selectedStatus: Status
    var selectedLife: Life
    var selectedEvent: Event

    var onSelectPension: (View, List<Message>, List<Message>, Status, Life, List<Message>) -> Unit
}

val StartPensionLife = FC<StartPensionLifeProps> { props ->

    // Init person life
    var life: Life = props.selectedLife
    val currentLife: Life

    //Init story
    var messageList: List<Message> = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    var historyMessages: List<Message> = props.selectedHistory
    var backupMessages: List<Message> = emptyList()
    val currentStatus: Status = props.selectedStatus
    var messageId = props.selectedLife.lastMessageId
    val maxMessages = 8


    if (!life.pensionStep || historyMessages.isEmpty()) {
        life.pensionStep = true

        // starting your pension life
        life.messageList = emptyList()
        life.lastMessageId = 0

        currentLife = pensionLife(life, props.selectedEvent)

        //Get what happened in your life
        life = currentLife
        messageList = currentLife.messageList
        messageId = currentLife.lastMessageId

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

                    ShowMessage {
                        selectedMessage = message
                    }

                } else {
                    leftMessages = leftMessages.plus(message)
                }
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
                        props.onSelectPension(
                            props.selectedView,
                            leftMessages,
                            historyMessages,
                            currentStatus,
                            life,
                            props.selectedLifeHistory
                        )
                    }
                    +props.selectedView.buttonText
                    +" ▶"
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
                            top = 10.px
                            left = 10.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = 18.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            props.onSelectPension(
                                props.selectedView,
                                backupMessages,
                                historyMessages,
                                currentStatus,
                                life,
                                props.selectedLifeHistory
                            )
                        }
                        +"◀ "
                    }
                }
            }
        }

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














