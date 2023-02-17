import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val id: Int,
    val eventText: String = "",
    val objectType: String = "",
    val eventType: String ="",
    var isSelected: Boolean = false
)
{
    fun getEvents(): List<Event> {
        return listOf(
            Event(0, "du får bonus i form av värdepapper 🤑", "depot", "depot"),
            Event(1, "du blir utbränd 😔", "burnedout", "sick"),
            Event(2, "du får en hjärtattack 😬", "heartattack", "sick"),
            Event(3, "du får en golfboll i huvudet 😨", "golf", "sick"),
            Event(4, "du blir skjuten 😱", "shot", "sick"),
            Event(5, "du får en pandemisk sjukdom 😱", "pandemi", "sick"),
            Event(6, "du blir deprimerad.", "depressed", "sick"),

            Event(7, "du skaffar katt och livet känns härligt 🐱😍", "cat", "luck"),
            Event(8, "du börjar träna och du känner dig stark 💪 och lycklig", "strong", "luck"),
            Event(9, "du finner en vän att prata med och livet vänder 🤗", "friend", "luck"),
            Event(10, "du drar iväg på en lång vandring själv 🚶 och du känner dig fri 😍", "alone", "luck"),
            Event(11, "du skaffar hund och du känner dig både lycklig och stark 🦖😍", "dog", "luck"),
            Event(12, "du drar ut och fiskar 🐬 och känner hur du fylls med lycka 😍", "fish", "luck"),
            Event(13, "du festar järnet och känner hur du fylls med glädje 🤸", "party", "luck"),
            Event(14, "du skaffar häst och du drar iväg i en härlig galopp 🦄", "horse", "luck"),
            Event(15, "du älskar pengar och dyker gärna ned i ett kassavalv 💰", "money", "luck"),
            Event(16, "du köper en bil och du känner dig fri 🚗", "car", "luck"),
            Event(17, "du köper en motorcykel och älskar mullret när du drar iväg 🛵", "bike", "luck"),

            Event(18, "du blir varslad 😢", "unemployed", "unemployed"),
            Event(19, "du byter jobb.", "employed", "unemployed"),
            Event(20, "du blir träffad av en Magellit.", "magellit", "magellit"),
            Event(21, "du får barn 👶", "parent", "parent"),
            Event(22, "du VAB:ar.", "vab", "vab")
        )
    }

    fun getCostEvents(): List<Event> {
        return listOf(
            Event(0, "Kul! du köper ett hus.","house","home"),
            Event(1, "Kul! du köper en bostadsrätt.","department","home"),
            Event(2, "Kul! du skaffar dig en hyresrätt.","hire","home"),
            Event(3, "Lågkonjuktur! ditt sparande rasar i värde ","depot","accident"),
            Event(4, "Lågkonjuktur! din hyra höjs på ditt boende ","home","accident"),
            Event(5, "Lågkonjuktur! din räntan höjs på ditt lån ","loan","accident"),
            Event(6, "Pandemi! risk att du blir sjuk","sick","accident"),
            Event(7, "Högkonjuktur! ditt sparande ökar i värde ","depot","happening"),
            Event(8, "Högkonjuktur! värdet på din bostad höjs ","home","happening"),
            Event(9, "Högkonjuktur! räntan sänks på ditt lån ","loan","happening")
        )
    }

    fun getEventList(eventType: String): List<Event> {

        val events: List<Event> = this.getEvents()
        var selectedEvents: List<Event> = emptyList()

        for (event in events) {
            if (event.eventType == eventType) {
                selectedEvents = selectedEvents.plus(event)
            }
        }

        return selectedEvents
    }

    fun showEvent(messageList: List<Message>, messageId: Int, messageStart: String, messageEnd: String): List<Message> {
        var storyList = messageList

        storyList = storyList.plus(
            Message(
                messageId,
                messageStart + this.eventText + messageEnd,
                "",
                "blinking"
            )
        )

        return storyList
    }

}