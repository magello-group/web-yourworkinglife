import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckImageProps : Props {
    var selectedImage: String
}

val ShowStreckImage = FC<ShowStreckImageProps> { props ->
    p {

        val streckImage: AnimationName = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = 0.pc
                width = 10.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = 26.pc
                width = 10.pc
            }
        }

        css {
            animationDuration = 3.s
            animationName = streckImage
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