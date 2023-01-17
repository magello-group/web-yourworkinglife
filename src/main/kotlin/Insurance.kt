import kotlinx.serialization.Serializable

@Serializable
data class Insurance( val insuranceType: String )
{
    var sickMonthsCount: Int = 0
    var sickSalaryAmount: Double = 0.0

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