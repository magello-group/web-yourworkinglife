import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val id: Int,
    val eventText: String = "",
    val objectType: String = "",
    val eventType: String =""
)
{
    fun getEvents(): List<Event> {
        var events: List<Event> = listOf(
            Event(0,"får du bonus i form av värdepapper.", "depot", "depot"),
            Event(1,"blir du utbränd.", "burnedout","sick"),
            Event(2,"får du en hjärtattack.", "heartattack","sick"),
            Event(3,"får du en golfboll i huvudet.", "golf","sick"),
            Event(4,"blir du skjuten.", "shot","sick"),
            Event(5,"blir du deprimerad.", "depressed","sick"),
            Event(6,"blir du lycklig.", "luck","luck"),
            Event(7,"blir du varslad.", "unemployed","unemployed"),
            Event(8,"blir du träffad av en Magellit.", "magellit","magellit"),
            Event(9,"får du barn.", "parent","parent"),
            Event(10,"VAB:ar du.", "VAB","VAB")
        )

        return events
    }

}