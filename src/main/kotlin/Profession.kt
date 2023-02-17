import kotlinx.serialization.Serializable

@Serializable
data class Profession(
    var id: Int,
    var professionType: String = "",
    var title: String = "",
    var professionText:String = "",
    var maxSalary: Float = 0.0F,
    var medianSalary: Float = 0.0F,
    var salary: Float = 0.0F,
    var pensionAge: Int = 0,
    var objectType: String = ""
) {

    fun getProfessionList(objectType: String): List<Profession> {

        val professions: List<Profession> = this.getAllProfession()
        var selectedProfessions: List<Profession> = emptyList()

        for (profession in professions) {
            if (profession.objectType == objectType) {
                selectedProfessions = selectedProfessions.plus(profession)
            }
        }

        return selectedProfessions
    }

    fun getAllProfession(): List<Profession> {

        return listOf(
            Profession(
                1, "solo", "Egenföretagare", "Du jobbar på eget företag inom IT",
                0.0F, 0.0F, 2000.0F, 58, "salary"
            ),
            Profession(
                2, "security", "Säkerhetsspecialist", "Du jobbar på ett säkerhetsföretag",
                0.0F, 52600.0F, 2500.0F, 58, "salary"
            ),
            Profession(
                3, "VD", "VD", "Du jobbar som VD på ett spelbolag",
                0.0F, 0.0F, 3000.0F, 58, "salary"
            ),
            Profession(
                4, "bank", "Banktjänsteman", "Du jobbar på en bank",
                0.0F, 0.0F, 1200.0F, 58, "pension"
            ),
            Profession(
                5, "insurance", "Försäkringsagent", "Du jobbar på ett försäkringsbolag",
                0.0F, 0.0F, 1200.0F, 58, "pension"
            ),
            Profession(
                6, "agent", "Hemlig agent", "Du jobbar på ett hemligt uppdrag",
                0.0F, 0.0F, 3000.0F, 50, "adventure"
            ),
            Profession(
                7, "pilote", "Pilot", "Du jobbar på ett flygbolag",
                0.0F, 0.0F, 3000.0F, 50, "adventure"
            ),
            Profession(
                8, "fireman", "Brandman", "Du jobbar som brandman",
                0.0F, 40500.0F, 1190.0F, 50, "adventure"
            ),
            Profession(
                9, "authority", "Statsanställd", "Du jobbar på en myndighet",
                0.0F, 0.0F, 1000.0F, 60, "vacation"
            ),
            Profession(
                10, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                0.0F, 0.0F, 1200.0F, 60, "vacation"
            ),
            Profession(
                11, "builder", "Snickare", "Du jobbar på ett byggbolag",
                0.0F, 0.0F, 1500.0F, 60, "vacation"
            ),
            Profession(
                12, "teacher", "Förskolelärare", "Du jobbar på en förskola",
                0.0F, 0.0F, 1385.0F, 65, "family"
            ),
            Profession(
                13, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 0.0F, 1190.0F, 50, "family"
            ),
            Profession(
                14, "rektor", "Rektor", "Du jobbar på en högskola",
                0.0F, 0.0F, 1000.0F, 65, "family"
            ),
            Profession(
                15, "writer", "Trädgårdsmästare", "Du jobbar som trädgårdsmästare!",
                0.0F, 0.0F, 800.0F, 75, "chilla"
            ),
            Profession(
                16, "painter", "Yogainstruktör", "Du jobbar som yogainstruktör",
                0.0F, 0.0F, 800.0F, 75, "chilla"
            ),
            Profession(
                16, "artist", "Cirkusprinsessa", "Du jobbar som artist!",
                0.0F, 0.0F, 500.0F, 75, "fun"
            ),
            Profession(
                17, "comedian", "Komiker", "Du jobbar som komiker",
                0.0F, 0.0F, 500.0F, 75, "fun"
            )

        )
    }

    fun showNewProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList

        val message: Message = Message(
            messageId,
            "Du byter jobb! ${this.professionText}.",
            "",
            "blinking"
        )
        message.status.profession = this.title
        storyList = storyList.plus(message)

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