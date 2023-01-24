import csstype.*
import emotion.css.merge
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.key
import kotlin.random.Random

external interface StartWorkingLifeProps : Props {
    var selectedProfession: Profession
    var selectedPerson: Person
}

val StartWorkingLife = FC<StartWorkingLifeProps> { props ->

    // Initiera arbetslivet

    val person = props.selectedPerson
    val age: Int = person.age
    val pensionAge = props.selectedProfession.pensionAge
    val parent = Parent(person.id)
    val employee = Employee(person.id)
    val accountPension = Account(person.id,"pension")
    val accountSalary = Account(person.id,"salary")
    val accountDepot = Account(person.id,"depot")
    val union = person.union

    val text: String = ""

    employee.firstSalary = props.selectedProfession.salary * person.age
    employee.salaryFixedPercentage = props.selectedProfession.salaryFixedPercentage * 100
    employee.salaryVariablePercentage = props.selectedProfession.salaryVariablePercentage * 100

    accountPension.amount = employee.firstSalary * person.pension
    accountSalary.amount = employee.firstSalary - (employee.firstSalary * person.pension)

    val randomLifeValues = List(pensionAge) { Random.nextInt(0, 100) }
    var randomEventValues = List(1) { Random.nextInt(0, 9) }
    var randomValues = List(1) { Random.nextInt(1, 12) }
    val event: Event = Event(0)
    var view: View = View(0)

    val randomEvents: List<Event> = event.getEvents()
    var eventList: List<Event> =  emptyList()
    var yearList: List<Int> =  emptyList()
    var messageList: List<String> =  emptyList()
    var amount: Double

    for (year in age..pensionAge) {
        //Mitt i livet

        /*
            Event(0,"Du får bonus i form av värdepapper", "depot", "depot"),
            Event(1,"Du blir utbränd", "burnedout","sick"),
            Event(2,"Du får en hjärtattack", "heartattack","sick"),
            Event(3,"Du får en golfboll i huvudet", "golf","sick"),
            Event(4,"Du blir skjuten", "shot","sick"),
            Event(5,"Du blir deprimerad", "depressed","sick"),
            Event(6,"Du blir lycklig", "luck","luck"),
            Event(7,"Du blir varslad", "unemployed","unemployed"),
            Event(8,"Du blir träffad av en Magellit", "maagellit","magellit"),
            Event(9,"Du får barn", "parent","parent"),
            Event(9,"Du VAB:ar", "VAB","VAB")
        */

        when (randomEvents[randomEventValues[0]].eventType) {
            "depot" -> {
                //Bonus
                if ((randomLifeValues[year - 1] > 50)) {
                    //Get value och financial instruments
                    randomValues = List(1) { Random.nextInt(0, 10000000) }
                    amount = randomValues[0].toDouble()
                    messageList = messageList.plus("Du fick bonus på $amount!")
                    accountDepot.amount += amount
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }
            }

            "sick" -> {
                //Sjuk
                if ((randomLifeValues[year - 1] <= 25 && year <= 30) ||
                    (randomLifeValues[year - 1] <= 40 && year > 30 && year <= 50) ||
                    (randomLifeValues[year - 1] <= 60 && year > 50)) {
                    when (randomEvents[randomEventValues[0]].objectType) {
                        "burnedout" -> {
                            if (!person.luck) person.sick = true
                        }
                        "heartattack" -> {
                            if (!person.luck) person.sick = true
                        }
                        "golf" -> {
                            person.sick = true
                        }
                        "shot" -> {
                            person.sick = true
                        }
                        "depressed" -> {
                            if (!person.luck) person.sick = true
                        }
                    }
                    if (person.sick) {
                        for (insurance in person.insurances) {
                            //Get chance to be approved by swedish authority
                            randomValues = List(1) { Random.nextInt(0, 100) }
                            when (insurance.insuranceType) {
                                "healthinsurance" -> {
                                    if (randomValues[0] < 15) {
                                        //Din sjukskrivning blir avslagen
                                        employee.sickSalary = 0.0
                                    } else {
                                        employee.sickSalary = insurance.getIncome(employee.currentSalary)
                                    }
                                }
                            }
                        }

                        //How many months are you sick?
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        employee.countSickMonth = randomValues[0]
                        eventList = eventList.plus(randomEvents[randomEventValues[0]])
                        yearList = yearList.plus(year)
                    }
                }
            }

            "luck" -> {
                //Lycklig
                if ((randomLifeValues[year - 1] < 25 && year <= 50) ||
                    (randomLifeValues[year - 1] < 60 && year > 50)) {
                    person.luck = true
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }
            }

            "unemployed" -> {
                //Varslad
                if ((randomLifeValues[year - 1] < 50 && year <= 25 && year >= 50) ||
                    (randomLifeValues[year - 1] < 10 && year > 25 && year < 50)) {

                    //Chans till avgångsvederlag
                    randomValues = List(1) { Random.nextInt(0, 100) }
                    if (randomValues[0] < 30 && employee.countWorkMonth >= 12) {
                        //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                        amount = employee.currentSalary * (employee.countWorkMonth / 12)
                        messageList = messageList.plus("Du fick avgångsvederlag på $amount!")
                        accountSalary.amount += amount
                    }
                    //Hur många månader är du arbetslös
                    randomValues = List(1) { Random.nextInt(1, pensionAge*12) }
                    union.countUnEmployeeMonth = randomValues[0]

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }

            }

            "magellit" -> {
                //Magellit
                if (randomLifeValues[year - 1] > 50) {
                    person.magellit = true
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }
            }

            "parent" -> {
                //Babies
                if ((randomLifeValues[year - 1] < 50 && year <= 35) ||
                    (randomLifeValues[year - 1] > 20 && year > 35 && year < 50)) {
                    parent.countBabies += 1
                    parent.familySalary = parent.getIncome(employee.currentSalary)
                    if (person.magellit) parent.familySalary += 5000.0

                    parent.countFamilyMonth += parent.familyMonth
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    //"Härligt att du bilda familj och fick barn! Du är föräldrarledig i $familyMonth månader. Föräldrarpenningen ligger på $familySalary kr. "
                }
            }

            "VAB" -> {
                if ((randomLifeValues[year - 1] > 75 && parent.countBabies > 0)) {
                    //VAB
                    if (parent.countBabies > 0) {
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        parent.countFamilyMonth += randomValues[1]
                        parent.familySalary = parent.getIncome(employee.currentSalary)
                        eventList = eventList.plus(randomEvents[randomEventValues[0]])
                        yearList = yearList.plus(year)
                        //"VAB" ->  text = "Du vabbar $countFamilyMonth månader totalt."
                    }
                }
            }
        }
        person.age += 1
        employee.currentSalary = props.selectedProfession.salary * person.age

        employee.countWorkMonth += 12 - parent.countFamilyMonth - employee.countSickMonth
        accountSalary.amount += employee.sickSalary * employee.countSickMonth
        accountSalary.amount += parent.familySalary * parent.countFamilyMonth
        accountSalary.amount += (employee.currentSalary - (employee.currentSalary * person.pension)) * employee.countWorkMonth
        accountPension.amount += (employee.currentSalary * person.pension) * employee.countWorkMonth

        randomEventValues = List(1) { Random.nextInt(0, 9) }
        employee.countSickMonth = 0
        employee.sickSalary = 0.0
        parent.countFamilyMonth = 0
        parent.familySalary = 0.0
    }

    ShowAction {
        actualProfession = props.selectedProfession
        actualAge = person.age.toString()
    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 90.px
            left = 10.px
            fontFamily = FontFamily.cursive
        }
        p {
            for (event in eventList) {
                +event.eventText
            }
        }

        table {
            css {
                width = 700.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(0.px, LineStyle.solid, NamedColor.white)
                margin = Auto.auto

            }

            tbody {
                css {
                    color = NamedColor.black
                    backgroundColor = NamedColor.white
                    textAlign = TextAlign.start
                }

                tr {
                    css {
                        fontSize = 18.px
                        cursor = Cursor.pointer
                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                        hover {
                            backgroundColor = NamedColor.lightgray
                        }
                    }

                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                                columnSpan = ColumnSpan.all
                                wordWrap = WordWrap.breakWord
                            }
                            +text
                        }
                    }
                }

                tr {
                    css {
                        fontSize = 18.px
                        cursor = Cursor.pointer
                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                        hover {
                            backgroundColor = NamedColor.lightgray
                        }
                    }

                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +"Arbetsmånader: "
                        }
                    }
                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +employee.countWorkMonth.toString()
                        }
                    }
                }

                tr {
                    css {
                        fontSize = 18.px
                        cursor = Cursor.pointer
                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                        hover {
                            backgroundColor = NamedColor.lightgray
                        }
                    }
                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +" Lön: "
                        }
                    }
                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +employee.firstSalary.toString()
                        }
                    }
                }

                tr {
                    css {
                        fontSize = 18.px
                        cursor = Cursor.pointer
                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                        hover {
                            backgroundColor = NamedColor.lightgray
                        }
                    }
                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +" Depå: "
                        }
                    }

                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +accountDepot.amount.toString()
                        }
                    }
                }
                tr {
                    css {
                        fontSize = 18.px
                        cursor = Cursor.pointer
                        borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                        hover {
                            backgroundColor = NamedColor.lightgray
                        }
                    }

                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +" Konto: "
                        }
                    }

                    td {
                        p {
                            css {
                                padding = Padding(0.px, 0.px)
                                height = 10.px
                            }
                            +accountSalary.amount.toString()
                        }
                    }
                }
            }
        }
    }
}


