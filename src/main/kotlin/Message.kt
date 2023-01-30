import kotlinx.serialization.Serializable

@Serializable
data class Message (
    val id: Int,
    val messageText: String = "",
    val objectType: String = "",
    val eventType: String =""
)
