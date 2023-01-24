import kotlinx.serialization.Serializable

@Serializable
data class Profession(
    var id: Int,
    var professionType: String = "",
    var professionText:String = "",
    var salaryFixedPercentage: Double = 0.0,
    var salaryVariablePercentage: Double = 0.0,
    var salary: Double = 0.0,
    var pensionAge: Int = 0,
    var objectType: String = ""
) {
    fun getProfessionList(objectType: String): List<Profession> {
        var professions: List<Profession> = emptyList()

        when(objectType) {
            "salary" -> {
                professions = listOf(
                    Profession(0, "solo","Wow, du startar eget företag som systemutvecklare",
                        0.0,1.0, 2000.0,58, "salary"),
                    Profession(1, "security", "Wow, du får jobb på ett säkerhetsföretag som säkerhetsspecialist",
                        0.0, 1.0, 2500.0,58, "salary") )
            }
            "pension" -> {
                professions = listOf(
                    Profession(2, "bank", "Du tar jobb som utvecklare på en bank",
                        1.0, 0.0, 1000.0,58, "pension"),
                    Profession(3, "insurance", "Du tar jobb på ett försäkringsbolag",
                        1.0, 0.0, 900.0,58, "pension"))
            }
            "adventure" -> {
                professions = listOf(
                    Profession(4, "secret", "Du tar jobb som utvecklare på ett hemligt uppdrag",
                        1.0, 0.0, 3000.0,50, "adventure"),
                    Profession(5, "police", "Du tar jobb hos polisen",
                        1.0, 0.0, 2000.0,50, "adventure"))
            }
            "vacation" -> {
                professions = listOf(
                    Profession(6, "authority", "Du tar jobb som utvecklare på en myndighet",
                    1.0, 0.0, 500.0,60, "vacation"),
                    Profession(7, "pilot", "Du tar jobb på en resebyrå",
                    1.0, 0.0, 900.0,60, "vacation"))
            }
            "family" -> {
                professions = listOf(
                    Profession(8, "family", "Du tar jobb som utvecklare på ett företag med bästa villkor för barnledig",
                    1.0, 0.0, 1000.0,65, "family"),
                    Profession(9, "teacher", "Du tar jobb som på en skola",
                    1.0, 0.0, 800.0,65, "family"))
            }
            "chilla" -> {
                professions = listOf(
                    Profession(10, "magellit", "Wow, Du blir träffad av en magellit!",
                        1.0, 0.0, 0.0,75, "chilla"),
                    Profession(11, "lazy", "Du behöver träffa en terapeut",
                        1.0, 0.0, 0.0,75, "chilla"))
            }

        }
        return professions
    }

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