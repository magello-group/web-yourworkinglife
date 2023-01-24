import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowInputProps : Props {
    var actualInputQuestions: List<Question>
    var actualName: String
    var actualAge: String
    var actualPension: String
}

val ShowInput = FC<ShowInputProps> { props ->

    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 570.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
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

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 600.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
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
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 630.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualPension.isEmpty()) {
                +" "
            } else {
                +props.actualInputQuestions[2].objectText
                +": ${props.actualPension}% "
            }
        }
    }

}