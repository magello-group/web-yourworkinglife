import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p

external interface ShowEventsProps : Props {
    var actualEventList: List<String>
}

val ShowEvents = FC<ShowEventsProps> { props ->
    val procent: Int = 100 / props.actualEventList.size
    var actualPCT: Int = 0
    var actualMargin: Int = 0
    p {
        val events: AnimationName = keyframes {
            for (index in 0 until props.actualEventList.size) {
                if (index == props.actualEventList.size-1)
                    actualPCT = 100

                actualPCT.pct {
                    props.actualEventList[index-1]
                    marginTop = actualMargin.pc
                    //width = 8.pc
                }
                actualPCT += procent
                actualMargin +=30
            }
        }

        css {
            animationDuration = 3.s
            animationName = events
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = 100.px
            left = 1.px
            //width = 154.px
            //height = 190.px
            fontFamily = FontFamily.cursive
        }
    }
}

