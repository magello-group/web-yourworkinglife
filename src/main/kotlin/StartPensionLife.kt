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
    val maxMessages = 6

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
                    if (message.status.countCats != "") currentStatus.countCats = message.status.countCats
                    if (message.status.countDogs != "") currentStatus.countDogs = message.status.countDogs
                    if (message.status.countHorses != "") currentStatus.countHorses = message.status.countHorses
                    if (message.status.countCars != "") currentStatus.countCars = message.status.countCars
                    if (message.status.countBikes != "") currentStatus.countBikes = message.status.countBikes
                    if (message.status.countBabies != "") currentStatus.countBabies = message.status.countBabies


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
                    left = 10.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                if (leftMessages.size >= maxMessages ) {
                    onClick = {
                        props.onSelectPension(
                            props.selectedView.getNewView("pension"),
                            leftMessages,
                            person,
                            messageList,
                            currentStatus,
                            life
                        )
                    }
                    +props.selectedView.buttonText

                } else {
                    onClick = {
                        props.onSelectPension(
                            props.selectedView.getNewView("pension"),
                            leftMessages,
                            person,
                            messageList,
                            currentStatus,
                            life
                        )
                    }
                    +props.selectedView.getNewView("pension").buttonText
                }
                +" ▶"
            }
        }

        if (currentStatus.age == "") currentStatus.age = life.person.age.toString()
        ShowStatusRow {
            actualAge = currentStatus.age
            actualName = life.person.name
            actualPension = (life.person.pension * 100).toString()
            actualProfession = currentStatus.profession
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
        }
    }
}











