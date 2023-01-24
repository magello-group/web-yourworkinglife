import kotlinx.serialization.Serializable
import kotlin.math.max

@Serializable
data class Parent (val personId: Int) {
    val link: String = "https://www.forsakringskassan.se/privatperson/foralder/vard-av-barn-vab"
    val incomePercentage = 0.765
    val maxSalary = 43750.0
    val maxIncome = 33480.0
    var familySalary = 0.0
    var familyMonth = 6
    var countBabies = 0
    var countFamilyMonth = 0

    fun getIncome(salary: Double): Double {
        var sum: Double = 0.0
        sum = if (salary < maxSalary)
            salary * incomePercentage
        else
            maxIncome

        return sum
    }


    fun registerVAB() {
        //Insert union in db
    }

    fun updateVAB() {
        //Update union in db
    }

    fun getVAB() {
        //Select union information
    }
}