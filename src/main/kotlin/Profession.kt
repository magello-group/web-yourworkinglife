import kotlinx.serialization.Serializable

@Serializable
data class Profession(
    var id: Int,
    var professionType: String = "",
    var title: String = "",
    var professionText:String = "",
    var salaryFixedPercentage: Float = 0.0F,
    var salaryVariablePercentage: Float = 0.0F,
    var salary: Float = 0.0F,
    var pensionAge: Int = 0,
    var objectType: String = ""
) {

    fun getProfessionList(objectType: String): List<Profession> {

        val professions: List<Profession> = getAllProfession()

        var selectedProfessions: List<Profession> = emptyList()

        for (profession in professions) {
            if (profession.objectType == objectType) {
                selectedProfessions = selectedProfessions.plus(profession)
            }
        }

        return selectedProfessions
    }

    fun getProfession(id: Int): Profession {

        val professions: List<Profession> = getAllProfession()

        return professions[id]
    }

    fun getAllProfession(): List<Profession> {

        return listOf(
            Profession(
                1, "solo", "egenföretagare", "Wow, du startar eget företag inom IT",
                0.0F, 1.0F, 2000.0F, 58, "salary"
            ),
            Profession(
                2, "security", "säkerhetsspecialist", "Wow, du får jobb på ett säkerhetsföretag",
                0.0F, 1.0F, 2500.0F, 58, "salary"
            ),
            Profession(
                3, "VD", "VD", "Wow, du blir VD på ett spelbolag",
                0.0F, 1.0F, 2500.0F, 58, "salary"
            ),
            Profession(
                4, "bank", "banktjänsteman", "Du tar jobb på en bank",
                1.0F, 0.0F, 1000.0F, 58, "pension"
            ),
            Profession(
                5, "insurance", "försäkringsagent", "Du tar jobb på ett försäkringsbolag",
                1.0F, 0.0F, 900.0F, 58, "pension"
            ),
            Profession(
                6, "secret", "agent", "Du tar jobb på ett hemligt uppdrag",
                1.0F, 0.0F, 3000.0F, 50, "adventure"
            ),
            Profession(
                7, "pilot", "pilot", "Du tar jobb på ett flygbolag",
                1.0F, 0.0F, 3000.0F, 50, "adventure"
            ),
            Profession(
                8, "fireman", "brandman", "Du tar jobb som brandman",
                1.0F, 0.0F, 3000.0F, 50, "adventure"
            ),
            Profession(
                9, "authority", "statsanställd", "Du tar jobb på en myndighet",
                1.0F, 0.0F, 500.0F, 60, "vacation"
            ),
            Profession(
                10, "travelagent", "reseledare", "Du tar jobb på en resebyrå ",
                1.0F, 0.0F, 900.0F, 60, "vacation"
            ),
            Profession(
                11, "builder", "snickare", "Du tar jobb på ett byggbolag",
                1.0F, 0.0F, 900.0F, 60, "vacation"
            ),
            Profession(
                12, "teacher", "förskolelärare", "Du tar jobb på en förskola",
                1.0F, 0.0F, 800.0F, 65, "family"
            ),
            Profession(
                13, "police", "polis", "Du tar jobb hos polisen",
                1.0F, 0.0F, 2000.0F, 50, "family"
            ),
            Profession(
                14, "rektor", "rektor", "Du tar jobb på en högskola",
                1.0F, 0.0F, 1000.0F, 65, "family"
            ),
            Profession(
                15, "artist", "artist", "Du blir artist!",
                1.0F, 0.0F, 500.0F, 75, "chilla"
            ),
            Profession(
                16, "lazy", "sökande", "Du behöver träffa en terapeut",
                1.0F, 0.0F, 10.0F, 75, "chilla"
            )
        )
    }

    fun showNewProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Du byter jobb! ${this.professionText}",
                "",
                ""
            )
        )

        return storyList
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