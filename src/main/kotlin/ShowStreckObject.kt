import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowStreckObjectProps : Props {
    var selectedImage: String
    var selectTop: Int
    var selectMarginFrom: Int
    var selectMarginTo: Int
    var selectSeconds: Int
    var selectWidth: Int //5
}

val ShowStreckObject = FC<ShowStreckObjectProps> { props ->

    p {

        val streckImage = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = props.selectMarginFrom.pc
                width = props.selectWidth.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = props.selectMarginTo.pc
                width = props.selectWidth.pc
            }
        }

        css {
            animationDuration = props.selectSeconds.s
            animationName = streckImage
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = props.selectTop.px
            left = 1.px
            width = 80.px
            height = 32.px
        }
    }
}