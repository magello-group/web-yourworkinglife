import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowEventProps : Props {
    var actualEvent: Event
}

val ShowEvent = FC<ShowEventProps> { props ->

    var hobby = Hobby(props.actualEvent.objectType)
    hobby = hobby.getHobby(props.actualEvent.objectType)

    div {

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 25.px
                left = 10.px
                color = NamedColor.green
                fontSize = 26.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
                width = 1000.px
                height = 50.px
            }
            +hobby.description
            +"!"
        }
    }

    div {

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 730.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Kostnad: "
            +hobby.cost.toInt().formatDecimalSeparator()
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 760.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Lycka: "
            +hobby.point.formatDecimalSeparator()
        }


        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 800.px
                left = 10.px
                color = NamedColor.hotpink
                borderColor = NamedColor.white
                fontSize = 14.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"OBS: En sambo betalar halva hyran. Blir du deppig får du prata med en terapeut och kan välja ännu en lycka!"
        }
    }
}
