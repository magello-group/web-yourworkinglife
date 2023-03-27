import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowInputProps : Props {
    var actualInputQuestions: List<Question>
    var actualName: String
    var actualAge: String
    var actualPension: String
    var actualStyle: Style
    var actualStartTopPX: Int // props.actualStyle.topPX02
}

val ShowInput = FC<ShowInputProps> { props ->
    val leftPX = props.actualStyle.leftPX01

    var topPX: Int = props.actualStartTopPX
    div {
        css {
            display = Display.block
            position = Position.absolute
            width = 1000.px
        }
        div {
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
                if (props.actualName.isEmpty()) {
                    +" "
                } else {
                    +props.actualInputQuestions[0].objectText
                    +": ${props.actualName} "
                }
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
                if (props.actualAge.isEmpty()) {
                    +" "
                } else {
                    +props.actualInputQuestions[1].objectText
                    +": ${props.actualAge} "
                }
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
                if (props.actualPension.isEmpty()) {
                    +" "
                } else {
                    +props.actualInputQuestions[2].objectText
                    +": ${props.actualPension}%"
                }
            }
        }
    }
}