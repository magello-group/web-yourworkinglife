import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key

external interface StartWorkingLifeProps : Props {
    var selectedProfession: Profession
    var selectedAge: String
}

val StartWorkingLife = FC<StartWorkingLifeProps> { props ->

    // Initiera arbetslivet

    val person = Person(0)
    val union = Union(0)
    val insurance = Insurance("healthinsurance")
    val employee = Employee(0)
    var accountPension = Account("pension")
    var accountSalary = Account("salary")
    var accountDepot = Account("depot")

    var sumDouble: Double
    var topPX = 540

    val salary = props.selectedProfession.salary * props.selectedAge.toInt()
    val salaryFixedPercentage = props.selectedProfession.salaryFixedPercentage * 100
    val salaryVariablePercentage = props.selectedProfession.salaryVariablePercentage * 100

    /* TODO
    props.actualPerson.name = props.actualName
    props.actualPerson.age = props.actualAge.toInt()
    props.actualEmployee.salary = props.actualProfession.salary * props.actualPerson.age
    props.actualEmployee.salaryFixedPercentage = props.actualProfession.salaryFixedPercentage
    props.actualEmployee.salaryVariablePercentage = props.actualProfession.salaryVariablePercentage
    */

    ShowAction {
        actualProfession = props.selectedProfession
        actualAge = props.selectedAge
    }

    when (props.selectedProfession.professionType) {
        "solo" -> {}
        "security" -> {}
        "bank" -> {}
        "insurance" -> {}
        "secret" -> {}
        "police" -> {}
        "authority" -> {}
        "teacher" -> {}
        "family" -> {}
        "school" -> {}
        "magellit" -> {}
        "lazy" -> {}
    }
}