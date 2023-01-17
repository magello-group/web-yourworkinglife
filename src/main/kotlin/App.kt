
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

val mainScope = MainScope()

val App = FC<Props> {

    val professions: List<Profession> = listOf(
        Profession("0", "hemlig", 25000.0,65, "spänning"),
        Profession("1", "egen", 25000.0,65, "lön"),
        Profession("2", "myndighet", 25000.0,65, "semester"),
        Profession("3", "magellit", 25000.0,65, "chilla"),
        Profession("4", "barn", 25000.0,65, "familj"),
        Profession("5", "bank", 25000.0,65, "pension")
    )

    val imagesStreckGubbe: List<String> = listOf("streckgubbe100.jpg","streckgubbe200.jpg","streckgubbe300.jpg",
        "streckgubbe400.jpg","streckgubbe500.jpg","streckgubbe600.jpg",
        "streckgubbe700.jpg", "streckgubbe800.jpg","streckgubbe900.jpg","streckgubbe1000.jpg",
        "streckgubbe1100.jpg","streckgubbe1200.jpg","streckgubbe1300.jpg","streckgubbe1500.jpg")

    val imagesStreck: List<String> = listOf("streck002.jpg","streck003.jpg","streck004.jpg")

    val inputQuestions: List<Question> = listOf(
        Question(0,"Vad heter du?", "Namn", "name", 0),
        Question(1,"I vilken ålder börjar du jobba?", "Ålder","age",0),
    )

    val unionQuestions: List<Question> = listOf(
        Question(0,"Går du med i a-kassan?", "A-kassa", "akassa", 0),
        Question(1,"Går du med i facket och får inkomstförsäkring?", "Inkomstförsäkring", "incomeinsurance", 0),
        Question(2, "Tecknar du olycksfallsförsäkring?", "Olycksfallsförsäkring", "healthinsurance",0)
    )

    val goalQuestions: List<Question> = listOf(
        Question(0,"Maxa spänningen", "spänning", "adventure",1),
        Question(1, "Bilda familj med massor av barn", "familj", "family", 1),
        Question(2, "Maxa semesterdagarna", "semester", "vacation",1),
        Question(3, "Chilla", "chilla", "chilla", 1),
        Question(4,"Maxa lönen", "lön", "salary", 1),
        Question(5, "Maxa pensionen", "pension", "pension",1),
    )

    val views: List<View> = listOf(
        View(0, "init", unionQuestions, "Ditt arbetsliv börjar här... gör dig redo:","Nästa steg", 1),
        View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "Start arbetslivet", 1)
    )
    // Initiera arbetslivet

    var person = Person(0,"",false,false,20,null)
    var union = Union("0",person,false,"0",false,0.0)
    var insurance = Insurance("0", "",0, 0.0)
    var employee = Employee("0", person, 0.0,0.0,0.0,0)
    var accountPension = Account("0", "pension",0.0)
    var accountSalary = Account("0", "lön",0.0)
    var accountDepot = Account("0", "depot",0.0)

    person.accounts.add(accountPension)
    person.accounts.add(accountSalary)
    person.accounts.add(accountDepot)

    // initiera selected items

    var onSelectView: (View) -> Unit
    var onSelectAction: (Question) -> Unit

    var name: String by useState("")
    var age: String by useState("")

    var currentView: View by useState(views[0])

    var currentQuestion: Question? by useState(null)
    var currentAction: Question? by useState(null)

    var unSelectedQuestions: List<Question>? by useState(emptyList())
    var selectedQuestions: List<Question>? by useState(emptyList())


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

    onSelectAction = {
            action -> currentAction = action

    }

    div {
        div {
            when (currentView.questionType) {
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
                                onSelectView(views[currentView.nextViewId])
                            }
                            +currentView.buttonText
                            +" ▶"
                            //+"◀ "
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
        when (currentView.questionType) {
            "init" -> {
                for (input in inputQuestions) {
                    div {
                        css {
                            display = Display.block
                            position = Position.absolute
                            when (input.objectType) {
                                "name" -> top = 90.px
                                "age" -> top = 130.px
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
                            }
                            type = InputType.text
                            when (input.objectType) {
                                "name" -> {
                                    value = name
                                    onChange = { event ->
                                        name = event.target.value
                                        person.personName = event.target.value
                                    }
                                }

                                "age" -> {
                                    value = age
                                    onChange = { event ->
                                        age = event.target.value
                                        person.age = event.target.value.toInt()
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
                        clickedQuestions = selectedQuestions!!

                        onSelectQuestion = { question ->
                            currentQuestion = question

                            if (!unSelectedQuestions.isNullOrEmpty()) {
                                if (unSelectedQuestions?.contains(question) == true) {
                                    unSelectedQuestions = unSelectedQuestions?.minus(question)
                                    selectedQuestions = selectedQuestions?.plus(question)
                                    clickedQuestions = selectedQuestions!!
                                }
                            }
                            if (!selectedQuestions.isNullOrEmpty()) {
                                if (selectedQuestions?.contains(question) == true) {
                                    selectedQuestions = selectedQuestions?.minus(question)
                                    unSelectedQuestions = unSelectedQuestions?.plus(question)
                                    clickedQuestions = selectedQuestions!!
                                }
                            }
                        }
                    }
                }
            }


            "action" -> {
                div {
                    ActionList {
                        questions = currentView.questions
                        selectedQuestion = currentQuestion

                        onSelectQuestion = { question ->
                            currentQuestion = question
                        }
                    }
                }
            }
        }

        div {

            showStreckGubbe {
                images = imagesStreckGubbe
            }
        }

        div {
            showStreck {
                images = imagesStreck
            }
        }

        div {
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 540.px
                    left = 10.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                if (name.isNullOrEmpty()) {
                    +" "
                } else {
                    +inputQuestions[0].objectText
                    +": $name "
                }
            }

            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 570.px
                    left = 10.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                if (age.isNullOrEmpty()) {
                    +" "
                } else {
                    +inputQuestions[1].objectText
                    +": $age "
                }
            }
        }

        div {
            if (!selectedQuestions.isNullOrEmpty()) {
                var i = 540
                for (question in selectedQuestions!!) {
                    when (question) {
                        unionQuestions[0] -> {
                            union.akassa = true
                            union.unEmployedSalaryAmount = 0.5
                        }

                        unionQuestions[1] -> {
                            union.incomeInsurance = true
                            union.unEmployedSalaryAmount = 0.8
                        }

                        unionQuestions[2] -> {
                            insurance.insuranceType = "olycksfall"
                            insurance.sickSalaryAmount = 30810.0
                            person.insurances.add(insurance)
                        }
                    }
                    p {
                        css {
                            display = Display.block
                            position = Position.absolute
                            top = i.px
                            left = 200.px
                            color = NamedColor.black
                            fontSize = 18.px
                            backgroundColor = NamedColor.white
                            fontFamily = FontFamily.cursive
                        }
                        +question.objectText
                        +" ✔"

                        i += 30
                    }
                }
            }
        }
    }
}


