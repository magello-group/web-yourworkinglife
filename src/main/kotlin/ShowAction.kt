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

    val salary =props.actualProfession.salary * props.actualAge.toInt()

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
                top = 670.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Första lön: "
            if (salary <= 0)
                +"?"
            else
                +salary.toString()
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
            +"Lyckochans: "
            +"${props.actualProfession.randomLuck}%"
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
            +"Bonuschans: "
            +"${props.actualProfession.randomBonus}%"
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
            +"Pension: "
            +"${(props.actualProfession.pension * 100).toInt().formatDecimalSeparator()}%"
        }

    }

    div {

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 580.px
                left = 440.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Varsel risk: "
            +"${props.actualProfession.randomUnemployed}%"
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 610.px
                left = 440.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Sjuk risk: "
            +"${props.actualProfession.randomSick}%"
        }
    }
}
