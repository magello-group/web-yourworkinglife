import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreck1Props : Props {
    //    var onWatchedButtonPressed: (Video) -> Unit
    var images: List<String>
    var index: Int
    var widthImage: Int
    var widthFrame: Int
}

val showStreck1 = FC<ShowStreck1Props> { props ->
    p {
        val streck: AnimationName

        streck = keyframes {
            from {
                marginLeft = 25.pc
                width = 35.pc
            }
            to {
                marginLeft = 0.pc
                width = 35.pc
            }
        }

        css {
            animationDuration = 1.s
            animationName = streck
            animationFillMode = AnimationFillMode.both
            backgroundImage = url("streck002.jpg")
            display = Display.flex
            position = Position.absolute
            top = 525.px
            left = 1.px
            width = 50.px
            height = 30.px
        }
    }
}