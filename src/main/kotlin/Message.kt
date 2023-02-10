import kotlinx.serialization.Serializable

@Serializable
data class Message (
    val id: Int,
    val messageText: String = "",
    val color: String = "",
    val animation: String =""
) {
    var status: Status = Status(id)
}
