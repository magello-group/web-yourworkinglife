import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.div



val App = FC<Props> {
    var currentPerson: Person? by useState(null)
    var currentAccount: Account? by useState(null)
    var currentProfession: Profession? by useState(null)
    var currentUnion: Union? by useState(null)

    val images1: List<String> = listOf("001.jpg","002.jpg","003.jpg","004.jpg","005.jpg","006.jpg","007.jpg","008.jpg",
        "009.jpg","010.jpg","011.jpg","012.jpg","013.jpg","014.jpg")

    div {
        css {
            display = Display.block
            //backgroundImage = url("background.jpg")
            position = Position.absolute
            top = 10.px
            left = 10.px

        }
        h1 {
            +"Ditt arbetsliv börjar här..."
        }
        div {
            getPrepared {
                akassa = false
                incomeInsurance = false
                healthInsurance = false
            }

            showStreckGubbe {
                images = images1
            }
        }
    }
}


