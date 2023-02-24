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

external interface StartPensionLifeProps : Props {
    var selectedView: View
    var selectedPerson: Person
    var selectedMessages: List<Message>
    var selectedHistory: List<Message>
    var selectedStatus: Status
    var selectedLife: Life

    var onSelectPension: (View, List<Message>, Person, List<Message>, Status, Life) -> Unit
}

val StartPensionLife = FC<StartPensionLifeProps> { props ->

    // Init person

    val currentStatus: Status = props.selectedStatus
    val person = props.selectedPerson
    val life = props.selectedLife

    //Init story
    var messageList = props.selectedHistory
    var leftMessages: List<Message> = emptyList()
    var backupMessages: List<Message> = emptyList()
    val maxMessages = 8
    var isDebugOn = false
    var lastDisplayedMessageId = 0
    var topPX = 0
    var messageId = 0

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

                    ShowMessage {
                        selectedMessage = message
                    }

                    lastDisplayedMessageId = message.id

                    if (false) {
                        div {
                            css {
                                display = Display.block
                                position = Position.absolute
                                top = topPX.px
                                left = 50.px
                                color = NamedColor.black
                                fontSize = 18.px
                                backgroundColor = NamedColor.white
                                fontFamily = FontFamily.cursive
                            }

                            ShowMessage {
                                selectedMessage = Message(
                                    6,
                                    "LastmessageId: ${lastDisplayedMessageId} index: ${messageIndex}  ",
                                    "",
                                    ""
                                )
                            }
                            topPX += 50
                        }
                    }

                } else {
                    leftMessages = leftMessages.plus(message)
                }
            }
        }

        if (isDebugOn) {
            topPX += 50
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        6,
                        "QuestionId: ${life.questionMessageId} employedid: ${life.professionMessageId} ",
                        "",
                        ""
                    )
                }

                ShowMessage {
                    selectedMessage = Message(
                        7,
                        "isQuestion: ${life.isQuestion}  isNewProfession: ${life.isNewProfession} ",
                        "",
                        ""
                    )
                }

                ShowMessage {
                    selectedMessage = Message(
                        7,
                        "messageList: ${messageList.size}  ",
                        "",
                        ""
                    )
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

                onClick = {
                    props.onSelectPension(
                        props.selectedView.getNewView("reload"),
                        leftMessages,
                        life.person,
                        props.selectedHistory,
                        currentStatus,
                        life
                    )
                }
                +props.selectedView.getNewView("reload").buttonText

                +" ▶"
            }

            if (messageList.isNotEmpty()) {

                if (isDebugOn) {
                    topPX += 150
                    div {
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = topPX.px
                            left = 50.px
                            color = NamedColor.black
                            fontSize = 18.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        ShowMessage {
                            selectedMessage = Message(
                                6,
                                "first message: ${messageList[0].id} >=  ${maxMessages} ",
                                "",
                                ""
                            )
                        }
                    }
                }

                if (props.selectedHistory.isNotEmpty() && messageList[0].id >= maxMessages) {
                    messageId = messageList[0].id - maxMessages

                    for ((messageIndex, message) in props.selectedHistory.withIndex()) {
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
                                life.person,
                                props.selectedHistory,
                                currentStatus,
                                life
                            )
                        }
                        +"◀ "
                    }

                    if (isDebugOn) {
                        topPX += 50
                        div {
                            css {
                                display = Display.block
                                position = Position.absolute
                                top = topPX.px
                                left = 50.px
                                color = NamedColor.black
                                fontSize = 18.px
                                backgroundColor = NamedColor.white
                                fontFamily = FontFamily.cursive
                            }

                            ShowMessage {
                                selectedMessage = Message(
                                    7,
                                    "backup: ${backupMessages.size} ",
                                    "",
                                    ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (currentStatus.age == "") currentStatus.age = life.person.age.toString()
    ShowStatusRow {
        actualPerson = life.person
        actualAge = currentStatus.age
        actualName = life.person.name
        actualPension = (life.person.pension * 100).toString()
        actualProfession = currentStatus.profession
        firstSalary = life.firstSalary.toInt().formatDecimalSeparator()
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
        actualWalking = currentStatus.countWalking
        actualFishing = currentStatus.countFishing
        actualFriends = currentStatus.countFriends
        actualBoats = currentStatus.countBoats
        actualStrong = currentStatus.countStrong
    }
}












