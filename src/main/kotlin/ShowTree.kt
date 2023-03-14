import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowTreeProps : Props {
    var selectedImage01: String //= "streck002.jpg"
    var selectedImage02: String //= "streck003.jpg"
    var selectedImage03: String //= "streck004.jpg"
    var selectedTopPX: Int //543
}

val ShowTree = FC<ShowTreeProps> { props ->

    p {

        val tree: AnimationName = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 0.pc
            }
            7.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 4.pc
            }
            14.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 8.pc
            }
            21.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 12.pc
            }
            28.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 16.pc
            }
            35.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 20.pc
            }
            42.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 24.pc
            }
            49.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 28.pc
            }
            56.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 32.pc
            }
            63.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 36.pc
            }
            70.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 40.pc
            }
            77.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = 0.pc
                width = 44.pc
            }
            84.pct {
                backgroundImage = url(props.selectedImage03)
                marginLeft = 0.pc
                width = 45.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage03)
                marginLeft = 0.pc
                width = 46.pc
            }
        }

        css {
            animationDuration = 4.s
            animationName = tree
            animationFillMode = AnimationFillMode.both
            backgroundImage = url("strecktree0020.png")
            display = Display.flex
            position = Position.absolute
            top = props.selectedTopPX.px
            left = 1.px
            width = 737.px
            height = 221.px
        }
    }
}