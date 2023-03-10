import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.p

external interface ShowEventProps : Props {
    var actualEvent: Event
    var actualStyle: Style
    var actualStartTopPX: Int // props.actualStyle.topPX07
}

val ShowEvent = FC<ShowEventProps> { props ->

    var hobby = Hobby(props.actualEvent.objectType)
    var topPX: Int
    var leftPX = props.actualStyle.leftPX01

    hobby = hobby.getHobby(props.actualEvent.objectType)

    div {

        h1 {
            css {
                display = Display.block
                position = Position.absolute
                top = props.actualStyle.topPXTitle.px //25
                left = props.actualStyle.leftPXTitle.px
                color = NamedColor.green
                fontSize = props.actualStyle.fontLarge.px
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
        topPX = props.actualStartTopPX
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = leftPX.px
                color = NamedColor.black
                fontSize = props.actualStyle.fontMedium.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Kostnad: "
            +hobby.cost.toInt().formatDecimalSeparator()
        }

        topPX += 40
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = leftPX.px
                color = NamedColor.black
                fontSize = props.actualStyle.fontMedium.px
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
                top = props.actualStyle.topPXOBSText00.px
                left = props.actualStyle.leftPXOBSText00.px
                color = NamedColor.hotpink
                borderColor = NamedColor.white
                fontSize = props.actualStyle.fontSmall.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"OBS: Blir du deppig får du prata med en terapeut och kan välja ännu en lycka!"
        }
    }
}
