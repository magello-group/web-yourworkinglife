import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p

external interface ShowStreckPilotProps : Props {
}

val ShowStreckPilot = FC<ShowStreckPilotProps> { props ->
    p {

        val streckPilot: AnimationName = keyframes {
            0.pct {
                backgroundImage = url("streckpilot1300.jpg")
                marginLeft = 0.pc
                width = 10.pc
            }
            100.pct {
                backgroundImage = url("streckpilot1300.jpg")
                marginLeft = 26.pc
                width = 10.pc
            }
        }

        css {
            animationDuration = 3.s
            animationName = streckPilot
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = 350.px
            left = 1.px
            width = 154.px
            height = 190.px
        }
    }
}