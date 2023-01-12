import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckProps : Props {
    //    var onWatchedButtonPressed: (Video) -> Unit
    var images: List<String>
}

val showStreck = FC<ShowStreckProps> { props ->
    p {
        val streck: AnimationName

        streck = keyframes {
            30.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 35.pc
            }
            60.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 35.pc
            }
            100.pct {
                backgroundImage = url(props.images[2])
                marginLeft = 0.pc
                width = 35.pc
            }
        }

        css {
            animationDuration = 3.s
            animationName = streck
            animationFillMode = AnimationFillMode.both
            backgroundImage = url("streck002.jpg")
            display = Display.flex
            position = Position.absolute
            top = 533.px
            left = 1.px
            width = 50.px
            height = 30.px
        }
    }
}