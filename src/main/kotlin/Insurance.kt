import kotlinx.serialization.Serializable

@Serializable
data class Insurance( val personId: Int, val insuranceType: String )
{
    val incomePercentage = 0.765
    val maxSalary = 43750.0
    val maxIncome = 33480.0

    fun getIncome(salary: Double): Double {
        var sum: Double = 0.0
        sum = if (salary < maxSalary)
            salary * incomePercentage
        else
            maxIncome

        return sum
    }
    fun registerInsurance(){
        //Insert insurance in db
    }

    fun updateInsurance(){
        //Update insurance in db
    }
    fun getInsurance(){
        //Select insurance information
    }
}