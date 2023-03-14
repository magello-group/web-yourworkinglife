
import csstype.*
import react.*
import kotlinx.coroutines.*
import emotion.react.css
import kotlinx.js.import
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.textarea
import react.dom.html.ReactHTML.tr

val mainScope = MainScope()

val App = FC<Props> {

    val appStyle = Style("standard")
    val question = Question(0)
    val view = View(0)

    val inputQuestions: List<Question> = question.getQuestionList("input")
    val unionQuestions: List<Question> = question.getQuestionList("union")

    // Initiera selected items
    val onSelectView: (View) -> Unit

    var name: String by useState("")
    var age: String by useState("")
    var pension: String by useState("")

    var currentView: View by useState(view.getViewList()[0])

    var currentQuestion: Question? by useState(null)
    var currentEvent: Event by useState(Event(999))

    var currentAction: Question? by useState(null)
    var currentProfession: Profession by useState(Profession(999))

    var currentMessages: List<Message> by useState(emptyList())
    var historyMessages: List<Message> by useState(emptyList())
    var currentPerson: Person by useState(Person(0))
    var currentLife: Life by useState(Life(0))
    var currentStatus: Status by useState(Status(0))

    var unSelectedQuestions: List<Question> by useState(emptyList())
    var selectedQuestions: List<Question> by useState(emptyList())
    var checkInt: Int?
    var checkDouble: Double?

    // Initiera arbetslivet
    val person: Person = currentPerson

    useEffectOnce {
        mainScope.launch {
            unSelectedQuestions = unionQuestions
            selectedQuestions = emptyList()
        }
    }

    //Initiate view
    onSelectView = { newView ->
        currentView = newView

        unSelectedQuestions = newView.questions
        currentLife.firstStep = true
    }

    // Top button
    div {
        div {

            if (currentView.viewType == "init") {
                // Initiera arbetslivet

                p {
                    button {

                        key = currentView.id.toString()
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = appStyle.topPXbutton01.px
                            left = appStyle.leftPXbutton01.px

                            color = NamedColor.green
                            borderColor = NamedColor.white
                            fontSize = appStyle.fontMedium.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }

                        onClick = {
                            if (name.isNotBlank() && age.isNotBlank() && pension.isNotBlank()) {
                                onSelectView(view.getNextView())
                            }
                        }
                        +currentView.buttonText
                        +" ▶"
                        //+"◀ "
                    }
                }

                //Check if input is correct
                checkInt = age.toIntOrNull()
                if (checkInt != null) {
                    if (checkInt!! > 58.0 || checkInt!! < 15.0)
                        checkInt = null
                }
                checkDouble = pension.toDoubleOrNull()
                if (checkDouble != null) {
                    if (checkDouble!! > 50.0 || checkDouble!! < 0.0)
                        checkDouble = null
                }

                if (name.isBlank() || age.isBlank() || pension.isBlank() || checkInt == null || checkDouble == null) {
                    p {
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = appStyle.topPXOBSText00.px
                            left = appStyle.leftPXOBSText00.px
                            color = NamedColor.hotpink
                            borderColor = NamedColor.white
                            fontSize = appStyle.fontSmall.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }
                        +"OBS: Ange namn, ålder och pension innan du går till nästa steg!"
                    }
                }
            }
        }

        div {

            //Main title
            h1 {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = appStyle.topPXTitle.px
                    left = appStyle.leftPXTitle.px
                    color = NamedColor.black
                    width = 800.px
                    //fontSize = appStyle.fontLarge.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +currentView.title
            }
        }

        // Welcome menu
        when (currentView.viewType) {
            "init" -> {
                div {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = appStyle.topPXTable02.px
                        left = appStyle.leftPXTable02.px
                        fontFamily = FontFamily.cursive
                    }

                    table {
                        css {
                            width = 800.px
                            borderSpacing = 0.px
                            borderCollapse = BorderCollapse.collapse
                            whiteSpace = WhiteSpace.nowrap
                            border = Border(0.px, LineStyle.solid, NamedColor.white)
                            margin = Auto.auto

                        }

                        tbody {
                            css {
                                color = NamedColor.black
                                backgroundColor = NamedColor.white
                                textAlign = TextAlign.start
                            }

                            for (input in inputQuestions) {
                                tr {
                                    css {
                                        fontSize = appStyle.fontMedium.px
                                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                                        hover {
                                            backgroundColor = NamedColor.lightgrey
                                        }
                                        maxHeight = 10.px
                                    }

                                    td {

                                        css {
                                            padding = Padding(0.px, 0.px)
                                            width = 300.px
                                            color = NamedColor.black
                                            fontSize = appStyle.fontMedium.px
                                            fontFamily = FontFamily.cursive
                                        }
                                        +input.questionText
                                        +" "
                                    }
                                    td {

                                        css {
                                            padding = Padding(0.px, 0.px)
                                            height = 5.px
                                            color = NamedColor.black
                                            fontSize = appStyle.fontMedium.px
                                            fontFamily = FontFamily.cursive
                                        }
                                        input {
                                            css {
                                                display = Display.block
                                                borderTop = Border(1.px, LineStyle.solid, NamedColor.white)
                                                borderLeft = Border(1.px, LineStyle.solid, NamedColor.white)
                                                borderRight = Border(1.px, LineStyle.solid, NamedColor.white)
                                                borderBottom = Border(1.px, LineStyle.solid, NamedColor.grey)
                                                height = 25.px
                                                marginTop = 1.pc
                                                marginBottom = 0.pc
                                                fontSize = appStyle.fontMedium.px
                                                fontFamily = FontFamily.cursive
                                                when (input.objectType) {
                                                    "name" -> width = appStyle.widthPXInputName.px
                                                    "age" -> width = appStyle.widthPXInputAge.px
                                                    "pension" -> width = appStyle.widthPXInputPension.px
                                                }
                                            }
                                            type = InputType.text
                                            when (input.objectType) {
                                                "name" -> {
                                                    value = name
                                                    onChange = { event ->
                                                        name = event.target.value
                                                    }
                                                }

                                                "age" -> {
                                                    value = age
                                                    onChange = { event ->
                                                        age = event.target.value
                                                    }
                                                }

                                                "pension" -> {
                                                    value = pension
                                                    onChange = { event ->
                                                        pension = event.target.value
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                //Show animation
                ShowInitAnimation {
                    actualMarginLeft = 30
                    actualStyle = appStyle
                }

                //Show notes
                div {
                    ShowInput {
                        actualInputQuestions = inputQuestions
                        actualName = name
                        actualAge = age
                        actualPension = pension
                        actualStyle = appStyle
                        actualStartTopPX = appStyle.topPX02
                    }

                    person.union.isAkassa = true
                    person.union.isIncomeInsurance = true
                    person.union.isExtraInsurance = true
                    person.isHealthInsurance = true
                }
            }

            "action" -> {
                //Store name, age and pension percentage
                person.name = name
                person.age = age.toInt()
                person.startWorkingAge = age.toInt()
                person.pension = pension.toFloat() * 0.01F

                //Initiate lists of person profession and accounts
                person.professions = emptyList()
                person.accounts = emptyList()

                div {

                    ActionList {
                        selectedView = currentView
                        selectedAction = currentAction
                        //workingProfession = currentProfession
                        workingPerson = person
                        selectedStyle = appStyle

                        onSelectAction = { question ->
                            currentAction = question
                        }

                        onSelectGoal = { profession ->
                            currentProfession = profession
                            currentView = view.getNewView("profession")
                        }
                    }
                }
                //Show animation
                ShowActionAnimation {
                    actualMarginLeft = 26
                    actualStyle = appStyle
                }

                ShowInput {
                    actualInputQuestions = inputQuestions
                    actualName = name
                    actualAge = age
                    actualPension = pension
                    actualStyle = appStyle
                    actualStartTopPX = appStyle.topPX02
                }
            }

            "start", "reload" -> {
                div {
                    person.name = name
                    person.age = age.toInt()
                    person.pension = pension.toFloat() * 0.01F

                    StartMiddleOfLife {
                        selectedView = currentView
                        selectedProfession = currentProfession
                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedHistory = historyMessages
                        selectedStatus = currentStatus
                        selectedLife = currentLife
                        selectedEvent = currentEvent
                        selectedStyle = appStyle

                        onSelectMessages =
                            { newView, newMessages, newProfession, newPerson, newHistory, newStatus, newLife ->
                                currentView = newView
                                currentMessages = newMessages
                                currentProfession = newProfession
                                currentPerson = newPerson
                                historyMessages = newHistory
                                age = newStatus.age
                                currentStatus = newStatus
                                currentLife = newLife
                            }
                    }
                }

                //Show animation
                ShowProfessionAnimation {
                    actualProfession = currentProfession
                    actualMarginLeft = 38
                    actualStyle = appStyle
                    actualCloudMarginLeftTo = 38
                }
            }

            "luck", "depressed" -> {

                div {

                    EventList {
                        selectedView = currentView
                        selectedQuestion = currentQuestion
                        selectedProfession = currentProfession
                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedHistory = historyMessages
                        selectedStatus = currentStatus
                        selectedLife = currentLife
                        selectedEvent = currentEvent
                        selectedStyle = appStyle

                        onSelectQuestion = { question, newEvent, newView, newMessages, newProfession,
                                             newPerson, newHistory, newStatus, newLife ->
                            currentEvent = newEvent
                            currentView = newView
                            currentMessages = newMessages
                            currentQuestion = question
                            currentProfession = newProfession
                            currentPerson = newPerson
                            historyMessages = newHistory
                            age = newPerson.age.toString()
                            currentStatus = newStatus
                            currentLife = newLife
                        }

                        onSelectEvent = { newEvent, newView, newMessages, newProfession,
                                          newPerson, newHistory, newStatus, newLife ->
                            currentEvent = newEvent
                            currentView = newView
                            currentMessages = newMessages
                            currentProfession = newProfession
                            currentPerson = newPerson
                            historyMessages = newHistory
                            age = newPerson.age.toString()
                            currentStatus = newStatus
                            currentLife = newLife
                        }
                    }
                }

                //Show animation
                ShowEventAnimation {
                    actualProfession = currentProfession
                    actualMarginLeft = 26
                    actualStyle = appStyle
                }
            }

            "profession", "question" -> {
                div {

                    ProfessionList {
                        selectedView = currentView
                        selectedProfession = currentProfession
                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedHistory = historyMessages
                        selectedStatus = currentStatus
                        selectedLife = currentLife
                        selectedEvent = currentEvent
                        selectedStyle = appStyle

                        onSelectQuestion = { newEvent, newView, newMessages, question,
                                             newPerson, newHistory, newStatus, newLife ->
                            currentProfession = question
                            currentEvent = newEvent
                            currentView = newView
                            currentMessages = newMessages
                            currentPerson = newPerson
                            historyMessages = newHistory
                            age = newPerson.age.toString()
                            currentStatus = newStatus
                            currentLife = newLife
                        }

                        onSelectProfession = { newEvent, newView, newMessages, newProfession,
                                               newPerson, newHistory, newStatus, newLife ->
                            currentEvent = newEvent
                            currentView = newView
                            currentMessages = newMessages
                            currentProfession = newProfession
                            currentPerson = newPerson
                            historyMessages = newHistory
                            age = newPerson.age.toString()
                            currentStatus = newStatus
                            currentLife = newLife
                        }
                    }
                }

                //Show animation
                ShowProfessionAnimation {
                    actualProfession = currentProfession
                    actualMarginLeft = 26
                    actualStyle = appStyle
                    actualCloudMarginLeftTo = 26
                }

                ShowInput {
                    actualInputQuestions = inputQuestions
                    actualName = currentPerson.name
                    actualAge = currentPerson.age.toString()
                    actualPension = (currentPerson.pension * 100).toString()
                    actualStyle = appStyle
                    actualStartTopPX = appStyle.topPX06
                }
            }

            "pension" -> {
                div {

                    StartPensionLife {
                        selectedView = currentView
                        selectedLife = currentLife
                        selectedStyle = appStyle

                        onSelectPension =
                            { newView, newLife ->
                                currentView = newView
                                currentLife = newLife
                            }
                    }
                }

                //Show animation
                ShowPensionerAnimation {
                    actualMarginLeft = 26
                    actualStyle = appStyle
                }
            }
        }
    }
}

fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
}





