import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowStatusRowProps : Props {
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
    var actualLoanMonthPayment: String
    var actualInterestMonthPayment: String
    var actualProfession: String
    var actualCats: String
    var actualDogs: String
    var actualHorses: String
    var actualBikes: String
    var actualCars: String
    var actualBabies: String
    var actualFriends: String
    var actualWalking: String
    var actualStrong: String
    var actualFishing: String
    var actualParties: String
    var actualBoats: String
    var actualLoves: String
    var firstSalary: String
    var actualPerson: Person
    var actualStyle: Style
}

val ShowStatusRow = FC<ShowStatusRowProps> { props ->
    var topPX: Int
    var leftPX = props.actualStyle.leftPX01
    val startPX = props.actualStyle.topPXProfessionStatus
    val padding = 40
    val leftPadding = 450

    div {
        css {
            display = Display.block
            position = Position.absolute
            width = 1000.px
        }

        topPX = startPX
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
                +"Namn: "
                +props.actualName

            }

            topPX += padding
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
                +"Ålder: "
                +props.actualAge
            }

            topPX += padding
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
                +"Pension: "
                +"${props.actualPension}%"
            }

            topPX += padding
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
                +"${props.firstSalary}"
            }

            topPX += padding
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
                +"Yrke: "
                +props.actualProfession
            }

            topPX += padding
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
                +"Aktuell lön: "
                if (props.actualSalary != "")
                    +props.actualSalary
            }

            topPX += padding
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
                +"Pensionskonto: "
                if (props.actualPensionAmount != "")
                    +props.actualPensionAmount
            }

            topPX += padding
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
                +"Lönekonto: "
                if (props.actualSalaryAmount != "")
                    +props.actualSalaryAmount
            }

            topPX += padding
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
                +"Depå: "
                if (props.actualDepotAmount != "")
                    +props.actualDepotAmount
            }

            topPX += padding
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
                +"Bostadshyra: "
                if (props.actualHireAmount != "")
                    +props.actualHireAmount
            }

            topPX += padding
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
                +"Värde hus: "
                if (props.actualHouseAmount != "")
                    +props.actualHouseAmount
            }

            topPX += padding
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
                +"Lån: "
                if (props.actualLoanAmount != "")
                    +props.actualLoanAmount
            }

            topPX += padding
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
                +"Lån avbetalning: "
                if (props.actualLoanMonthPayment != "")
                    +props.actualLoanMonthPayment
            }

            topPX += padding
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
                +"Räntebelopp: "
                if (props.actualInterestMonthPayment != "")
                    +props.actualInterestMonthPayment
            }
        }

        div {
            topPX = startPX
            leftPX += leftPadding
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
                +"Barn: "
                if (props.actualBabies != "")
                    +props.actualBabies
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("cat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Katter: "
                if (props.actualCats != "")
                    +props.actualCats
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("dog"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Hundar: "
                if (props.actualDogs != "")
                    +props.actualDogs
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("horse"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Hästar: "
                if (props.actualHorses != "")
                    +props.actualHorses
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("car"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Bilar: "
                if (props.actualCars != "")
                    +props.actualCars
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("bike"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Motorcyklar: "
                if (props.actualBikes != "")
                    +props.actualBikes
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("boat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Segelbåtar: "
                if (props.actualBoats != "")
                    +props.actualBoats
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("friend"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Vänner: "
                if (props.actualFriends != "")
                    +props.actualFriends
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("fish"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Fisketurer: "
                if (props.actualFishing != "")
                    +props.actualFishing
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("strong"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Träning: "
                if (props.actualStrong != "")
                    +props.actualStrong
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("walk"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Vandringar: "
                if (props.actualWalking != "")
                    +props.actualWalking
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
                    if (props.actualPerson.findLuck("party"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black
                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Fester: "
                if (props.actualParties != "")
                    +props.actualParties
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px

                    if (props.actualPerson.findLuck("love"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = props.actualStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Sambos: "
                if (props.actualLoves != "")
                    +props.actualLoves
            }
        }
    }
}