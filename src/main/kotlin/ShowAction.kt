import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowActionProps : Props {
    var actualProfession: Profession
    var actualAge: String
}

val ShowAction = FC<ShowActionProps> { props ->

    var topPX = 570
    val salary =props.actualProfession.salary * props.actualAge.toInt()
    val salaryFixedPercentage = props.actualProfession.salaryFixedPercentage * 100
    val salaryVariablePercentage = props.actualProfession.salaryVariablePercentage * 100

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
            +props.actualProfession.professionText
            +"!"
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 500.px
                color = NamedColor.green
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Fast lön: "
            +salaryFixedPercentage.toString()

            topPX += 30
        }
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 500.px
                color = NamedColor.green
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Rörlig lön: "
            +salaryVariablePercentage.toString()

            topPX += 30
        }
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 500.px
                color = NamedColor.green
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Lön: "
            if (salary <= 0)
                +"?"
            else
                +salary.toString()
        }
    }
}