
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

    val professions: List<Profession> = listOf(
        Profession(0, "solo","Wow, du startar eget företag som systemutvecklare",
            0.0,1.0, 2000.0,58, "salary"),
        Profession(1, "security", "Wow, du får jobb på ett säkerhetsföretag som säkerhetsspecialist",
            0.0, 1.0, 2500.0,58, "salary"),

        Profession(2, "bank", "Du tar jobb som utvecklare på en bank",
            1.0, 0.0, 1000.0,58, "pension"),
        Profession(3, "insurance", "Du tar jobb på ett försäkringsbolag",
            1.0, 0.0, 900.0,58, "pension"),

        Profession(4, "secret", "Du tar jobb som utvecklare på ett hemligt uppdrag",
            1.0, 0.0, 3000.0,50, "adventure"),
        Profession(5, "police", "Du tar jobb hos polisen",
            1.0, 0.0, 2000.0,50, "adventure"),

        Profession(6, "authority", "Du tar jobb som utvecklare på en myndighet",
            1.0, 0.0, 500.0,60, "vacation"),
        Profession(7, "teacher", "Du tar jobb på en resebyrå",
            1.0, 0.0, 900.0,60, "vacation"),

        Profession(8, "family", "Du tar jobb som utvecklare på ett företag med bästa villkor för barnledig",
            1.0, 0.0, 1000.0,65, "family"),
        Profession(9, "school", "Du tar jobb som utvecklare på ett dagis",
            1.0, 0.0, 800.0,65, "family"),

        Profession(10, "magellit", "Wow, Du blir träffad av en magellit!",
            1.0, 0.0, 0.0,75, "chilla"),
        Profession(11, "terapeut", "Du behöver träffa en terapeut",
            1.0, 0.0, 0.0,75, "chilla")
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

    var person: Person
    var union = Union(0)
    var insurance = Insurance("healthinsurance")
    var employee = Employee(0)
    var accountPension = Account("pension")
    var accountSalary = Account("salary")
    var accountDepot = Account("depot")
    var sumDouble: Double

    // Initiera selected items

    var onSelectView: (View) -> Unit

    var name: String by useState("")
    var age: String by useState("")

    var currentView: View by useState(views[0])

    var currentQuestion: Question? by useState(null)
    var currentAction: Question? by useState(null)

    var unSelectedQuestions: List<Question> by useState(emptyList())
    var selectedQuestions: List<Question> by useState(emptyList())
    var selectedProfessions: List<Profession> by useState(emptyList())

    var topPX: Int
    val randomValues = List(10) { Random.nextInt(0, 100) }

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
                                if (name.isNotBlank() && age.isNotBlank()) {
                                    onSelectView(views[currentView.nextViewId])
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

                                color = NamedColor.green
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
                                    }
                                }

                                "age" -> {
                                    value = age
                                    onChange = { event ->
                                        age = event.target.value
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
                div {
                    ActionList {
                        actions = currentView.questions
                        selectedAction = currentAction

                        onSelectAction = { question ->
                            currentAction = question

                            selectedProfessions = emptyList()
                            for ((professionIndex, profession) in professions.withIndex()) {
                                if (question.objectType == profession.objectType) {
                                    if (randomValues[0] < 50 && professionIndex == 0 || randomValues[0] >= 50 && professionIndex == 1 )
                                        selectedProfessions = selectedProfessions.plus(profession)
                                }
                            }
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
                if (name.isEmpty()) {
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
                if (age.isEmpty()) {
                    +" "
                } else {
                    +inputQuestions[1].objectText
                    +": $age "
                }
            }
        }

        div {
            if (selectedQuestions.isNotEmpty()) {
                topPX = 540
                for (question in selectedQuestions) {
                    when (question.objectType) {
                        "akassa" -> {
                            union.akassa = true
                            union.unEmployedSalaryAmount = 0.5
                        }

                        "incomeinsurance" -> {
                            union.incomeInsurance = true
                            union.unEmployedSalaryAmount = 0.8
                        }

                        "healthinsurance" -> {
                            insurance.sickSalaryAmount = 30810.0
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

        div {
            for (profession in selectedProfessions) {
                topPX = 540
                person = Person(name)
                person.age = age.toInt()
                employee.salary = profession.salary * person.age
                employee.salaryFixedPercentage = profession.salaryFixedPercentage
                employee.salaryVariablePercentage = profession.salaryVariablePercentage
/*
                when (profession.objectType) {
                    "adventure" -> {
                        +"adventure"
                    }

                    "vacation" -> {
                        +"vacation"
                    }

                    "pension" -> {
                        +"pension"
                    }

                    "salary" -> {
                        +"salary"
                    }

                    "family" -> {
                        +"family"
                    }

                    "chilla" -> {
                        +"chilla"
                    }

                }

 */
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = 2.px
                        left = 10.px
                        color = NamedColor.green
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +profession.professionText
                    +"!"
                }
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = 500.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +"Fast lön: "
                    sumDouble = employee.salaryFixedPercentage * 100
                    +sumDouble.toString()

                    topPX += 30
                }
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = 500.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +"Rörlig lön: "
                    sumDouble = employee.salaryVariablePercentage * 100
                    +sumDouble.toString()

                    topPX += 30
                }
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = 500.px
                        color = NamedColor.black
                        fontSize = 18.px
                        backgroundColor = NamedColor.white
                        fontFamily = FontFamily.cursive
                    }
                    +"Lön: "
                    +employee.salary.toString()
                }
            }
        }
    }
}


