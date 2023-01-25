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
import react.useState
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
    var employeeId: Int = 1
    var employee = Employee(employeeId)
    val accountPension = Account(1)
    accountPension.accountType = "Pension"
    val accountSalary = Account(2)
    accountSalary.accountType = "Lön"
    val accountDepot = Account(3)
    accountDepot.accountType = "Depå"
    val union = person.union

    val text: String = ""

    person.professions = person.professions.plus(profession)
    employee.title = profession.title
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

        randomEventValues = List(1) { Random.nextInt(0, 10) }

        when (randomEvents[randomEventValues[0]].eventType) {
            "depot" -> {
                //Bonus
                if (randomLifeValues[year - 1] < 25) {
                    //Get value och financial instruments
                    randomValues = List(1) { Random.nextInt(0, 10000000) }
                    amount = randomValues[0].toDouble()
                    messageList = messageList.plus("När du är $age år får du bonus på $amount!")
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
                if (randomLifeValues[year - 1] < 8 ) {
                    when (randomEvents[randomEventValues[0]].objectType) {
                        "burnedout" -> {
                            if (!person.luck && age > 30) person.sick = true
                        }
                        "heartattack" -> {
                            if (!person.luck && age > 40) person.sick = true
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
                        messageList = messageList.plus( "När du är $age år " + randomEvents[randomEventValues[0]].eventText)
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
                        messageList = messageList.plus("Du är sjukskriven i $amount månader.")
                    }
                }
            }

            "luck" -> {
                //Lycklig
                if (randomLifeValues[year - 1] < 25) {
                    person.luck = true

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    messageList = messageList.plus("När du är $age år är du lycklig!")
                }
            }

            "unemployed" -> {
                //Varslad
                union.countUnEmployeeMonth = 0
                union.unEmployedSalaryAmount = 0.0

                if (randomLifeValues[year - 1] < 25) {

                    //Chans till avgångsvederlag
                    randomValues = List(1) { Random.nextInt(0, 100) }
                    if (randomValues[0] < 30 && employee.countWorkMonth >= 12) {

                        //Avgångsvederlag! Du får lön i lika många månader som du jobbat år
                        amount = employee.currentSalary * (employee.countWorkMonth / 12)
                        messageList = messageList.plus("När du är $age år får du avgångsvederlag på $amount!")
                        accountSalary.amount += amount
                    }

                    //Hur många dagar är du arbetslös, 22 dagar per månad får man ersättning
                    randomValues = List(1) { Random.nextInt(1, 300) }
                    union.countUnEmployeeMonth = randomValues[0]/22
                    if (employee.countWorkMonth > 12) {
                        //För att få akassa behövs 12 månaders arbete
                        if (union.incomeInsurance)
                            union.unEmployedSalaryAmount = union.getIncomeInsurance(employee.currentSalary)
                        else if (union.akassa)
                            union.unEmployedSalaryAmount = union.getAkassa(employee.currentSalary)
                        else
                            union.unEmployedSalaryAmount = union.getNoAkassa(employee.currentSalary)
                    }

                    //Endast 300 dagar kan man få a-kassa
                    employee.countWorkMonth -= union.countUnEmployeeMonth
                    accountSalary.amount += union.unEmployedSalaryAmount

                    amount = randomValues[0].toDouble()
                    messageList = messageList.plus("När du är $age år är du arbetslös i $amount dagar.")

                    if (union.incomeInsurance && union.extraInsurance) {
                        amount = union.unEmployedSalary200
                        messageList = messageList.plus("Då du har inkomstförsäkring + tillägsförsäkring fick du ut ca: $amount i 200 dagar")
                        amount = union.unEmployedSalary300
                        messageList = messageList.plus("De sista 100 dagarna fick du ut av a-kassan ca: $amount")

                    } else if (union.incomeInsurance) {
                        amount = union.unEmployedSalary150
                        messageList = messageList.plus("Då du har inkomstförsäkring fick du ut ca: $amount i 150 dagar")
                        amount = union.unEmployedSalary300
                        messageList = messageList.plus("De sista 150 dagarna fick du ut av a-kassan ca: $amount")
                    } else if (union.akassa) {
                        amount = union.unEmployedSalary100
                        messageList = messageList.plus("Då du har a-kassa fick du ut ca: $amount i 100 dagar")
                        amount = union.unEmployedSalary300
                        messageList = messageList.plus("De sista 200 dagarna fick du ut av a-kassan ca: $amount")
                    } else {
                        amount = union.unEmployedSalary100
                        messageList = messageList.plus("Du har ingen a-kassa så du fick ut ca: $amount i 100 dagar")
                        amount = union.unEmployedSalary300
                        messageList = messageList.plus("De sista 200 dagarna fick du ut av a-kassan ca: $amount")
                    }

                    //Spara tidigare jobb
                    person.professions = person.professions.plus(profession)
                    person.employees = person.employees.plus(employee)

                    //Nytt jobb
                    randomValues = List(1) { Random.nextInt(0, 12) }
                    profession = props.selectedProfession.getProfession(randomValues[0])

                    employeeId += 1
                    employee = Employee(employeeId)
                    employee.title = profession.title
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
                if (randomLifeValues[year - 1] < 10) {
                    person.magellit = true
                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    messageList = messageList.plus("När du är $age år blir du träffad av en Magellit!")

                }
            }

            "parent" -> {
                //Babies
                parent.countFamilyMonth = 0
                parent.familySalary = 0.0
                if (randomLifeValues[year - 1] < 25 && age <= 45) {
                    parent.countBabies += 1
                    parent.familySalary = parent.getIncome(employee.currentSalary)
                    if (person.magellit) parent.familySalary += 5000.0

                    parent.countFamilyMonth += parent.familyMonth

                    employee.countWorkMonth -= parent.countFamilyMonth
                    accountSalary.amount += parent.familySalary * parent.countFamilyMonth

                    eventList = eventList.plus(randomEvents[randomEventValues[0]])
                    yearList = yearList.plus(year)
                    amount = parent.familySalary
                    messageList = messageList.plus("När du är $age år får du barn!")
                    messageList = messageList.plus("Du får en föräldrarpenningen på $amount kr i 6 månader.")
                }
            }

            "VAB" -> {
                if ((randomLifeValues[year - 1] < 100 && parent.countBabies > 0)) {
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
                        messageList = messageList.plus("När du är $age år VAB:ar du $amount månader.")
                        messageList = messageList.plus("Du får en föräldrarpenningen på $amount kr.")
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
    person.age = age
    person.professions = person.professions.plus(profession)
    person.employees = person.employees.plus(employee)
    person.accounts = person.accounts.plus(accountSalary)
    person.accounts = person.accounts.plus(accountDepot)
    person.accounts = person.accounts.plus(accountPension)

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 100.px
            left = 800.px
            fontFamily = FontFamily.cursive
        }

        for (message in messageList) {
            p {
                +message
            }
        }
    }


    ShowWorkingLife {
        actualProfession = props.selectedProfession
        actualPerson = person
    }

}


