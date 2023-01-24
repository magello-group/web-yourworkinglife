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
    var profession = props.selectedProfession
    var age: Int = person.age
    val pensionAge = profession.pensionAge
    val parent = Parent(person.id)
    val employee = Employee(person.id)
    val accountPension = Account(person.id,"pension")
    val accountSalary = Account(person.id,"salary")
    val accountDepot = Account(person.id,"depot")
    val union = person.union

    val text: String = ""

    person.professions = person.professions.plus(profession)
    employee.firstSalary = profession.salary * person.age
    employee.salaryFixedPercentage = profession.salaryFixedPercentage * 100
    employee.salaryVariablePercentage = profession.salaryVariablePercentage * 100

    accountPension.amount = employee.firstSalary * person.pension
    accountSalary.amount = employee.firstSalary - (employee.firstSalary * person.pension)

    val randomLifeValues = List(pensionAge) { Random.nextInt(0, 100) }
    var randomEventValues = List(1) { Random.nextInt(0, 9) }
    var randomValues = List(1) { Random.nextInt(1, 12) }
    val event = Event(0)

    val randomEvents: List<Event> = event.getEvents()
    var eventList: List<Event> =  emptyList()
    var yearList: List<Int> =  emptyList()
    var messageList: List<String> =  emptyList()
    var amount: Double

    for (year in person.age..profession.pensionAge) {
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
            Event(10,"Du VAB:ar", "VAB","VAB")
        */

        randomEventValues = List(1) { Random.nextInt(0, 9) }

        when (randomEvents[randomEventValues[0]].eventType) {
            "depot" -> {
                //Bonus
                if (randomLifeValues[year - 1] < 5) {
                    //Get value och financial instruments
                    randomValues = List(1) { Random.nextInt(0, 10000000) }
                    amount = randomValues[0].toDouble()
                    messageList = messageList.plus("Du fick bonus på $amount! när du är $age år")
                    accountDepot.amount += amount

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }
            }

            "sick" -> {
                //Sjuk
                employee.countSickMonth = 0
                employee.sickSalary = 0.0
                person.sick = false
                if (randomLifeValues[year - 1] < 10 ) {
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
                        messageList = messageList.plus(randomEvents[randomEventValues[0]].eventText)
                        for (insurance in person.insurances) {
                            when (insurance.insuranceType) {
                                "healthinsurance" -> {

                                    employee.sickSalary = insurance.getIncome(employee.currentSalary)
                                    amount = employee.sickSalary
                                    messageList = messageList.plus("Du är försäkrad och får en sjukpenning på $amount.")
                                }
                            }
                        }

                        //Get chance to be approved by swedish authority
                        randomValues = List(1) { Random.nextInt(0, 100) }
                        if (randomValues[0] < 15) {
                            //Din sjukskrivning blir avslagen
                            employee.sickSalary = 0.0

                            messageList = messageList.plus("Tyvärr blir din sjukskrivning avslagen.")
                        }

                        //How many months are you sick?
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        employee.countSickMonth = randomValues[0]

                        employee.countWorkMonth -= employee.countSickMonth
                        accountSalary.amount += employee.sickSalary * employee.countSickMonth

                        eventList = eventList.plus(randomEvents[randomEventValues[0]])
                        yearList = yearList.plus(year)

                        amount = employee.countSickMonth.toDouble()
                        messageList = messageList.plus("Du är sjukskriven i $amount månader när du är $age år.")
                    }
                }
            }

            "luck" -> {
                //Lycklig
                if (randomLifeValues[year - 1] < 40) {
                    person.luck = true

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    messageList = messageList.plus("Du är lycklig när du är $age år!")
                }
            }

            "unemployed" -> {
                //Varslad
                union.countUnEmployeeMonth = 0
                union.unEmployedSalaryAmount = 0.0

                if (randomLifeValues[year - 1] < 5) {

                    //Chans till avgångsvederlag
                    randomValues = List(1) { Random.nextInt(0, 100) }
                    if (randomValues[0] < 30 && employee.countWorkMonth >= 12) {
                        //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                        amount = employee.currentSalary * (employee.countWorkMonth / 12)
                        messageList = messageList.plus("Du fick avgångsvederlag på $amount!")
                        accountSalary.amount += amount
                    }
                    //Hur många månader är du arbetslös
                    randomValues = List(1) { Random.nextInt(1, pensionAge * 12) }
                    union.countUnEmployeeMonth = randomValues[0]
                    if (employee.countWorkMonth > 12) {
                        //För att få akassa behövs 12 månaders arbete
                        if (union.incomeInsurance)
                            union.setIncomeInsurance(employee.currentSalary)
                        else if (union.akassa)
                            union.setAkassa(employee.currentSalary)
                        else
                            union.setNoAkassa(employee.currentSalary)
                    }

                    //Endast 300 dagar kan man få a-kassa
                    employee.countWorkMonth -= union.countUnEmployeeMonth
                    accountSalary.amount += union.unEmployedSalaryAmount

                    amount = union.countUnEmployeeMonth.toDouble()

                    messageList = messageList.plus("Du var arbetslös i $amount när du är $age år")
                    amount = union.unEmployedSalaryAmount

                    if (union.incomeInsurance && union.extraInsurance)
                        messageList = messageList.plus("Då du har inkomstförsäkring och tilläggsförsäkring fick du ut ca: $amount i månaden")
                    else if (union.incomeInsurance)
                        messageList = messageList.plus("Då du har inkomstförsäkring fick du ut ca: $amount i månaden")
                    else if (union.akassa)
                        messageList = messageList.plus("Då du har a-kassa fick du ut ca: $amount i månaden")
                    else
                        messageList = messageList.plus("Du har ingen a-kassa så du fick ut ca: $amount i månaden")

                    //Nytt jobb
                    randomValues = List(1) { Random.nextInt(0, pensionAge*11) }
                    profession = props.selectedProfession.getProfession(randomValues[0])

                    person.professions = person.professions.plus(profession)
                    employee.currentSalary = profession.salary * person.age
                    employee.salaryFixedPercentage = profession.salaryFixedPercentage * 100
                    employee.salaryVariablePercentage = profession.salaryVariablePercentage * 100
                    messageList = messageList.plus(profession.professionText)

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                }
            }

            "magellit" -> {
                //Magellit
                if (randomLifeValues[year - 1] < 30) {
                    person.magellit = true
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    messageList = messageList.plus("Du blev träffad av en Magellit när du är $age")

                }
            }

            "parent" -> {
                //Babies
                parent.countFamilyMonth = 0
                parent.familySalary = 0.0
                if (randomLifeValues[year - 1] < 5 && age <= 45) {
                    parent.countBabies += 1
                    parent.familySalary = parent.getIncome(employee.currentSalary)
                    if (person.magellit) parent.familySalary += 5000.0

                    parent.countFamilyMonth += parent.familyMonth

                    employee.countWorkMonth -= parent.countFamilyMonth
                    accountSalary.amount += parent.familySalary * parent.countFamilyMonth

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    amount = parent.familySalary
                    messageList = messageList.plus("Härligt att du bilda familj och fick barn när du är $age! Du är föräldrarledig i 6 månader och föräldrarpenningen ligger på $amount kr. ")
                }
            }

            "VAB" -> {
                if ((randomLifeValues[year - 1] < 50 && parent.countBabies > 0)) {
                    //VAB
                    if (parent.countBabies > 0) {
                        randomValues = List(1) { Random.nextInt(1, 12) }
                        parent.countFamilyMonth += randomValues[1]
                        parent.familySalary = parent.getIncome(employee.currentSalary)
                        if (person.magellit) parent.familySalary += 5000.0

                        employee.countWorkMonth -= parent.countFamilyMonth
                        accountSalary.amount += parent.familySalary * parent.countFamilyMonth

                        eventList = eventList.plus(randomEvents[randomEventValues[0]])
                        yearList = yearList.plus(year)

                        amount = parent.countFamilyMonth.toDouble()
                        messageList = messageList.plus("Härligt med barn, du vabbar $amount månader när du är $age och föräldrarpenningen ligger på $amount kr. ")
                    }
                }
            }
        }
        age += 1
        employee.currentSalary = props.selectedProfession.salary * age

        employee.countWorkMonth += 12
        accountSalary.amount += (employee.currentSalary - (employee.currentSalary * person.pension)) * employee.countWorkMonth
        accountPension.amount += (employee.currentSalary * person.pension) * employee.countWorkMonth
    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 10.px
            left = 800.px
            fontFamily = FontFamily.cursive
        }

        for (message in messageList) {
            p {
                +message
            }
        }
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


