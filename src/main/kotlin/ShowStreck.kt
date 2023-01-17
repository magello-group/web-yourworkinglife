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

        val streck: AnimationName = keyframes {
            0.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 7.pct
            }
            7.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 14.pct
            }
            14.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 21.pct
            }
            21.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 25.pct
            }
            28.pct {
                backgroundImage = url(props.images[0])
                marginLeft = 0.pc
                width = 28.pct
            }
            35.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 35.pct
            }
            42.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 42.pct
            }
            49.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 49.pct
            }
            56.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 56.pct
            }
            63.pct {
                backgroundImage = url(props.images[1])
                marginLeft = 0.pc
                width = 63.pct
            }
            70.pct {
                backgroundImage = url(props.images[2])
                marginLeft = 0.pc
                width = 70.pct
            }
            77.pct {
                backgroundImage = url(props.images[2])
                marginLeft = 0.pc
                width = 77.pct
            }
            84.pct {
                backgroundImage = url(props.images[2])
                marginLeft = 0.pc
                width = 84.pct
            }
            100.pct {
                backgroundImage = url(props.images[2])
                marginLeft = 0.pc
                width = 100.pct
            }
        }

        css {
            animationDuration = 3.s
            animationName = streck
            animationFillMode = AnimationFillMode.both
            backgroundImage = url("streck002.jpg")
            display = Display.flex
            position = Position.absolute
            top = 493.px
            left = 1.px
            width = 50.px
            height = 30.px
        }
    }
}