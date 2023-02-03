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
        return listOf(
            Event(0, "du får bonus i form av värdepapper.", "depot", "depot"),
            Event(1, "du blir utbränd.", "burnedout", "sick"),
            Event(2, "du får en hjärtattack.", "heartattack", "sick"),
            Event(3, "du får en golfboll i huvudet.", "golf", "sick"),
            Event(4, "du blir skjuten.", "shot", "sick"),
            Event(5, "du blir deprimerad.", "depressed", "sick"),
            Event(6, "du blir lycklig.", "luck", "luck"),
            Event(7, "du blir varslad.", "unemployed", "unemployed"),
            Event(8, "du byter jobb.", "employed", "unemployed"),
            Event(9, "du blir träffad av en Magellit.", "magellit", "magellit"),
            Event(10, "du får barn.", "parent", "parent"),
            Event(11, "du VAB:ar.", "vab", "vab")
        )
    }

    fun getCostEvents(): List<Event> {
        return listOf(
            Event(0, "Kul! du köper ett hus.","house","home"),
            Event(1, "Kul! du köper en bostadsrätt.","department","home"),
            Event(2, "Kul! du skaffar dig en hyresrätt.","hire","home"),
            Event(3, "Åh nej! ditt sparande rasar i värde ","depot","accident"),
            Event(4, "Åh nej! din hyra höjs på ditt boende ","home","accident"),
            Event(5, "Åh nej! din räntan höjs på ditt lån ","loan","accident")
        )
    }

}