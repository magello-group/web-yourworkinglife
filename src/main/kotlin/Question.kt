import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val id: Int?,
    val questionText: String = "",
    val objectText: String = ""
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