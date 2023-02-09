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
    var actualSalary: String
    var actualSalaryAmount: String
    var actualDepotAmount: String
    var actualPensionAmount: String
    var actualHireAmount: String
    var actualHouseAmount: String
    var actualLoanAmount: String
}

val ShowInput = FC<ShowInputProps> { props ->

    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 580.px
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
                top = 610.px
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
                top = 640.px
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

    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 580.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualSalary == "") {
                +" "
            } else {
                +"Lön:"
                +": ${props.actualSalary} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 610.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualSalaryAmount == "") {
                +" "
            } else {
                +"Lönekonto:"
                +": ${props.actualSalaryAmount} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 640.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualDepotAmount == "") {
                +" "
            } else {
                +"Depå:"
                +": ${props.actualDepotAmount} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 670.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualPensionAmount == "") {
                +" "
            } else {
                +"pension:"
                +": ${props.actualPensionAmount} "
            }
        }
    }

    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 580.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualHireAmount == "") {
                +" "
            } else {
                +"Hyra:"
                +": ${props.actualHireAmount} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 610.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualHouseAmount == "") {
                +" "
            } else {
                +"Värde hus:"
                +": ${props.actualHouseAmount} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 640.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            if (props.actualLoanAmount == "") {
                +" "
            } else {
                +"Lån:"
                +": ${props.actualLoanAmount} "
            }
        }
    }
}