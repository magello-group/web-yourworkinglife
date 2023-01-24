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

    fun getViewList(viewType: String): View {
        var view: View = View(0)
        var question: Question = Question(0)
        var unionQuestions: List<Question> = question.getQuestionList("union")
        var goalQuestions: List<Question> = question.getQuestionList("goal")

        when(viewType) {
            "init" -> {
                view =  View(0, "init", unionQuestions, "Ditt arbetsliv börjar här... gör dig redo:","Nästa steg", "action")
            }
            "action" -> {
                view = View(1, "action", goalQuestions,"Vilket mål har du med arbetslivet?", "", "start")
            }
            "start" -> {
                view = View(2, "start", goalQuestions,"Vilket mål har du med arbetslivet?", "Starta arbetslivet", "start")
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