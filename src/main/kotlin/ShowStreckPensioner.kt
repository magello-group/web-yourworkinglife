
import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckPensionerProps : Props {
    var selectedTopPX: Int
}

val ShowStreckPensioner = FC<ShowStreckPensionerProps> { props ->
    p {

        val streckPensioner = keyframes {
            0.pct {
                backgroundImage = url("streckpensioner100.jpg")
                marginLeft = 0.pc
                width = 8.pc
            }
            7.pct {
                backgroundImage = url("streckpensioner200.jpg")
                marginLeft = 1.5.pc
                width = 8.pc
            }
            14.pct {
                backgroundImage = url("streckpensioner300.jpg")
                marginLeft = 3.pc
                width = 8.pc
            }
            21.pct {
                backgroundImage = url("streckpensioner100.jpg")
                marginLeft = 4.5.pc
                width = 8.pc
            }
            28.pct {
                backgroundImage = url("streckpensioner200.jpg")
                marginLeft = 6.pc
                width = 8.pc
            }
            35.pct {
                backgroundImage = url("streckpensioner300.jpg")
                marginLeft = 7.5.pc
                width = 8.pc
            }
            42.pct {
                backgroundImage = url("streckpensioner100.jpg")
                marginLeft = 9.pc
                width = 8.pc
            }
            49.pct {
                backgroundImage = url("streckpensioner200.jpg")
                marginLeft = 10.5.pc
                width = 8.pc
            }
            56.pct {
                backgroundImage = url("streckpensioner300.jpg")
                marginLeft = 12.pc
                width = 8.pc
            }
            63.pct {
                backgroundImage = url("streckpensioner400.jpg")
                marginLeft = 13.5.pc
                width = 8.pc
            }
            70.pct {
                backgroundImage = url("streckpensioner400.jpg")
                marginLeft = 15.pc
                width = 8.pc
            }
            77.pct {
                backgroundImage = url("streckpensioner400.jpg")
                marginLeft = 17.5.pc
                width = 8.pc
            }
            84.pct {
                backgroundImage = url("streckpensioner400.jpg")
                marginLeft = 24.pc
                width = 8.pc
            }
            100.pct {
                backgroundImage = url("streckpensioner400.jpg")
                marginLeft = 35.pc
                width = 8.pc
            }
        }

        css {
            animationDuration = 3.s
            animationName = streckPensioner
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = props.selectedTopPX.px
            left = 1.px
            width = 154.px
            height = 190.px
        }
    }
}

