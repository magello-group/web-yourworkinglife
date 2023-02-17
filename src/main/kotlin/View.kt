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
        val depressedQuestions: List<Question> = question.getQuestionList("depressed")

        return listOf(
            View(0, "init", emptyList(), "Ditt arbetsliv börjar här... gör dig redo:","Nästa steg", "action"),
            View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "Starta arbetslivet", "start"),
            View(2, "start", emptyList(),"Ditt arbetsliv har startat!", "Gå vidare i arbetslivet", "reload"),
            View(3, "question", depressedQuestions,"Du svarar:", "Gå vidare i arbetslivet", "reload"),
            View(4, "reload", emptyList(),"Mitt i livet", "Traska på", "pension"),
            View(5, "pension", emptyList(),"Nu startar pensionen", "Pensionär", "slut")
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
            "question" -> {
                view = this.getViewList()[3]
            }
            "reload" -> {
                view = this.getViewList()[4]
            }
            "pension" -> {
                view = this.getViewList()[5]
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
            "question" -> {
                view = this.getViewList()[3]
            }
            "reload" -> {
                view = this.getViewList()[4]
            }
            "pension" -> {
                view = this.getViewList()[5]
            }
        }

        return view
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