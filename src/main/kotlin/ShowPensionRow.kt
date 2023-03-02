import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowPensionRowProps : Props {
    var actualName: String
    var actualAge: String
    var actualPension: String
    var actualSalary: String
    var actualWorkYear: String
    var actualSickMonth: String
    var actualParentMonth: String
    var actualSalaryAmount: String
    var actualNoAkassaAmount: String
    var actualDepotAmount: String
    var actualPensionAmount: String
    var actualWorkPensionAmount: String
    var actualTaxPensionAmount: String
    var actualTaxAmount: String
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
    var actualDeadCats: String
    var actualDeadDogs: String
    var actualDeadHorses: String
    var actualDeadBikes: String
    var actualDeadCars: String
    var actualDeadBoats: String
    var firstSalary: String
    var actualPerson: Person
}

val ShowPensionRow = FC<ShowPensionRowProps> { props ->
    var topPX: Int

    topPX = 580
    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Namn: "
            +props.actualName

        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Ålder: "
            +props.actualAge
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Pension: "
            +"${props.actualPension}%"
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Första lön: "
            +props.firstSalary
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Yrke: "
            +props.actualProfession
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Lön: "
            if (props.actualSalary != "")
                +props.actualSalary
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Pensionskonto: "
            if (props.actualPensionAmount != "")
                +props.actualPensionAmount
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Varav tjänstepension: "
            if (props.actualWorkPensionAmount != "")
                +props.actualWorkPensionAmount
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Varav inkomstpension: "
            if (props.actualTaxPensionAmount != "")
                +props.actualTaxPensionAmount
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Betald skatt: "
            if (props.actualTaxAmount != "")
                +props.actualTaxAmount
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Arbetat år: "
            if (props.actualWorkYear != "")
                +props.actualWorkYear
        }

        if (props.actualSickMonth != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 10.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Sjukmånader: "
                +props.actualSickMonth
            }
        }

        if (props.actualParentMonth != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 10.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Föräldrarledig månader: "
                +props.actualParentMonth
            }
        }
    }

    div {

        topPX = 580
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 230.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Lönekonto: "
            if (props.actualSalaryAmount != "")
                +props.actualSalaryAmount
        }

        if (props.actualNoAkassaAmount != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"utan A-kassa: "
                +props.actualNoAkassaAmount
            }
        }

        if (props.actualDepotAmount != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Depå: "
                +props.actualDepotAmount
            }
        }

        topPX += 30
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 230.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Bostadshyra: "
            if (props.actualHireAmount != "")
                +props.actualHireAmount
        }

        if (props.actualHouseAmount != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Värde hus: "
                +props.actualHouseAmount
            }
        }

        topPX += 30
        if (props.actualLoanAmount != "" && props.actualLoanAmount != "0") {
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Lån: "
                +props.actualLoanAmount
            }

            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Lån avbetalning: "
                +props.actualLoanMonthPayment
            }

            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 230.px
                    color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Räntebelopp: "
                +props.actualInterestMonthPayment
            }
        }
    }

    div {
        topPX = 580
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = topPX.px
                left = 470.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Barn: "
            if (props.actualBabies != "")
                +props.actualBabies
        }

        if (props.actualCats != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("cat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Katter: "
                +props.actualCats
            }
        }

        if (props.actualDogs != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("dog"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Hundar: "
                +props.actualDogs
            }
        }

        if (props.actualHorses != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("horse"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Hästar: "
                +props.actualHorses
            }
        }

        if (props.actualCars != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("car"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Bilar: "
                +props.actualCars
            }
        }

        if (props.actualBikes != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("bike"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Motorcyklar: "
                +props.actualBikes
            }
        }

        if (props.actualBoats != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("boat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Segelbåtar: "
                +props.actualBoats
            }
        }

        if (props.actualDeadCats != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("cat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Avlidna katter: "
                +props.actualDeadCats
            }
        }

        if (props.actualDeadDogs != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("dog"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Avlidna hundar: "
                +props.actualDeadDogs
            }
        }

        if (props.actualDeadHorses != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("horse"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Avlidna hästar: "
                +props.actualDeadHorses
            }
        }

        if (props.actualDeadCars != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("car"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Skrotade bilar: "
                +props.actualDeadCars
            }
        }

        if (props.actualBikes != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("bike"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Skrotade motorcyklar: "
                +props.actualDeadBikes
            }
        }

        if (props.actualBoats != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 470.px

                    if (props.actualPerson.findLuck("boat"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Sjunkna segelbåtar: "
                +props.actualDeadBoats
            }
        }
    }

    div {
        topPX = 580
        if (props.actualFriends != "0") {
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px

                    if (props.actualPerson.findLuck("friend"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Vänner: "

                +props.actualFriends
            }
        }

        if (props.actualFishing != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px

                    if (props.actualPerson.findLuck("fish"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Fisketurer: "

                +props.actualFishing
            }
        }

        if (props.actualStrong != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px

                    if (props.actualPerson.findLuck("strong"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Träning: "
                +props.actualStrong
            }
        }

        if (props.actualWalking != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px

                    if (props.actualPerson.findLuck("walk"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Vandringar: "
                +props.actualWalking
            }
        }

        if (props.actualParties != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px
                    if (props.actualPerson.findLuck("party"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Fester: "

                +props.actualParties
            }
        }

        if (props.actualLoves != "0") {
            topPX += 30
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = 640.px

                    if (props.actualPerson.findLuck("love"))
                        color = NamedColor.hotpink
                    else
                        color = NamedColor.black

                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }
                +"Sambos: "
                +props.actualLoves
            }
        }
    }
}