
import kotlinx.coroutines.async
import react.*
import react.dom.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import emotion.react.css
import csstype.Position
import csstype.px
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.img

val mainScope = MainScope()

val App = FC<Props> {
    var currentQuestion: Question? by useState(null)

    val images1: List<String> = listOf("00100.jpg","00200.jpg","00300.jpg","00300.jpg","00500.jpg","00600.jpg",
        "00700.jpg", "00800.jpg","00900.jpg","01000.jpg","01100.jpg","01200.jpg","01300.jpg","01400.jpg")

    var questions1: List<Question> = listOf(
        Question(1,"Går du med i a-kassan?", false),
        Question(2,"Går du med i facket och får inkomstförsäkring?", false),
        Question(3, "Tecknar du olycksfallsförsäkring?", false)
    )

    var unclickedQuestions: List<Question> by useState(listOf(
        Question(2,"Går du med i facket och får inkomstförsäkring?", false),
        Question(3, "Tecknar du olycksfallsförsäkring?", false)
    ))

    var clickedQuestions: List<Question> by useState(listOf(
        Question(1,"Går du med i a-kassan?", true)
    ))

    val titlesH1: List<String> = listOf(
        "Ditt arbetsliv börjar här... gör dig redo:",
        "xxxx",
        "yyyy")

    val titlesH2: List<String> = listOf(
        "",
        "xxxx",
        "yyyy")

    useEffectOnce {
        mainScope.launch {
            unclickedQuestions = questions1
        }
    }

    div {

        ViewTitle{
            titleH1 = titlesH1[0]
            titleH2 = titlesH2[0]
        }

        div {
            QuestionList {
                questions = unclickedQuestions // and watchedVideos respectively
                selectedQuestion = currentQuestion
                onSelectQuestion = { question ->
                    currentQuestion = question
                }
            }

            showStreckGubbe1 {
                images = images1
                index = 0
            }
        }

        div {
            showStreck1 {
                images = images1
                index = 1
            }
        }

    }
}


