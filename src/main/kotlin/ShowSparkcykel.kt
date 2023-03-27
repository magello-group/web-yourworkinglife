
import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowSparkcykelProps : Props {
    var selectedTopPX: Int // 350
    var marginLeftFrom: Int // = 0
    var marginLeftTo: Int   // = 26
    var selectedImage01: String // streckgubbe1200.jpg
    var selectedWidth01: Int // 13 eller 10
    var selectedImage02: String // streckgubbe1300.jpg
    var selectedWidth02: Int // 13 eller 10
}

val ShowSparkcykel = FC<ShowSparkcykelProps> { props ->
    val i = props.marginLeftTo/4
    val marginLeft20 = props.marginLeftFrom + i
    val marginLeft40 = marginLeft20 + i
    val marginLeft60 = marginLeft40 + i
    val marginLeft80 = marginLeft60 + i

    p {

        val sparkCykel: AnimationName = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = props.marginLeftFrom.pc
                width = props.selectedWidth01.pc
            }
            20.pct {
                backgroundImage = url(props.selectedImage02)
                marginLeft = marginLeft20.pc
                width = props.selectedWidth02.pc
            }
            40.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = marginLeft40.pc
                width = props.selectedWidth01.pc
            }
            60.pct {
                backgroundImage = url(props.selectedImage02)
                marginLeft = marginLeft60.pc
                width = props.selectedWidth02.pc
            }
            80.pct {
                backgroundImage = url(props.selectedImage01)
                marginLeft = marginLeft80.pc
                width = props.selectedWidth01.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage02)
                marginLeft = props.marginLeftTo.pc
                width = props.selectedWidth02.pc
            }
        }

        css {
            animationDuration = 2.s
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

