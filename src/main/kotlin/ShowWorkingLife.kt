import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key

external interface ShowWorkingLifeProps : Props {
    var actualProfession: Profession
    var actualPerson: Person
}

val ShowWorkingLife = FC<ShowWorkingLifeProps> { props ->

    var topPX = 570

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
            +"Ålder: "
            +props.actualPerson.age.toString()
        }

        topPX += 30
        for (employee in props.actualPerson.employees) {
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
                +"Titel: "
                +employee.title
            }
            topPX += 30
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
                +"Arbetsmånader: "
                +employee.countWorkMonth.toString()
            }
            topPX += 30
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
                +"Första lönen: "
                +employee.firstSalary.toString()
            }
            topPX += 30
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
                +"Aktuell lön: "
                +employee.currentSalary.toString()
            }
            topPX += 30
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
                +"Fast lön %: "
                +employee.salaryFixedPercentage.toString()
            }
            topPX += 30
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
                +"Rörlig lön %: "
                +employee.salaryVariablePercentage.toString()
            }
            topPX += 40
        }

        for (account in props.actualPerson.accounts) {
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
                +"Kontotyp: "
                +account.accountType

                +" ,Belopp: "
                +account.amount.toString()
            }
            topPX += 30
        }
    }
}