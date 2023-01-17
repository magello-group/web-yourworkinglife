import kotlinx.serialization.Serializable

@Serializable
data class Employee( val employeeNumber: Int )
{
    var salary: Double = 0.0
    var salaryFixedPercentage: Double = 0.0
    var salaryVariablePercentage: Double = 0.0
    var workMonthCount: Int = 0

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