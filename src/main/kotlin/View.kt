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
        var question: Question = Question(0)
        var unionQuestions: List<Question> = question.getQuestionList("union")
        var goalQuestions: List<Question> = question.getQuestionList("goal")

        return listOf(
            View(0, "init", unionQuestions, "Ditt arbetsliv börjar här... gör dig redo:","Nästa steg", "action"),
            View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "Starta arbetslivet", "start"),
            View(2, "start", emptyList(),"Ditt arbetsliv har startat!", "Gå vidare i arbetslivet", "reload"),
            View(3, "reload", emptyList(),"Nu startar pensionen", "Starta om arbetslivet", "init"))
    }
    fun getNextView(currentView: View): View {
        var view: View = View(0)

        when(currentView.nextViewType) {
            "init" -> {
                view = currentView.getViewList()[0]
            }
            "action" -> {
                view = currentView.getViewList()[1]
            }
            "start" -> {
                view = currentView.getViewList()[2]
            }
            "reload" -> {
                view = currentView.getViewList()[3]
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