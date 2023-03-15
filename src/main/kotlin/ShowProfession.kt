import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.p

external interface ShowProfessionProps : Props {
    var actualProfession: Profession
    var actualAge: String
    var actualStyle: Style
    var actualStartTopPX: Int // props.actualStyle.topPX06.px
}

val ShowProfession = FC<ShowProfessionProps> { props ->

    val salary =props.actualProfession.salary * props.actualAge.toInt()
    var topPX: Int
    var leftPX: Int

    topPX = props.actualStartTopPX
    leftPX = props.actualStyle.leftPX01
    div {

        //Main title
        h1 {
            css {
                display = Display.block
                position = Position.absolute
                top = props.actualStyle.topPXTitle.px //25
                left = props.actualStyle.leftPXTitle.px
                color = NamedColor.green
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
                width = 1000.px
                height = 50.px
            }
            +props.actualProfession.professionText
            +"!"
        }

    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            width = 800.px
        }

        div {

            topPX += 120
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
                +"Första lön: "
                if (salary <= 0)
                    +"?"
                else
                    +salary.toString()
            }
        }

        div {
            topPX = props.actualStartTopPX
            leftPX += 350
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
                +"Lyckochans: "
                +"${props.actualProfession.randomLuck}%"
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
                +"Bonuschans: "
                +"${props.actualProfession.randomBonus}%"
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
                +"Pension jobb: "
                +"${(props.actualProfession.pension * 100).toInt().formatDecimalSeparator()}%"
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
                +"Varsel risk: "
                +"${props.actualProfession.randomUnemployed}%"
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
                +"Sjuk risk: "
                +"${props.actualProfession.randomSick}%"
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
                +"Avgångsvederlagchans: "
                +"${(props.actualProfession.randomSeverancePay).formatDecimalSeparator()}%"
            }
        }
    }
}
