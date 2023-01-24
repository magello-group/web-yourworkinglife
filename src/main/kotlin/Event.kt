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
            Event(0,"Du får bonus i form av värdepapper", "depot", "depot"),
            Event(1,"Du blir utbränd", "burnedout","sick"),
            Event(2,"Du får en hjärtattack", "heartattack","sick"),
            Event(3,"Du får en golfboll i huvudet", "golf","sick"),
            Event(4,"Du blir skjuten", "shot","sick"),
            Event(5,"Du blir deprimerad", "depressed","sick"),
            Event(6,"Du blir lycklig", "luck","luck"),
            Event(7,"Du blir varslad", "unemployed","unemployed"),
            Event(8,"Du blir träffad av en Magellit", "maagellit","magellit"),
            Event(9,"Du får barn", "parent","parent"),
            Event(9,"Du VAB:ar", "VAB","VAB")
        )

        return events
    }

}