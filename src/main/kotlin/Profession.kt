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
    var randomUnemployed: Int = 30
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

            HÄR ÄR DE 10 YRKEN SOM HAR HÖGST STATUS JUST NU ENLIGT UNDERSÖKNINGEN:
            Läkare
            VD
            verkställande direktör
            Advokat
            Professor
            Domare
            Ambassadör
            Statsråd
            Forskare
            Pilot
            Ingenjör
         */

        return listOf(
            //Salary
            Profession(
                1, "solo", "Egenföretagare", "Du driver ditt eget företag som ingenjör",
                0.0F, 64800.0F, 2000.0F, 58, "salary",30, 30
            ),
            Profession(
                2, "security", "Säkerhetschef", "Du jobbar som säkerhetschef",
                0.0F, 67500.0F, 2000.0F, 58, "salary", 30,10
            ),
            Profession(
                3, "VD", "Verkställande direktör", "Du jobbar som VD på ett spelbolag",
                0.0F, 163000.0F, 4500.0F, 58, "salary", 30, 10
            ),
            Profession(
                4, "ambassadör", "Ambassadör", "Du jobbar som ambassadör i Tyskland",
                0.0F, 100000.0F, 2800.0F, 58, "salary",30,20
            ),
            Profession(
                4, "chef", "Bankchef", "Du jobbar som chef på en bank",
                0.0F, 88000.0F, 2500.0F, 58, "salary",30,40
            ),
            Profession(
                6, "driftchef", "Driftchef", "Du jobbar som driftchef inom bygg, anläggning och gruva",
                0.0F, 64000.0F, 2000.0F, 58, "salary",30,40
            ),
            Profession(
                7, "advokat", "Advokat", "Du jobbar som Advokat",
                0.0F, 46400.0F, 1400.0F, 58, "salary",30,10
            ),
            Profession(
                8, "läkare", "Läkare", "Du jobbar som Läkare",
                0.0F, 58000.0F, 1700.0F, 58, "salary",10,10
            ),
            Profession(
                9, "Programmerare", "Programmerar", "Du jobbar som programmerare på ett säkerhetsbolag",
                0.0F, 58000.0F, 1700.0F, 58, "salary",30,10
            ),

            //Pension
            Profession(
                10, "professor", "Professor", "Du jobbar på en bank",
                0.0F, 0.0F, 1000.0F, 58, "pension",30,50
            ),
            Profession(
                11, "insurance", "Försäkringsdirektör", "Du jobbar som försäkringsdirektör",
                0.0F, 100000.0F, 2800.0F, 58, "pension",10,50
            ),
            Profession(
                12, "Programmerare", "Programmerar", "Du jobbar som programmerare på en bank",
                0.0F, 40000.0F, 1200.0F, 60, "pension",30,20
            ),

            //Äventyr
            Profession(
                13, "detektiv", "Detektiv", "Du jobbar som detektiv",
                0.0F, 30800.0F, 3000.0F, 50, "adventure",30,50
            ),
            Profession(
                14, "pilote", "Pilot", "Du jobbar på ett flygbolag",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",20,10
            ),
            Profession(
                15, "fireman", "Brandman", "Du jobbar som brandman",
                0.0F, 40500.0F, 1190.0F, 50, "adventure",40,40
            ),
            Profession(
                16, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 68200.0F, 2000.0F, 50, "adventure",40,10
            ),
            Profession(
                17, "kapten", "Kapten", "Du jobbar som kapten på ett fartyg",
                0.0F, 51100.0F, 1500.0F, 50, "adventure",30,30
            ),
            Profession(
                18, "rescue", "Fjällräddare", "Du jobbar som fjällräddare",
                0.0F, 30000.0F, 900.0F, 50, "adventure",20,50
            ),
            Profession(
                19, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                0.0F, 30000.0F, 900.0F, 60, "adventure",40,40
            ),
            Profession(
                20, "Programmerare", "Programmerar", "Du jobbar som programmerare hos underrättelsetjänsten",
                0.0F, 50000.0F, 1500.0F, 60, "adventure",40,10
            ),

            //Semester
            Profession(
                21, "authority", "Statsanställd", "Du jobbar på en myndighet",
                0.0F, 30000.0F, 900.0F, 60, "vacation",30,10
            ),
            Profession(
                22, "travelagent", "Reseledare", "Du jobbar på en resebyrå ",
                0.0F, 30000.0F, 900.0F, 60, "vacation",40,40
            ),
            Profession(
                23, "builder", "Snickare", "Du jobbar som snickare på ett byggbolag",
                0.0F, 35500.0F, 1000.0F, 60, "vacation",40,20
            ),
            Profession(
                24, "teacher", "Lärare", "Du jobbar som lärare",
                0.0F, 50800.0F, 1500.0F, 65, "vacation",40,40
            ),
            Profession(
                25, "Programmerare", "Programmerar", "Du jobbar som programmerare på en myndighet",
                0.0F, 35000.0F, 900.0F, 60, "vacation",30,10
            ),

            //Samhället
            Profession(
                26, "teacher", "Lärare", "Du jobbar som lärare",
                0.0F, 50800.0F, 1500.0F, 65, "family",40,40
            ),
            Profession(
                27, "police", "Polis", "Du jobbar hos polisen",
                0.0F, 0.0F, 1190.0F, 50, "family",40,10
            ),
            Profession(
                28, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "family",20,20
            ),
            Profession(
                29, "Programmerare", "Programmerar", "Du jobbar som programmerare hos polisen",
                0.0F, 39000.0F, 1000.0F, 60, "family",30,10
            ),
            Profession(
                30, "Socialassistent", "Socialassistent", "Du jobbar som socialassistent",
                0.0F, 32000.0F, 900.0F, 60, "family",40,20
            ),

            //Chilla
            Profession(
                31, "writer", "Trädgårdsmästare", "Du jobbar som trädgårdsmästare!",
                0.0F, 0.0F, 800.0F, 75, "chilla",20,50
            ),
            Profession(
                32, "yoga", "Yogainstruktör", "Du jobbar som yogainstruktör",
                0.0F, 0.0F, 800.0F, 75, "chilla", 20,50
            ),
            Profession(
                33, "bonde", "Jordbrukare", "Du jobbar som jordbrukare",
                0.0F, 25000.0F, 700.0F, 60, "chilla",20,20
            ),
            Profession(
                34, "painter", "Konstnär", "Du jobbar som konstnär",
                0.0F, 32000.0F, 900.0F, 60, "chilla",20,50
            ),
            Profession(
                35, "musiker", "Musiker", "Du jobbar som musiker",
                0.0F, 39000.0F, 1000.0F, 60, "chilla", 20,50
            ),
            Profession(
                36, "writer", "Författare", "Du jobbar som författare",
                0.0F, 39000.0F, 1000.0F, 60, "chilla",20,50
            ),
            Profession(
                37, "Programmerare", "Programmerar", "Du jobbar som programmerare på en högskola",
                0.0F, 40000.0F, 1000.0F, 60, "chilla",30,10
            ),

            //Kul
            Profession(
                38, "artist", "Cirkusprinsessa", "Du jobbar som cirkusartist!",
                0.0F, 30000.0F, 900.0F, 75, "fun",40,40
            ),
            Profession(
                39, "pt", "Privat tränare", "Du jobbar som PT på ett gym",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50
            ),
            Profession(
                40, "actor", "Skådespelare", "Du jobbar som skådespelare",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50
            ),
            Profession(
                41, "comedian", "Komiker", "Du jobbar som komiker",
                0.0F, 30000.0F, 900.0F, 75, "fun",30,50
            ),
            Profession(
                42, "musiker", "Musiker", "Du jobbar som musiker",
            0.0F, 39000.0F, 1000.0F, 60, "fun",30,50
            ),
            Profession(
                43, "riding", "Ridlärare", "Du jobbar som ridlärare",
                0.0F, 33000.0F, 950.0F, 60, "fun",30,50
            ),
            Profession(
                44, "architect", "Arkitekt", "Du jobbar som landskapsarkitekt",
            0.0F, 40000.0F, 1200.0F, 60, "fun",30,40
            ),
            Profession(
                45, "Programmerare", "Programmerar", "Du jobbar som programmerare på ett spelbolag",
                0.0F, 58000.0F, 1700.0F, 58, "fun",30,10
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