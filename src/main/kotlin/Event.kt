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
            Event(0, "får du bonus i form av värdepapper.", "depot", "depot"),
            Event(1, "blir du utbränd.", "burnedout", "sick"),
            Event(2, "får du en hjärtattack.", "heartattack", "sick"),
            Event(3, "får du en golfboll i huvudet.", "golf", "sick"),
            Event(4, "blir du skjuten.", "shot", "sick"),
            Event(5, "blir du deprimerad.", "depressed", "sick"),
            Event(6, "blir du lycklig.", "luck", "luck"),
            Event(7, "blir du varslad.", "unemployed", "unemployed"),
            Event(8, "du byter jobb.", "employed", "unemployed"),
            Event(9, "blir du träffad av en Magellit.", "magellit", "magellit"),
            Event(10, "får du barn.", "parent", "parent"),
            Event(11, "VAB:ar du.", "vab", "vab")
        )
    }

    fun getCostEvents(): List<Event> {
        return listOf(
            Event(0, "köper du ett hus.","house","home"),
            Event(1, "köper du en bostadsrätt.","department","home"),
            Event(2, "får du en hyresrätt.","hire","home"),
            Event(3, "rasar ditt sparande i värde.","depot","accident"),
            Event(4, "höjs hyran på ditt boende.","home","accident"),
            Event(5, "höjs räntan på ditt lån.","loan","accident")
        )
    }

}