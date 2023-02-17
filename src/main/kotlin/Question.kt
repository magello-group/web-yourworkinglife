import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val id: Int,
    val questionText: String = "",
    val objectText: String = "",
    val objectType: String =""
) {

    fun getQuestionList(questionType: String): List<Question> {
        var questions: List<Question> = emptyList()

        when(questionType) {
            "input" -> {
                questions = listOf(
                    Question(0,"Vad heter du?", "Namn", "name"),
                    Question(1,"I vilken ålder börjar du jobba?", "Ålder","age"),
                    Question(2,"Hur mycket av lönen sparar du till pension i procent?", "Pension","pension")

                )
            }
            "union" -> {
                questions = listOf(
                    Question(0,"Går du med i a-kassan?", "A-kassa", "akassa"),
                    Question(1,"Går du med i facket och får inkomstförsäkring?", "Inkomstförsäkring", "incomeinsurance"),
                    Question(2,"Tecknar du tilläggsförsäkring?", "Tilläggsförsäkring", "extrainsurance")
                   // Question(3, "Tecknar du olycksfallsförsäkring?", "Olycksfallsförsäkring", "healthinsurance")
                )
            }

            "goal" -> {
                questions = listOf(
                    Question(0,"Maxa spänningen", "spänning", "adventure"),
                    Question(1, "Göra samhällsnytta och skillnad", "familj", "family",),
                    Question(2, "Maxa semesterdagarna", "semester", "vacation"),
                    Question(3, "Chilla", "chilla", "chilla"),
                    Question(4,"Maxa lönen", "lön", "salary"),
                    Question(5, "Maxa pensionen", "pension", "pension"),
                    Question(6, "Bara ha kul", "kul", "fun")
                )
            }

            "profession" -> {
                questions = listOf(
                    Question(0,"Egenföretagare", "solo", "salary"),
                    Question(1, "Säkerhetsspecialist", "security", "salary",),
                    Question(2, "VD", "VD", "salary"),
                    Question(3, "Banktjänsteman", "bank", "pension"),
                    Question(4,"Försäkringsagent", "insurance", "pension"),
                    Question(4,"Hemlig agent", "agent", "adventure"),
                    Question(5,"Pilot","pilote", "adventure"),
                    Question(6,"Brandman", "fireman", "adventure"),
                    Question(7,"Statsanställd", "authority", "vacation"),
                    Question(8,"Reseledare", "travelagent", "vacation"),
                    Question(9,"Snickare", "builder", "vacation"),
                    Question(10,"Förskolelärare", "teacher", "family"),
                    Question(8,"Polis", "police", "family"),
                    Question(9,"Rektor", "rektor", "family"),
                    Question(10,"Trädgårdsmästare", "writer", "chilla"),
                    Question(8,"Yogainstruktör", "yoga", "chilla"),
                    Question(9,"Cirkusprinsessa", "artist", "fun"),
                    Question(10,"Komiker", "comedian", "fun")
                )
            }

            "luck" -> {
                questions = listOf(
                    Question(0,"Katter🐱😍", "cat", "luck"),
                    Question(1, "Träning 💪", "strong", "luck",),
                    Question(2, "Vänner 🤗", "friend", "luck"),
                    Question(3, "Hundar 🦖", "dog", "luck"),
                    Question(4,"Vandring 🚶", "alone", "luck"),
                    Question(5,"Att fiska 🐬","fish", "luck"),
                    Question(6,"Fester och dans 🤸", "party", "luck"),
                    Question(7,"Pengar 💰", "money", "luck"),
                    Question(8,"Hästar 🦄 ", "horse", "luck"),
                    Question(9,"Motorcyklar 🛵", "bike", "luck"),
                    Question(10,"Bilar 🚗", "car", "luck")
                )
            }
        }
        return questions
    }

    fun registerQuestion()
    {
        //Insert in database
    }

    fun updateQuestion()
    {
        //Update in database
    }

    fun getQuestion(){
        //Select person status
    }
}