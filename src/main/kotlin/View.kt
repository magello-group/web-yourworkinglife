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
        val question = Question(0)
        //val unionQuestions: List<Question> = question.getQuestionList("union")
        val goalQuestions: List<Question> = question.getQuestionList("goal")
        val luckQuestions: List<Question> = question.getQuestionList("luck")
        val professionQuestions: List<Question> = question.getQuestionList("profession")
        val houseQuestions: List<Question> = question.getQuestionList("house")

        return listOf(
            View(0, "init", emptyList(), "Ditt arbetsliv börjar här:","Nästa steg", "action"),
            View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "Välj yrke", "profession"),
            View(2, "start", emptyList(),"Ditt arbetsliv har startat!", "Gå vidare i arbetslivet", "reload"),
            View(3, "luck", luckQuestions,"Vad gör dig lycklig?", "Starta", "start"),
            View(4, "profession", professionQuestions,"Vilket yrke väljer du?", "Nästa", "luck"),
            View(5, "reload", emptyList(),"Mitt i livet", "Traska på", "pension"),
            View(6, "pension", emptyList(),"Nu startar pensionen", "Pensionär", "slut"),
            View(7, "question", professionQuestions,"Vilket yrke väljer du?", "Gå vidare till nya jobbet", "reload"),
            View(8, "depressed", luckQuestions,"Vad gör dig lycklig?", "Gå vidare lycklig", "reload"),
            View(9, "house", houseQuestions,"Var vill du bo?", "Gå vidare", "reload")
        )
    }
    fun getNextView(): View {
        var view = View(0)
        val allViews = view.getViewList()

        for (next in allViews) {
            if (this.nextViewType == next.viewType)
                view = next
        }

        return view
    }

    fun getNewView(viewType: String): View {
        var view = View(0)
        val allViews = view.getViewList()

        for (next in allViews) {
            if (viewType == next.viewType)
                view = next
        }

        return view
    }
}