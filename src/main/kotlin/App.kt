
import csstype.*
import kotlinx.coroutines.async
import react.*
import react.dom.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import emotion.react.css
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.img

val mainScope = MainScope()

val App = FC<Props> {
    var currentQuestion: Question? by useState(null)

    val imagesStreckGubbe: List<String> = listOf("streckgubbe100.jpg","streckgubbe200.jpg","streckgubbe300.jpg",
        "streckgubbe400.jpg","streckgubbe500.jpg","streckgubbe600.jpg",
        "streckgubbe700.jpg", "streckgubbe800.jpg","streckgubbe900.jpg","streckgubbe1000.jpg",
        "streckgubbe1100.jpg","streckgubbe1200.jpg","streckgubbe1300.jpg","streckgubbe1500.jpg")

    val imagesStreck: List<String> = listOf("streck002.jpg","streck003.jpg","streck004.jpg")

    var personQuestions: List<Question> = listOf(
        Question(1,"Vad heter du?", "Namn"),
        Question(2,"I vilken ålder börjar du jobba?", "Ålder"),
    )
    var unionQuestions: List<Question> = listOf(
        Question(1,"Går du med i a-kassan?", "A-kassa"),
        Question(2,"Går du med i facket och får inkomstförsäkring?", "Inkomstförsäkring"),
        Question(3, "Tecknar du olycksfallsförsäkring?", "Olycksfallsförsäkring")
    )

    var unclickedUnionQuestions: List<Question>? by useState(null)

    var clickedUnionQuestions: List<Question>? by useState(null)

    val titles: List<String> = listOf(
        "Ditt arbetsliv börjar här... gör dig redo:")

    useEffectOnce {
        mainScope.launch {
            unclickedUnionQuestions = unionQuestions
        }
    }

    div {

        WelcomeQuestionList {
            title = titles[0]
            questions = personQuestions
        }

        div {

            QuestionList {
                questions = unionQuestions // and watchedVideos respectively
                selectedQuestion = currentQuestion
                onSelectQuestion = { question ->
                    currentQuestion = question
                }
            }

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
            css {
                display = Display.block
                position = Position.absolute
                top = 600.px
                left = 200.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (currentQuestion == unionQuestions[0]) {
                +unionQuestions[0].objectText
                +" ✔"
            }
            if (currentQuestion == unionQuestions[1]) {
                +unionQuestions[1].objectText
                +" ✔"
            }
            if (currentQuestion == unionQuestions[2]) {
                +unionQuestions[2].objectText
                +" ✔"
            }
        }
    }
}


