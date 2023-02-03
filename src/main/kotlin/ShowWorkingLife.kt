import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key

external interface ShowWorkingLifeProps : Props {
    var actualProfession: Profession
    var actualPerson: Person
    var newTitle: Boolean
}

val ShowWorkingLife = FC<ShowWorkingLifeProps> { props ->
    var topPX = 580

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
            if (props.newTitle)
                +"Mitt i livet"
            else
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
            +"Pensionsålder: "
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
        }
    }
}