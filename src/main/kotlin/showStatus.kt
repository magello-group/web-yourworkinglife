import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowStatusProps : Props {
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
    var actualProfession: String
}

val ShowStatus = FC<ShowStatusProps> { props ->

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
            +props.actualInputQuestions[0].objectText
            if (props.actualName.isNotBlank()) {
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
            +props.actualInputQuestions[1].objectText
            if (props.actualAge.isNotBlank()) {
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
            +props.actualInputQuestions[2].objectText
            if (props.actualPension.isNotBlank()) {
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
            +"Yrke:"
            if (props.actualProfession.isNotBlank()) {
                +": ${props.actualProfession} "
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
            +"Lön:"
            if (props.actualSalary.isNotBlank()) {
                +": ${props.actualSalary} "
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
            +"Lönekonto:"
            if (props.actualSalaryAmount.isNotBlank()) {
                +": ${props.actualSalaryAmount} "
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
            +"Depå:"
            if (props.actualDepotAmount.isNotBlank()) {
                +": ${props.actualDepotAmount} "
            }
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 700.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Pensionskonto:"
            if (props.actualPensionAmount.isNotBlank()) {
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
            +"Hyra:"
            if (props.actualHireAmount.isNotBlank()) {
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
            +"Värde hus:"
            if (props.actualHouseAmount.isNotBlank()) {
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
            +"Lån:"
            if (props.actualLoanAmount.isNotBlank()) {
                +": ${props.actualLoanAmount} "
            }
        }
    }
}