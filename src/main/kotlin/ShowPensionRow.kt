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
    var actualStyle: Style
}

val ShowPensionRow = FC<ShowPensionRowProps> { props ->
    var topPX: Int
    val startPX: Int = props.actualStyle.topPXPensionerStatus
    val padding = 40
    var leftPX = props.actualStyle.leftPX01
    val leftPadding = 450


    div {
        css {
            display = Display.block
            position = Position.absolute
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
                +"Sista lön: "
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

            if (props.actualNoAkassaAmount != "0") {
                topPX += padding
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"med A-kassa: "
                    +props.actualNoAkassaAmount
                }
            }

            if (props.actualDepotAmount != "0") {
                topPX += padding
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"Depå: "
                    +props.actualDepotAmount
                }
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
                +"Varav tjänstepension: "
                if (props.actualWorkPensionAmount != "")
                    +props.actualWorkPensionAmount
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
                }
                +"Varav inkomstpension: "
                if (props.actualTaxPensionAmount != "")
                    +props.actualTaxPensionAmount
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
                }
                +"Betald skatt: "
                if (props.actualTaxAmount != "")
                    +props.actualTaxAmount
            }

            topPX += padding
            p {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = topPX.px
                    left = leftPX.px
                }
                +"Arbetat år: "
                if (props.actualWorkYear != "")
                    +props.actualWorkYear
            }

            if (props.actualSickMonth != "0") {
                topPX += padding
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"Sjukmånader: "
                    +props.actualSickMonth
                }
            }

            if (props.actualParentMonth != "0") {
                topPX += padding
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"Föräldrarledig månader: "
                    +props.actualParentMonth
                }
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
                +"Bostadshyra: "
                if (props.actualHireAmount != "")
                    +props.actualHireAmount
            }

            if (props.actualHouseAmount != "0") {
                topPX += padding
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"Värde hus: "
                    +props.actualHouseAmount
                }
            }

            topPX += padding
            if (props.actualLoanAmount != "" && props.actualLoanAmount != "0") {
                p {
                    css {
                        display = Display.block
                        position = Position.absolute
                        top = topPX.px
                        left = leftPX.px
                    }
                    +"Lån: "
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
                    +props.actualInterestMonthPayment
                }
            }

            topPX += padding
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

            if (props.actualCats != "0") {
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
                    }
                    +"Katter: "
                    +props.actualCats
                }
            }

            if (props.actualDogs != "0") {
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
                    }
                    +"Hundar: "
                    +props.actualDogs
                }
            }

            if (props.actualHorses != "0") {
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
                    }
                    +"Hästar: "
                    +props.actualHorses
                }
            }

            if (props.actualCars != "0") {
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
                    }
                    +"Bilar: "
                    +props.actualCars
                }
            }

            if (props.actualBikes != "0") {
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
                    }
                    +"Motorcyklar: "
                    +props.actualBikes
                }
            }

            if (props.actualBoats != "0") {
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
                    }
                    +"Segelbåtar: "
                    +props.actualBoats
                }
            }

            if (props.actualDeadCats != "0") {
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
                    }
                    +"Avlidna katter: "
                    +props.actualDeadCats
                }
            }

            if (props.actualDeadDogs != "0") {
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
                    }
                    +"Avlidna hundar: "
                    +props.actualDeadDogs
                }
            }

            if (props.actualDeadHorses != "0") {
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
                    +"Avlidna hästar: "
                    +props.actualDeadHorses
                }
            }

            if (props.actualDeadCars != "0") {
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
                    +"Skrotade bilar: "
                    +props.actualDeadCars
                }
            }

            if (props.actualBikes != "0") {
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
                    +"Skrotade motorcyklar: "
                    +props.actualDeadBikes
                }
            }

            if (props.actualBoats != "0") {
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
                    +"Sjunkna segelbåtar: "
                    +props.actualDeadBoats
                }
            }

            if (props.actualFriends != "0") {
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

                    +props.actualFriends
                }
            }

            if (props.actualFishing != "0") {
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

                    +props.actualFishing
                }
            }

            if (props.actualStrong != "0") {
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
                    +props.actualStrong
                }
            }

            if (props.actualWalking != "0") {
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
                    +props.actualWalking
                }
            }

            if (props.actualParties != "0") {
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

                    +props.actualParties
                }
            }

            if (props.actualLoves != "0") {
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
                    +props.actualLoves
                }
            }
        }
    }
}