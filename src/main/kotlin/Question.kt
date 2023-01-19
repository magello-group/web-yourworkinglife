import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val id: Int,
    val questionText: String = "",
    val objectText: String = "",
    val objectType: String ="",
    val nextViewId: Int = 0
) {

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