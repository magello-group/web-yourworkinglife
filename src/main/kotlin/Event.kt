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
            Event(0, "du får bonus i form av värdepapper 🤑", "depot", "depot"),
            Event(1, "du blir utbränd 😔", "burnedout", "sick"),
            Event(2, "du får en hjärtattack 😬", "heartattack", "sick"),
            Event(3, "du får en golfboll i huvudet 😨", "golf", "sick"),
            Event(4, "du blir skjuten 😱", "shot", "sick"),
            Event(5, "du blir deprimerad.", "depressed", "sick"),
            Event(6, "du skaffar katt och livet känns härligt 🐱😍", "luck", "luck"),
            Event(7, "du börjar träna och du känner dig stark 🤸", "luck", "luck"),
            Event(8, "du finner en vän att prata med och livet vänder 🤗", "luck", "luck"),
            Event(9, "du skaffar hund och du känner dig både lycklig och stark 🦖", "luck", "luck"),
            Event(10, "du blir varslad 😢", "unemployed", "unemployed"),
            Event(11, "du byter jobb.", "employed", "unemployed"),
            Event(12, "du blir träffad av en Magellit.", "magellit", "magellit"),
            Event(13, "du får barn 👶", "parent", "parent"),
            Event(14, "du VAB:ar.", "vab", "vab")
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