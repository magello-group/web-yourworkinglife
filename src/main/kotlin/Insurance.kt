import kotlinx.serialization.Serializable

@Serializable
data class Insurance( val personId: Int, val insuranceType: String )
{
    val incomePercentage: Float = 0.765F
    val maxSalary: Float = 43750.0F
    val maxIncome: Float = 33480.0F

    fun getIncome(salary: Float): Float {
        var sum: Float = 0.0F
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