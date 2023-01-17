import kotlinx.serialization.Serializable

@Serializable
data class Profession(
    var professionTypeIdentity: Int,
    var professionType: String,
    var professionText:String,
    var salaryFixedPercentage: Double = 0.0,
    var salaryVariablePercentage: Double = 0.0,
    var salary: Double = 0.0,
    var pensionAge: Int = 0,
    var objectType: String = ""
) {
    fun storeProfession() {
        //Insert profession in db
    }

    fun registerProfession() {
        //Update profession in db
    }

    fun getProfession() {
        //Select profession information
    }
}