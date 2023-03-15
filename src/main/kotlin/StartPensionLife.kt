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
    var selectedLife: Life
    var selectedStyle: Style

    var onSelectPension: (View, Life) -> Unit
}

val StartPensionLife = FC<StartPensionLifeProps> { props ->

    // Init person life
    var life: Life = props.selectedLife
    val currentLife: Life

    //Init story
    var messageList: List<Message> = emptyList()
    var leftMessages: List<Message> = emptyList()
    val maxMessages = 100

    // starting your pension life
        life.messageList = emptyList()
        life.lastMessageId = 0

        currentLife = pensionLife(life)

        //Get what happened in your life
        life = currentLife
        messageList = currentLife.messageList

    //Show story
    if (messageList.isNotEmpty()) {

        div {
            css {
                display = Display.block
                position = Position.absolute
                top = props.selectedStyle.topPXStory01.px
                left = props.selectedStyle.leftPXStory01.px

                color = NamedColor.green
                borderColor = NamedColor.white
                fontSize = props.selectedStyle.fontMedium.px
                backgroundColor = NamedColor.transparent
                fontFamily = FontFamily.cursive
                width = 1000.px
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

        ShowPensionRow {
            actualPerson = life.person
            actualAge = life.age.toString()
            actualName = life.person.name
            actualPension = (life.person.pension * 100.0F).toInt().formatDecimalSeparator()
            actualProfession = getProfession(life.professionId).title
            firstSalary = life.firstSalary.toInt().formatDecimalSeparator()
            actualSalary = life.employee.currentSalary.toInt().formatDecimalSeparator()
            actualWorkYear = (life.person.countWorkMonth/12).formatDecimalSeparator()
            actualSickMonth = (life.person.countSickMonth).formatDecimalSeparator()
            actualParentMonth = (life.person.countParentMonth).formatDecimalSeparator()
            actualSalaryAmount = life.accountSalary.amount.toInt().formatDecimalSeparator()
            actualNoAkassaAmount = (life.accountSalary.amount - life.accountNoAkassa.amount).toInt().formatDecimalSeparator()
            actualDepotAmount = life.accountDepot.amount.toInt().formatDecimalSeparator()
            actualPensionAmount = life.accountPension.amount.toInt().formatDecimalSeparator()
            actualWorkPensionAmount = life.accountWorkPension.amount.toInt().formatDecimalSeparator()
            actualTaxPensionAmount = life.accountTaxPension.amount.toInt().formatDecimalSeparator()
            actualDepotAmount = life.accountDepot.amount.toInt().formatDecimalSeparator()
            actualTaxAmount = life.accountTax.amount.toInt().formatDecimalSeparator()
            actualHireAmount = life.person.house.houseMonthPayment.toInt().formatDecimalSeparator()
            actualHouseAmount =  life.person.house.houseAmount.toInt().formatDecimalSeparator()
            actualLoanAmount = life.person.house.houseLoan.loanAmount.toInt().formatDecimalSeparator()
            actualLoanMonthPayment = life.person.house.houseLoan.loanMonthPayment.toInt().formatDecimalSeparator()
            actualInterestMonthPayment = life.person.house.houseLoan.calculateInterest().toInt().formatDecimalSeparator()
            actualCats = life.person.cats.size.toString()
            actualDogs = life.person.dogs.size.toString()
            actualHorses = life.person.horses.size.toString()
            actualCars = life.person.cars.size.toString()
            actualBabies = life.parent.countBabies.toString()
            actualBikes = life.person.bikes.size.toString()
            actualParties = (life.person.parties.size + life.person.deadParties.size).toString()
            actualWalking = life.person.countWalking.toString()
            actualFishing = life.person.countFishing.toString()
            actualFriends = life.person.countFriends.toString()
            actualBoats = life.person.boats.size.toString()
            actualLoves = life.person.countLove.toString()
            actualStrong = life.person.countStrong.toString()
            actualDeadCats = life.person.deadCats.size.toString()
            actualDeadDogs = life.person.deadDogs.size.toString()
            actualDeadHorses = life.person.deadHorses.size.toString()
            actualDeadBikes = life.person.deadBikes.size.toString()
            actualDeadCars = life.person.deadCars.size.toString()
            actualDeadBoats = life.person.deadBoats.size.toString()
            actualStyle = props.selectedStyle
        }
    }
}

fun pensionLife(life: Life): Life {
    val currentLife = life

    //Init story
    var messageList = life.messageList
    var messageId = life.lastMessageId

    // Du är pensionär
    messageList = life.showPensionLife(messageList, messageId)
    messageId = messageList[messageList.size - 1].id

    currentLife.messageList = messageList
    currentLife.lastMessageId = messageId

    return currentLife
}














