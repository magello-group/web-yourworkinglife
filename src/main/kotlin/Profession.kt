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
    var objectType: String = "",
    var randomSick: Int = 30,
    var randomUnemployed: Int = 30,
    var randomLuck: Int = 30,
    var randomBonus: Int = 30
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
        /*
            Yrken med högst löner:
            Fondchef, funktions- eller mellanchef	144 900 kr
            Lånechef, bank, funktions- eller mellanchef	144 900 kr
            Regionchef, försäkringar, funktions- eller mellanchef	144 900 kr
            Kontorschef, bank, försäkring, funktions- eller mellanchef	144 900 kr
            Ambassadör	99 900 kr
            Yrkeskod	Yrke	Månadslön
            1331	Forsknings- och utvecklingschefer, nivå 1	78 500
            1361	Driftchefer inom bygg, anläggning och gruva, nivå 1	78 000
            1311	IT-chefer, nivå 1	76 000
        */

        return listOf(
            //Salary
            Profession(
                1, "solo", "Egenföretagare", "Du driver ditt eget företag som ingenjör",
                0.0F, 64800.0F, 2000.0F, 58, "salary",30, 30, 20, 60
            ),
            Profession(
                2, "security", "Säkerhetschef", "Du jobbar som säkerhetschef",
                0.0F, 67500.0F, 2000.0F, 58, "salary", 30,10, 20, 60
            ),
            Profession(
                3, "VD", "Verkställande direktör", "Du jobbar som VD på ett spelbolag",
                0.0F, 163000.0F, 4500.0F, 58, "salary", 30, 10, 20, 60
            ),
            Profession(
                4, "ambassador", "Ambassadör", "Du jobbar som ambassadör i Tyskland",
                0.0F, 100000.0F, 2800.0F, 58, "salary",30,20, 20, 60
            ),
            Profession(
                4, "chef", "Bankchef", "Du jobbar som chef på en bank",
                0.0F, 88000.0F, 2500.0F, 58, "salary",30,40, 20, 60
            ),
            Profession(
                6, "driftchef", "Driftchef", "Du jobbar som driftchef inom bygg, anläggning och gruva",
                0.0F, 64000.0F, 2000.0F, 58, "salary",30,40, 20, 60
            ),
            Profession(
                7, "advokat", "Advokat", "Du jobbar som Advokat",
                0.0F, 46400.0F, 1400.0F, 58, "salary",30,10, 20, 60
            ),
            Profession(
                8, "doctor", "Läkare", "Du jobbar som Läkare",
                0.0F, 58000.0F, 1700.0F, 58, "salary",10,10, 20, 60
            ),
            Profession(
                9, "programmer", "Programmerar", "Du jobbar som programmerare på ett säkerhetsbolag",
                0.0F, 58000.0F, 1700.0F, 58, "salary",30,10, 20, 60
            ),
            Profession(
                11, "professor", "Professor", "Du jobbar som professor",
                0.0F, 50000.0F, 1500.0F, 60, "salary",40,10, 40, 20
            ),

            //Pension
            Profession(
                12, "bank", "Banktjänsteman", "Du jobbar på som banktjänsteman",
                0.0F, 0.0F, 1000.0F, 58, "pension",30,50, 10, 10
            ),
            Profession(
                13, "insurance", "Försäkringsdirektör", "Du jobbar som försäkringsdirektör",
                0.0F, 100000.0F, 2800.0F, 58, "pension",10,50, 10, 10
            ),
            Profession(
                14, "programmer", "Programmerar", "Du jobbar som programmerare på en bank",
                0.0F, 40000.0F, 1200.0F, 60, "pension",30,20, 10, 10
            ),

            //Äventyr
            Profession(
                15, "detektiv", "Detektiv", "Du jobbar som detektiv",
                0.0F, 30800.0F, 3000.0F, 50, "adventure",30,50, 40,20
            ),
            Profession(
                16, "pilote", "Pilot", "Du jobbar på ett flygbolag",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",20,10, 40, 20
            ),
            Profession(
                17, "fireman", "Brandman", "Du jobbar som brandman",
                0.0F, 40500.0F, 1190.0F, 50, "adventure",40,40, 40, 20
            ),
            Profession(
                18, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 68200.0F, 2000.0F, 50, "adventure",40,10, 40, 20
            ),
            Profession(
                19, "kapten", "Kapten", "Du jobbar som kapten på ett fartyg",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",30,30, 40, 20
            ),
            Profession(
                20, "rescue", "Fjällräddare", "Du jobbar som fjällräddare",
                0.0F, 30000.0F, 900.0F, 50, "adventure",20,50, 40, 20
            ),
            Profession(
                21, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                0.0F, 30000.0F, 900.0F, 60, "adventure",40,40, 40, 20
            ),
            Profession(
                22, "programmer", "Programmerar", "Du jobbar som programmerare hos underrättelsetjänsten",
                0.0F, 50000.0F, 1500.0F, 60, "adventure",40,10, 40, 20
            ),

            //Semester
            Profession(
                23, "authority", "Statsanställd", "Du jobbar på en myndighet",
                0.0F, 30000.0F, 900.0F, 60, "vacation",30,10, 10, 20
            ),
            Profession(
                24, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                0.0F, 30000.0F, 900.0F, 60, "vacation",40,40,10, 10
            ),
            Profession(
                25, "builder", "Snickare", "Du jobbar som snickare på ett byggbolag",
                0.0F, 35500.0F, 1000.0F, 60, "vacation",40,20,10, 10
            ),
            Profession(
                26, "teacher", "Lärare", "Du jobbar som lärare",
                0.0F, 35000.0F, 900.0F, 65, "vacation",40,40,10, 10
            ),
            Profession(
                27, "programmer", "Programmerar", "Du jobbar som programmerare på en myndighet",
                0.0F, 35000.0F, 900.0F, 60, "vacation",30,10,10, 10
            ),

            //Samhället
            Profession(
                28, "teacher", "Lärare", "Du jobbar som lärare",
                0.0F, 50800.0F, 1500.0F, 65, "family",40,40, 40, 10
            ),
            Profession(
                29, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 0.0F, 1190.0F, 50, "family",40,10, 40, 10
            ),
            Profession(
                30, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "family",20,20, 40, 10
            ),
            Profession(
                31, "programmer", "Programmerar", "Du jobbar som programmerare hos polisen",
                0.0F, 39000.0F, 1000.0F, 60, "family",30,10, 40, 10
            ),
            Profession(
                32, "socialworker", "Socialassistent", "Du jobbar som socialassistent",
                0.0F, 32000.0F, 900.0F, 60, "family",40,20, 40, 10
            ),

            //Chilla
            Profession(
                33, "writer", "Trädgårdsmästare", "Du jobbar som trädgårdsmästare!",
                0.0F, 0.0F, 800.0F, 75, "chilla",20,50, 50, 10
            ),
            Profession(
                34, "yoga", "Yogainstruktör", "Du jobbar som yogainstruktör",
                0.0F, 0.0F, 800.0F, 75, "chilla", 20,50, 50, 10
            ),
            Profession(
                35, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "chilla",20,20, 50, 10
            ),
            Profession(
                36, "painter", "Konstnär", "Du jobbar som konstnär",
                0.0F, 32000.0F, 900.0F, 60, "chilla",20,50, 50, 60
            ),
            Profession(
                37, "musiker", "Musiker", "Du jobbar som musiker",
                0.0F, 39000.0F, 1000.0F, 60, "chilla", 20,50, 50, 60
            ),
            Profession(
                38, "writer", "Författare", "Du jobbar som författare",
                0.0F, 39000.0F, 1000.0F, 60, "chilla",20,50, 50, 60
            ),
            Profession(
                39, "programmer", "Programmerar", "Du jobbar som programmerare på en högskola",
                0.0F, 40000.0F, 1000.0F, 60, "chilla",30,10, 50, 10
            ),

            //Kul
            Profession(
                40, "artist", "Cirkusprinsessa", "Du jobbar som cirkusartist!",
                0.0F, 30000.0F, 900.0F, 75, "fun",40,40, 60, 10
            ),
            Profession(
                41, "pt", "Privat tränare", "Du jobbar som PT på ett gym",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 10
            ),
            Profession(
                42, "actor", "Skådespelare", "Du jobbar som skådespelare",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 60
            ),
            Profession(
                43, "comedian", "Komiker", "Du jobbar som komiker",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 60
            ),
            Profession(
                44, "musiker", "Musiker", "Du jobbar som musiker",
            0.0F, 39000.0F, 1000.0F, 60, "fun",30,50, 60, 60
            ),
            Profession(
                45, "riding", "Ridlärare", "Du jobbar som ridlärare",
                0.0F, 33000.0F, 950.0F, 60, "fun",30,50, 60, 10
            ),
            Profession(
                46, "architect", "Arkitekt", "Du jobbar som landskapsarkitekt",
            0.0F, 40000.0F, 1200.0F, 60, "fun",30,40, 60, 10
            ),
            Profession(
                47, "programmer", "Programmerar", "Du jobbar som programmerare på ett spelbolag",
                0.0F, 58000.0F, 1700.0F, 58, "fun",30,10, 60, 60
            )
        )
    }

    fun showNewProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        val message: Message = Message(
            storyId,
            "Dags att börja ditt nya jobb! ${this.professionText}.",
            "",
            "blinking"
        )
        message.status.profession = this.title
        storyList = storyList.plus(message)

        return storyList
    }
    fun showSearchProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        val message: Message = Message(
            storyId,
            "Dags att söka nytt jobb!",
            "",
            "blinking"
        )
        message.status.profession = this.title
        storyList = storyList.plus(message)

        return storyList
    }

    fun showCurrentProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1
        var message: Message

        message = Message(
            storyId,
            "Du jobbar som ${this.title}.",
            "",
            ""
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