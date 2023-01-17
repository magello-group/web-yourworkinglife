import kotlinx.serialization.Serializable

@Serializable
data class View(
    val id: Int,
    val questionType: String = "init",
    val questions: List<Question>,
    val title: String = "",
    val buttonText: String = "",
    val nextViewId: Int
) {

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