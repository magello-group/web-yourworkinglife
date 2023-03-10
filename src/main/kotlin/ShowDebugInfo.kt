import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowDebugInfoProps : Props {
    var selectedDebugMessage: String
    var selectedStyle: Style
    var selectedLife: Life
    var selectedPerson: Person
    var selectedEvent: Event
    var selectedProfession: Profession
    var selectedMessage: Message
    var selectedMessageList: List<Message>
    var selectedHistoryMessages: List<Message>
    var selectedTopPX: Int
    var selectedlastDisplayedMessageId: Int
    var selectedMessageIndex: Int
    var selectedMaxMessages: Int
}

val ShowDebugInfo = FC<ShowDebugInfoProps> { props ->
    when (props.selectedDebugMessage) {
        "steg1" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 450.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 1 life.age: ${props.selectedLife.age} boende:${props.selectedPerson.isAccommodation} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg2" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 500.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 2 life.age: ${props.selectedLife.age} event: ${props.selectedEvent.objectType} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg3" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 800.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 3 life.age: ${props.selectedLife.age} pension: ${props.selectedProfession.pensionAge} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg4" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 850.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 4 age life.age: ${props.selectedLife.age} pension: ${props.selectedLife.person.pensionAge} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg5" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 900.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        5,
                        "Nu startar vi steg 5 message: ${props.selectedMessage.messageText} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg6" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = props.selectedTopPX.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        6,
                        "Steg 6 LastmessageId: ${props.selectedlastDisplayedMessageId} index: ${props.selectedMessageIndex}  ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg7" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = props.selectedTopPX.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        6,
                        "Steg 7 QuestionId: ${props.selectedLife.questionMessageId} employedid: ${props.selectedLife.professionMessageId} ",
                        "",
                        ""
                    )
                }

                ShowMessage {
                    selectedMessage = Message(
                        7,
                        "isQuestion: ${props.selectedLife.isQuestion}  isNewProfession: ${props.selectedLife.isNewProfession} ",
                        "",
                        ""
                    )
                }

                ShowMessage {
                    selectedMessage = Message(
                        7,
                        "messageList: ${props.selectedMessageList.size}  historyMessages: ${props.selectedHistoryMessages.size} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg8" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = props.selectedTopPX.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        6,
                        "steg 8 first message: ${props.selectedMessage.id} >=  ${props.selectedMaxMessages} ",
                        "",
                        ""
                    )
                }
            }
        }
        "steg9" -> {
            div {
                css {
                    display = Display.block
                    position = Position.absolute
                    top = props.selectedTopPX.px
                    left = 50.px
                    color = NamedColor.black
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                ShowMessage {
                    selectedMessage = Message(
                        7,
                        "Steg 9 backup: ${props.selectedMessageList.size}  historyMessages: ${props.selectedHistoryMessages.size} ",
                        "",
                        ""
                    )
                }
            }
        }
    }
}