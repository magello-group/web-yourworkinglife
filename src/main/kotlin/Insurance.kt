import kotlinx.serialization.Serializable

@Serializable
data class Insurance( val personId: Int, val insuranceType: String )
{
    val incomePercentage: Float = 0.765F
    val maxSalary: Float = 43750.0F
    val maxIncome: Float = 33480.0F

    fun getIncome(salary: Float): Float {
        val sum: Float = if (salary < maxSalary)
            salary * incomePercentage
        else
            maxIncome

        return sum
    }
}