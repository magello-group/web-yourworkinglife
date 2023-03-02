
import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowSparkcykelProps : Props {
    var selectedTopPX: Int // 350
}

val ShowSparkcykel = FC<ShowSparkcykelProps> { props ->
    var topPX = 350

    if (props.selectedTopPX > 0) topPX = props.selectedTopPX

    p {

        val sparkCykel: AnimationName = keyframes {
            0.pct {
                backgroundImage = url("streckgubbe1200.jpg")
                marginLeft = 0.pc
                width = 6.pc
            }
            100.pct {
                backgroundImage = url("streckgubbe1300.jpg")
                marginLeft = 26.pc
                width = 10.pc
            }
        }

        css {
            animationDuration = 4.s
            animationName = sparkCykel
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

