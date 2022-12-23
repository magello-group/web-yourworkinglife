import csstype.*
import emotion.css.keyframes
import emotion.react.css
import emotion.styled.styled
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.style

external interface ShowStreckGubbeProps : Props {
    //    var onWatchedButtonPressed: (Video) -> Unit
    var images: List<String>
}

val showStreckGubbe = FC<ShowStreckGubbeProps> { props ->
    p {
        val streckGubbe: AnimationName

        streckGubbe = keyframes {
            from {
                marginLeft = 0.pc
                width = 100.pc
            }
            to {
                marginLeft = 100.pc
                width = 300.pc
            }
        }
        css {
            animationDuration = 3.s
            animationName = streckGubbe
            backgroundImage = url("00100.jpg")
            display = Display.flex
            position = Position.absolute
            top = 300.px
            left = 1.px
            width = 154.px
            height = 221.px
        }
    }
}