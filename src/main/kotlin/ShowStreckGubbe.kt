import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckGubbeProps : Props {
    var selectedTopPX: Int // 350
    var marginLeftFrom: Int // = 0
    var marginLeftTo: Int   // = 26
}

val ShowStreckGubbe = FC<ShowStreckGubbeProps> { props ->
    p {

        val streckGubbe = keyframes {
            0.pct {
                backgroundImage = url("streckgubbe100.jpg")
                marginLeft = props.marginLeftFrom.pc
                width = 8.pc
            }
            7.pct {
                backgroundImage = url("streckgubbe200.jpg")
                marginLeft = 1.5.pc
                width = 9.pc
            }
            14.pct {
                backgroundImage = url("streckgubbe300.jpg")
                marginLeft = 3.pc
                width = 9.5.pc
            }
            21.pct {
                backgroundImage = url("streckgubbe400.jpg")
                marginLeft = 4.5.pc
                width = 8.pc
            }
            28.pct {
                backgroundImage = url("streckgubbe500.jpg")
                marginLeft = 6.pc
                width = 7.pc
            }
            35.pct {
                backgroundImage = url("streckgubbe600.jpg")
                marginLeft = 7.5.pc
                width = 7.pc
            }
            42.pct {
                backgroundImage = url("streckgubbe700.jpg")
                marginLeft = 9.pc
                width = 8.pc
            }
            49.pct {
                backgroundImage = url("streckgubbe800.jpg")
                marginLeft = 10.5.pc
                width = 10.pc
            }
            56.pct {
                backgroundImage = url("streckgubbe900.jpg")
                marginLeft = 12.pc
                width = 8.pc
            }
            63.pct {
                backgroundImage = url("streckgubbe1000.jpg")
                marginLeft = 13.5.pc
                width = 13.pc
            }
            70.pct {
                backgroundImage = url("streckgubbe1100.jpg")
                marginLeft = 15.pc
                width = 9.pc
            }
            77.pct {
                backgroundImage = url("streckgubbe1200.jpg")
                marginLeft = 17.5.pc
                width = 6.pc
            }
            84.pct {
                backgroundImage = url("streckgubbe1300.jpg")
                marginLeft = 24.pc
                width = 10.pc
            }
            100.pct {
                backgroundImage = url("streckgubbe1500.jpg")
                marginLeft = props.marginLeftTo.pc
                width = 10.pc
            }
        }

        css {
            animationDuration = 3.s
            animationName = streckGubbe
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

