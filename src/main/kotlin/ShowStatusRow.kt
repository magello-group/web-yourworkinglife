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
            width = 1000.px
            color = NamedColor.black
            fontSize = props.actualStyle.fontMedium.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }

        topPX = startPX
        div {
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
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
                }
                +"Första lön: "
                +props.firstSalary
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
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

                    color = if (props.actualPerson.findLuck("cat"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("dog"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("horse"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("car"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("bike"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("boat"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("friend"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("fish"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("strong"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("walk"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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
                    color = if (props.actualPerson.findLuck("party"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
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

                    color = if (props.actualPerson.findLuck("love"))
                        NamedColor.hotpink
                    else
                        NamedColor.black
                }
                +"Sambos: "
                if (props.actualLoves != "")
                    +props.actualLoves
            }
        }
    }
}