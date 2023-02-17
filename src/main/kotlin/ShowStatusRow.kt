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
    var actualProfession: String
    var actualCats: String
    var actualDogs: String
    var actualHorses: String
    var actualBikes: String
    var actualCars: String
    var actualBabies: String
    var actualFriends: String
    var actualAlone: String
    var actualStrong: String
    var actualFishes: String
    var actualParties: String
    var actualMoney: String
}

val ShowStatusRow = FC<ShowStatusRowProps> { props ->

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
            +"Namn: "
            +props.actualName

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
            +"Ålder: "
            +props.actualAge
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
            +"Pension: "
            +"${props.actualPension}%"
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 700.px
                left = 10.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Yrke: "
            +props.actualProfession
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 730.px
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
            +"Bostadshyra: "
            if (props.actualHireAmount != "")
                +props.actualHireAmount
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
            +"Värde hus: "
            if (props.actualHouseAmount != "")
                +props.actualHouseAmount
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
            +"Lån: "
            if (props.actualLoanAmount != "")
                +props.actualLoanAmount
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
            +"Lönekonto: "
            if (props.actualSalaryAmount != "")
                +props.actualSalaryAmount
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
            +"Depå: "
            if (props.actualDepotAmount != "")
                +props.actualDepotAmount
        }


        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 730.px
                left = 220.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Pensionskonto: "
            if (props.actualPensionAmount != "")
                +props.actualPensionAmount
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
            +"Barn: "
            if (props.actualBabies != "")
                +props.actualBabies
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
            +"Katter: "
            if (props.actualCats != "")
                +props.actualCats
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
            +"Hundar: "
            if (props.actualDogs != "")
                +props.actualDogs
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 670.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Hästar: "
            if (props.actualHorses != "")
                +props.actualHorses
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 700.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Bilar: "
            if (props.actualCars != "")
                +props.actualCars
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 730.px
                left = 430.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Motorcyklar: "
            if (props.actualBikes != "")
                +props.actualBikes
        }

    }

    div {
        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 580.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Vänner: "
            if (props.actualFriends != "")
                +props.actualFriends
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 610.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Fisketurer: "
            if (props.actualFishes != "")
                +props.actualFishes
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 640.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Träning: "
            if (props.actualStrong != "")
                +props.actualStrong
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 670.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Vandringar: "
            if (props.actualAlone != "")
                +props.actualAlone
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 700.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Fester: "
            if (props.actualParties != "")
                +props.actualParties
        }

        p {
            css {
                display = Display.block
                position = Position.absolute
                top = 730.px
                left = 600.px
                color = NamedColor.black
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }
            +"Bankvalv: "
            if (props.actualMoney != "")
                +props.actualMoney
        }

    }

}