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
    var randomBonus: Int = 30,
    var pension: Float = 0.07F,
    var randomSeverancePay: Int = 0
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
                0.0F, 64800.0F, 2000.0F, 58, "salary",30, 30, 20, 60,  0.00F
            ),
            Profession(
                2, "security", "Säkerhetschef", "Du jobbar som säkerhetschef",
                0.0F, 67500.0F, 2000.0F, 58, "salary", 30,10, 20, 60,  0.07F, 30
            ),
            Profession(
                3, "VD", "Verkställande direktör", "Du jobbar som VD på ett spelbolag",
                0.0F, 163000.0F, 4500.0F, 58, "salary", 30, 10, 20, 60,  0.07F, 50
            ),
            Profession(
                4, "ambassador", "Ambassadör", "Du jobbar som ambassadör i Tyskland",
                0.0F, 100000.0F, 2800.0F, 58, "salary",30,20, 20, 60,  0.07F, 50
            ),
            Profession(
                5, "chef", "Bankchef", "Du jobbar som chef på en bank",
                0.0F, 88000.0F, 2500.0F, 58, "salary",30,40, 20, 60,  0.07F, 30
            ),
            Profession(
                6, "driftchef", "Driftchef", "Du jobbar som driftchef inom bygg, anläggning och gruva",
                0.0F, 64000.0F, 2000.0F, 58, "salary",30,40, 20, 60,  0.07F,30
            ),
            Profession(
                7, "advokat", "Advokat", "Du jobbar som Advokat",
                0.0F, 46400.0F, 1400.0F, 58, "salary",30,10, 20, 60,  0.07F, 30
            ),
            Profession(
                8, "doctor", "Läkare", "Du jobbar som Läkare",
                0.0F, 58000.0F, 1700.0F, 58, "salary",10,10, 20, 60,  0.07F
            ),
            Profession(
                9, "programmer", "Programmerar", "Du jobbar som programmerare på ett säkerhetsbolag",
                0.0F, 58000.0F, 1700.0F, 58, "salary",30,10, 20, 60,  0.07F
            ),
            Profession(
                10, "professor", "Professor", "Du jobbar som professor",
                0.0F, 50000.0F, 1500.0F, 60, "salary",40,10, 40, 20,  0.07F
            ),

            //Pension
            Profession(
                20, "bank", "Banktjänsteman", "Du jobbar på som banktjänsteman",
                0.0F, 0.0F, 1000.0F, 58, "pension",30,50, 10, 10,  0.15F, 50
            ),
            Profession(
                21, "insurance", "Försäkringsdirektör", "Du jobbar som försäkringsdirektör",
                0.0F, 100000.0F, 2800.0F, 58, "pension",10,50, 10, 10, 0.15F, 50
            ),
            Profession(
                22, "programmer", "Programmerar", "Du jobbar som programmerare på en bank",
                0.0F, 40000.0F, 1200.0F, 60, "pension",30,20, 10, 10, 0.15F, 50
            ),

            //Äventyr
            Profession(
                30, "detektiv", "Detektiv", "Du jobbar som detektiv",
                0.0F, 30800.0F, 3000.0F, 50, "adventure",30,50, 40,20, 0.0F
            ),
            Profession(
                31, "pilote", "Pilot", "Du jobbar på ett flygbolag",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",20,10, 40, 20, 0.07F, 30
            ),
            Profession(
                32, "fireman", "Brandman", "Du jobbar som brandman",
                50000.0F, 40500.0F, 1190.0F, 50, "adventure",40,40, 40, 20, 0.07F
            ),
            Profession(
                33, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 68200.0F, 2000.0F, 50, "adventure",40,10, 40, 20, 0.07F
            ),
            Profession(
                34, "kapten", "Kapten", "Du jobbar som kapten på ett fartyg",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",30,30, 40, 20, 0.07F
            ),
            Profession(
                35, "rescue", "Fjällräddare", "Du jobbar som fjällräddare",
                40000.0F, 30000.0F, 900.0F, 50, "adventure",20,50, 40, 20, 0.00F
            ),
            Profession(
                36, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                50000.0F, 30000.0F, 900.0F, 60, "adventure",40,40, 40, 20, 0.07F
            ),
            Profession(
                37, "programmer", "Programmerar", "Du jobbar som programmerare hos underrättelsetjänsten",
                0.0F, 50000.0F, 1500.0F, 60, "adventure",40,10, 40, 20, 0.07F
            ),

            //Semester
            Profession(
                40, "authority", "Statsanställd", "Du jobbar på en myndighet",
                0.0F, 30000.0F, 900.0F, 60, "vacation",30,10, 10, 20, 0.07F, 30
            ),
            Profession(
                41, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                50000.0F, 30000.0F, 900.0F, 60, "vacation",40,40,10, 10, 0.07F
            ),
            Profession(
                42, "builder", "Snickare", "Du jobbar som snickare på ett byggbolag",
                0.0F, 35500.0F, 1000.0F, 60, "vacation",40,20,10, 10, 0.07F
            ),
            Profession(
                43, "teacher", "Lärare", "Du jobbar som lärare",
                50000.0F, 35000.0F, 900.0F, 65, "vacation",40,40,10, 10, 0.07F
            ),
            Profession(
                44, "programmer", "Programmerar", "Du jobbar som programmerare på en myndighet",
                0.0F, 35000.0F, 900.0F, 60, "vacation",30,10,10, 10, 0.07F, 30
            ),

            //Samhället
            Profession(
                50, "teacher", "Lärare", "Du jobbar som lärare",
                50000.0F, 50800.0F, 1500.0F, 65, "family",40,40, 40, 10, 0.07F
            ),
            Profession(
                51, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 0.0F, 1190.0F, 50, "family",40,10, 40, 10, 0.07F
            ),
            Profession(
                52, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "family",20,20, 40, 10, 0.00F
            ),
            Profession(
                53, "programmer", "Programmerar", "Du jobbar som programmerare hos polisen",
                0.0F, 39000.0F, 1000.0F, 60, "family",30,10, 40, 10, 0.07F
            ),
            Profession(
                54, "socialworker", "Socialassistent", "Du jobbar som socialassistent",
                50000.0F, 32000.0F, 900.0F, 60, "family",40,20, 40, 10, 0.07F
            ),

            //Chilla
            Profession(
                60, "writer", "Trädgårdsmästare", "Du jobbar som trädgårdsmästare!",
                50000.0F, 0.0F, 800.0F, 75, "chilla",20,50, 50, 10, 0.00F
            ),
            Profession(
                61, "yoga", "Yogainstruktör", "Du jobbar som yogainstruktör",
                40000.0F, 0.0F, 800.0F, 75, "chilla", 20,50, 50, 10, 0.00F
            ),
            Profession(
                62, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "chilla",20,20, 50, 10, 0.00F
            ),
            Profession(
                63, "painter", "Konstnär", "Du jobbar som konstnär",
                0.0F, 32000.0F, 900.0F, 60, "chilla",20,50, 50, 60, 0.00F
            ),
            Profession(
                64, "musiker", "Musiker", "Du jobbar som musiker",
                0.0F, 39000.0F, 1000.0F, 60, "chilla", 20,50, 50, 60, 0.00F
            ),
            Profession(
                65, "writer", "Författare", "Du jobbar som författare",
                0.0F, 39000.0F, 1000.0F, 60, "chilla",20,50, 50, 60, 0.00F
            ),
            Profession(
                66, "programmer", "Programmerar", "Du jobbar som programmerare på en högskola",
                0.0F, 40000.0F, 1000.0F, 60, "chilla",30,10, 50, 10, 0.07F
            ),

            //Kul
            Profession(
                70, "artist", "Cirkusprinsessa", "Du jobbar som cirkusartist!",
                0.0F, 30000.0F, 900.0F, 75, "fun",40,40, 60, 10, 0.00F
            ),
            Profession(
                71, "pt", "Privat tränare", "Du jobbar som PT på ett gym",
                50000.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 10, 0.0F
            ),
            Profession(
                72, "actor", "Skådespelare", "Du jobbar som skådespelare",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 60, 0.0F
            ),
            Profession(
                73, "comedian", "Komiker", "Du jobbar som komiker",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50, 60, 60, 0.0F
            ),
            Profession(
                74, "musiker", "Musiker", "Du jobbar som musiker",
            0.0F, 39000.0F, 1000.0F, 60, "fun",30,50, 60, 60, 0.0F
            ),
            Profession(
                75, "riding", "Ridlärare", "Du jobbar som ridlärare",
                50000.0F, 33000.0F, 950.0F, 60, "fun",30,50, 60, 10, 0.00F
            ),
            Profession(
                76, "architect", "Arkitekt", "Du jobbar som landskapsarkitekt",
            0.0F, 40000.0F, 1200.0F, 60, "fun",30,40, 60, 10, 0.07F
            ),
            Profession(
                77, "programmer", "Programmerar", "Du jobbar som programmerare på ett spelbolag",
                0.0F, 58000.0F, 1700.0F, 58, "fun",30,10, 60, 60, 0.07F
            )
        )
    }

    fun showNewProfession(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        val message = Message(
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

        val message = Message(
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
        val message: Message

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
}