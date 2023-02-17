import kotlinx.serialization.Serializable

@Serializable
data class View(
    val id: Int,
    val viewType: String = "init",
    val questions: List<Question> = emptyList(),
    val title: String = "",
    val buttonText: String = "",
    val nextViewType: String = "action"
) {

    fun getViewList(): List<View> {
        val question: Question = Question(0)
        val unionQuestions: List<Question> = question.getQuestionList("union")
        val goalQuestions: List<Question> = question.getQuestionList("goal")
        val luckQuestions: List<Question> = question.getQuestionList("luck")
        val professionQuestions: List<Question> = question.getQuestionList("profession")

        return listOf(
            View(0, "init", emptyList(), "Ditt arbetsliv börjar här... gör dig redo:","Nästa steg", "action"),
            View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "Starta arbetslivet", "profession"),
            View(2, "start", emptyList(),"Ditt arbetsliv har startat!", "Gå vidare i arbetslivet", "reload"),
            View(3, "luck", luckQuestions,"Vad gör dig lycklig:", "Gå vidare i arbetslivet", "reload"),
            View(4, "profession", professionQuestions,"Vilket yrke väljer du:", "Gå vidare i arbetslivet", "start"),
            View(5, "reload", emptyList(),"Mitt i livet", "Traska på", "pension"),
            View(6, "pension", emptyList(),"Nu startar pensionen", "Pensionär", "slut")
        )
    }
    fun getNextView(): View {
        var view: View = View(0)

        when(this.nextViewType) {
            "init" -> {
                view = this.getViewList()[0]
            }
            "action" -> {
                view = this.getViewList()[1]
            }
            "start" -> {
                view = this.getViewList()[2]
            }
            "luck" -> {
                view = this.getViewList()[3]
            }
            "profession" -> {
                view = this.getViewList()[4]
            }
            "reload" -> {
                view = this.getViewList()[5]
            }
            "pension" -> {
                view = this.getViewList()[6]
            }
        }

        return view
    }

    fun getNewView(viewType: String): View {
        var view: View = View(0)

        when(viewType) {
            "init" -> {
                view = this.getViewList()[0]
            }
            "action" -> {
                view = this.getViewList()[1]
            }
            "start" -> {
                view = this.getViewList()[2]
            }
            "luck" -> {
                view = this.getViewList()[3]
            }
            "profession" -> {
                view = this.getViewList()[4]
            }
            "reload" -> {
                view = this.getViewList()[5]
            }
            "pension" -> {
                view = this.getViewList()[6]
            }
        }

        return view
    }

    fun getQuestions(objectType: String): List<Question> {
        var questionList: List<Question> = emptyList()

        for (question in this.questions) {
            if (question.objectType == objectType) {
                questionList = questionList.plus(question)
            }
        }

        return questionList
    }

    fun registerView()
    {
        //Insert in database
    }

    fun updateView()
    {
        //Update in database
    }

    fun getView(){
        //Select person status
    }
}