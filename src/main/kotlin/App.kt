
import csstype.*
import react.*
import kotlinx.coroutines.*
import emotion.react.css
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p

val mainScope = MainScope()

val App = FC<Props> {
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
    }

    // Top button
    div {
        div {
            when (currentView.viewType) {
                "init" -> {
                    // Initiera arbetslivet

                    p {
                        button {

                            key = currentView.id.toString()
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
                                if (name.isNotBlank() && age.isNotBlank()) {
                                    onSelectView(view.getNextView())
                                }
                            }
                            +currentView.buttonText
                            +" ▶"
                            //+"◀ "
                        }
                    }
                    if (name.isBlank() || age.isBlank()) {
                        p {
                            css {
                                display = Display.block
                                position = Position.absolute
                                top = 6.px
                                left = 160.px

                                color = NamedColor.hotpink
                                borderColor = NamedColor.white
                                fontSize = 14.px
                                backgroundColor = NamedColor.white
                                fontFamily = FontFamily.cursive
                            }
                            +"OBS: Ange namn och ålder innan du går till nästa steg!"
                        }
                    }
                }
            }
            //Main title
            h1 {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 30.px
                    left = 10.px
                    color = NamedColor.black
                    fontSize = 26.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +currentView.title
            }
        }

        // Welcome menu
        when (currentView.viewType) {
            "init" -> {
                for (input in inputQuestions) {
                    div {
                        css {
                            display = Display.block
                            position = Position.absolute
                            when (input.objectType) {
                                "name" -> top = 100.px
                                "age" -> top = 140.px
                                "pension" -> top = 180.px

                            }
                            left = 10.px
                            color = NamedColor.black
                            fontSize = 18.px
                            fontFamily = FontFamily.cursive
                        }
                        +input.questionText
                        +" "

                        input {
                            css {
                                marginTop = 5.px
                                marginBottom = 5.pc
                                fontSize = 18.px
                                fontFamily = FontFamily.cursive
                                when (input.objectType) {
                                    "name" -> width = 400.px
                                    "age" -> width = 25.px
                                    "pension" -> width = 25.px

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

                //Show animation
                div {
                    ShowStreckGubbe {}

                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }

                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 26
                        marginLeftTo = 0
                    }
                }

                //Show notes
                div {
                    ShowInput {
                        actualInputQuestions = inputQuestions
                        actualName = name
                        actualAge = age
                        actualPension = pension
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
                div {
                    ShowSparkcykel {}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }

                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 0
                        marginLeftTo = 26
                    }
                }

                ShowInput {
                    actualInputQuestions = inputQuestions
                    actualName = name
                    actualAge = age
                    actualPension = pension
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

                ShowAction {
                    actualProfession = currentProfession
                    actualAge = currentPerson.startWorkingAge.toString()
                }

                //Show animation
                ShowProfessionAnimation {
                    actualProfession = currentProfession
                }
            }
            "question", "luck" -> {

                div {

                    EventList {
                        selectedView = currentView
                        selectedQuestion = currentQuestion

                        onSelectQuestion = { question ->
                            currentQuestion = question
                        }

                        selectedProfession = currentProfession
                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedLife = currentLife

                        onSelectEvent = { newEvent, newView, newMessages, newProfession, newPerson, newLife ->
                            currentEvent = newEvent
                            currentView = newView
                            currentMessages = newMessages
                            currentProfession = newProfession
                            currentPerson = newPerson
                            age = newPerson.age.toString()
                            currentLife = newLife
                        }
                    }
                }
                //Show animation
                div {
                    ShowSparkcykel {}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }

                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 0
                        marginLeftTo = 26
                    }
                }

                ShowInput {
                    actualInputQuestions = inputQuestions
                    actualName = currentPerson.name
                    actualAge = currentPerson.age.toString()
                    actualPension = currentPerson.pension.toString()
                }
            }
            "profession" -> {

                div {

                    ProfessionList {
                        selectedView = currentView
                        selectedProfession = currentProfession
                        onSelectQuestion = { question ->
                            currentProfession = question
                        }

                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedLife = currentLife

                        onSelectProfession = { newView, newMessages, newProfession, newPerson, newLife ->
                            currentView = newView
                            currentMessages = newMessages
                            currentProfession = newProfession
                            currentPerson = newPerson
                            age = newPerson.age.toString()
                            currentLife = newLife
                        }
                    }
                }

                //Show animation
                ShowAction {
                    actualProfession = currentProfession
                    actualAge = currentPerson.startWorkingAge.toString()
                }

                //Show animation
                ShowProfessionAnimation {
                    actualProfession = currentProfession
                }

                ShowInput {
                    actualInputQuestions = inputQuestions
                    actualName = currentPerson.name
                    actualAge = currentPerson.age.toString()
                    actualPension = currentPerson.pension.toString()
                }
            }
            "pension" -> {
                div {

                    StartPensionLife {
                        selectedView = currentView
                        selectedPerson = person
                        selectedMessages = currentMessages
                        selectedHistory = historyMessages
                        selectedStatus = currentStatus
                        selectedLife = currentLife

                        onSelectPension =
                            { newView, newMessages, newPerson, newHistory, newStatus, newLife ->
                                currentView = newView
                                currentMessages = newMessages
                                currentPerson = newPerson
                                historyMessages = newHistory
                                age = newStatus.age
                                currentStatus = newStatus
                                currentLife = newLife
                            }
                    }
                }

                //Show animation
                div {
                    ShowSparkcykel {}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }

                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 0
                        marginLeftTo = 26
                    }
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





