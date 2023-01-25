import kotlinx.serialization.Serializable

@Serializable
data class Employee( val employeeId: Int )
{
    var title: String = ""
    var firstSalary: Double = 0.0
    var currentSalary: Double = 0.0
    var salaryFixedPercentage: Double = 0.0
    var salaryVariablePercentage: Double = 0.0
    var countWorkMonth: Int = 0
    var sickSalary: Double = 0.0
    var countSickMonth: Int = 0

    fun registerWork() {
        //Insert work in db
    }

    fun updateWork() {
        //Update work in db
    }

    fun getWork() {
        //Select work information
    }
}