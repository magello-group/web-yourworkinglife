import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckImageProps : Props {
    var selectedImage: String
    var selectTop: Int //350
}

val ShowStreckImage = FC<ShowStreckImageProps> { props ->

    p {

        val streckImage = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = 0.pc
                width = 10.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = 35.pc
                width = 10.pc
            }
        }

        css {
            animationDuration = 4.s
            animationName = streckImage
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = props.selectTop.px
            left = 1.px
            width = 154.px
            height = 190.px
        }
    }
}