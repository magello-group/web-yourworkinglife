
import csstype.*
import react.*
import kotlinx.coroutines.*
import emotion.react.css
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.select
import kotlin.random.Random

val mainScope = MainScope()

val App = FC<Props> {
    val question: Question = Question(0)
    val view: View = View(0)

    val inputQuestions: List<Question> = question.getQuestionList("input")
    val unionQuestions: List<Question> = question.getQuestionList("union")

    // Initiera selected items
    val onSelectView: (View) -> Unit

    var name: String by useState("")
    var age: String by useState("")
    var pension: String by useState("")

    var currentView: View by useState(view.getViewList("init"))

    var currentQuestion: Question? by useState(null)
    var currentAction: Question? by useState(null)
    var currentProfession: Profession by useState(Profession(999))
    var currentMessages: List<Message> by useState(emptyList())
    var currentPerson: Person by useState(Person(0))

    var unSelectedQuestions: List<Question> by useState(emptyList())
    var selectedQuestions: List<Question> by useState(emptyList())

    // Initiera arbetslivet
    val person = currentPerson
    val insurance = Insurance(person.id, "healthinsurance")
    person.insurances = person.insurances.plus(insurance)

    useEffectOnce {
        mainScope.launch {
            unSelectedQuestions = unionQuestions
            selectedQuestions = emptyList()
        }
    }

    onSelectView = {
            view -> currentView = view

        unSelectedQuestions = view.questions
    }

    div {
        div {
            when (currentView.viewType) {
                "init" -> {
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
                                    onSelectView(view.getViewList(currentView.nextViewType))
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
                                left = 200.px

                                color = NamedColor.red
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
                                "name" -> top = 90.px
                                "age" -> top = 130.px
                                "pension" -> top = 170.px

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

                div {

                    QuestionList {
                        questions = currentView.questions
                        selectedQuestion = currentQuestion
                        clickedQuestions = selectedQuestions

                        onSelectQuestion = { question ->
                            currentQuestion = question

                            if (unSelectedQuestions.isNotEmpty()) {
                                if (unSelectedQuestions.contains(question)) {
                                    unSelectedQuestions = unSelectedQuestions.minus(question)
                                    selectedQuestions = selectedQuestions.plus(question)
                                    clickedQuestions = selectedQuestions
                                }
                            }
                            if (selectedQuestions.isNotEmpty()) {
                                if (selectedQuestions.contains(question)) {
                                    selectedQuestions = selectedQuestions.minus(question)
                                    unSelectedQuestions = unSelectedQuestions.plus(question)
                                    clickedQuestions = selectedQuestions
                                }
                            }
                        }
                    }
                }
            }


            "action" -> {
                person.name = name
                person.age = age.toInt()
                person.pension = pension.toDouble() * 0.01
                div {
                    if (currentProfession.id == 999) {
                        ActionList {
                            actions = currentView.questions
                            selectedAction = currentAction
                            workingProfession = currentProfession
                            workingPerson = person

                            onSelectAction = { question ->
                                currentAction = question
                            }

                            onSelectProfession = { profession ->
                                currentProfession = profession
                            }
                        }
                    } else {
                        StartWorkingLife {
                            selectedProfession = currentProfession
                            selectedPerson = person
                            selectedMessages = currentMessages

                            onSelectMessages = { messages, profession, person ->
                                currentMessages = messages
                                currentProfession = profession
                                currentPerson = person
                            }
                        }
                    }
                }
            }
        }

        div {
            ShowStreckGubbe{}

            ShowStreck{}
        }

        div {
            ShowInput {
                actualInputQuestions = inputQuestions
                actualName = name
                actualAge = age
                actualPension = pension
            }

            var topPX: Int

            if (selectedQuestions.isNotEmpty()) {
                topPX = 570
                div {
                    for (question in selectedQuestions) {
                        when (question.objectType) {
                            "akassa" -> {
                                person.union.akassa = true
                            }

                            "incomeinsurance" -> {
                                person.union.incomeInsurance = true
                            }

                            "extrainsurance" -> {
                                person.union.extraInsurance = true
                            }

                            "healthinsurance" -> {
                                if (!person.insurances.contains(insurance)) {
                                    person.insurances = person.insurances.plus(insurance)
                                }
                            }
                        }
                        p {
                            css {
                                display = Display.block
                                position = Position.absolute
                                top = topPX.px
                                left = 200.px
                                color = NamedColor.black
                                fontSize = 18.px
                                backgroundColor = NamedColor.white
                                fontFamily = FontFamily.cursive
                            }
                            +question.objectText
                            +" ✔"

                            topPX += 30
                        }
                    }
                }
            }
        }
    }
}





