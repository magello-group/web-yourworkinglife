import kotlinx.serialization.Serializable

@Serializable
data class Profession(
    var professionTypeIdentity: String?,
    var professionType: String?,
    var firstSalary: Double = 0.0,
    var pensionAge: Int = 0,
    var typeOfGoal: String = ""
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