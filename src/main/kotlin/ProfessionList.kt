import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.key

external interface ProfessionListProps : Props {
    var selectedView: View
    var selectedProfession: Profession
    var selectedPerson: Person
    var selectedMessages: List<Message>
    var selectedHistory: List<Message>
    var selectedStatus: Status
    var selectedLife: Life
    var selectedEvent: Event
    var selectedStyle: Style

    //var onSelectQuestion: (Profession) -> Unit
    var onSelectQuestion: (Event, View, List<Message>, Profession, Person, List<Message>, Status, Life) -> Unit

    var onSelectProfession: (Event, View, List<Message>, Profession, Person, List<Message>, Status, Life) -> Unit
}

val ProfessionList = FC<ProfessionListProps> { props ->

    val profession = props.selectedProfession
    var selectedProfessions: List<Profession> = emptyList()
    val allProfessions: List<Profession> = profession.getProfessionList(profession.objectType)
    val selectedEvents: List<Event>
    selectedEvents = props.selectedEvent.getEvent("employed")


    div {
        css {
            display = Display.block
            position = Position.absolute
            top = props.selectedStyle.topPXTable01.px
            left = props.selectedStyle.leftPXTable01.px
            fontFamily = FontFamily.cursive
        }

        table {
            css {
                width = 300.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(0.px, LineStyle.solid, NamedColor.white)
                margin = Auto.auto
            }

            tbody {
                css {
                    color = NamedColor.black
                    backgroundColor = NamedColor.transparent
                    textAlign = TextAlign.start
                }

                for (question in allProfessions) {
                    if (question == props.selectedProfession) {
                        selectedProfessions = selectedProfessions.plus(question)
                    }


                    tr {
                        css {
                            fontSize = props.selectedStyle.fontMedium.px
                            borderBottom = Border(0.px, LineStyle.solid, NamedColor.white)
                            hover {
                                backgroundColor = NamedColor.transparent
                            }
                        }

                        for (cell in 1..2) {
                            td {
                                p {
                                    css {
                                        padding = Padding(0.px, 0.px)
                                        height = 10.px
                                    }

                                    if (cell == 1) {
                                        button {

                                            key = question.id.toString()
                                            css {
                                                display = Display.block
                                                border = Border(1.px, LineStyle.solid, NamedColor.black)
                                                height = 25.px
                                                width = 25.px
                                                backgroundColor =
                                                    if (question == props.selectedProfession)
                                                        NamedColor.lightgreen
                                                    else
                                                        NamedColor.white
                                            }
                                            onClick = {
                                                props.onSelectQuestion(
                                                    props.selectedEvent,
                                                    props.selectedView,
                                                    props.selectedMessages,
                                                    question,
                                                    props.selectedPerson,
                                                    props.selectedHistory,
                                                    props.selectedStatus,
                                                    props.selectedLife
                                                )
                                            }
                                            +"▶ "
                                        }
                                    } else if (cell == 2) {
                                        +" ${question.title}"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedProfessions.isNotEmpty() && selectedEvents.isNotEmpty()) {
        p {
            button {

                key = selectedProfessions[0].id.toString()
                css {
                    display = Display.block
                    position = Position.absolute
                    top = props.selectedStyle.topPXbutton01.px
                    left = props.selectedStyle.leftPXbutton01.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                onClick = {
                    selectedEvents[0].isSelected = true
                    props.onSelectProfession(
                        selectedEvents[0],
                        props.selectedView.getNextView(),
                        props.selectedMessages,
                        selectedProfessions[0],
                        props.selectedPerson,
                        props.selectedHistory,
                        props.selectedStatus,
                        props.selectedLife
                    )
                }
                +props.selectedView.buttonText
                +" ▶"
            }
        }

        ShowProfession {
            actualProfession = selectedProfessions[0]
            actualAge = props.selectedPerson.startWorkingAge.toString()
            actualStyle = props.selectedStyle
            actualStartTopPX = props.selectedStyle.topPXProfessionStatus
        }
    }
}

