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
                    Question(2,"Tecknar du tilläggsförsäkring?", "Tilläggsförsäkring", "extrainsurance"),
                    Question(3, "Tecknar du olycksfallsförsäkring?", "Olycksfallsförsäkring", "healthinsurance")
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