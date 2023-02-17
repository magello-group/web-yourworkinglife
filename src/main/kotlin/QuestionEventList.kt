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

external interface QuestionEventListProps : Props {
    var selectedView: View
    var selectedQuestion: Question?
    var onSelectQuestion: (Question) -> Unit

    var selectedProfession: Profession
    var selectedPerson: Person
    var selectedMessages: List<Message>
    var selectedLife: Life

    var onSelectEvent: (Event, View, List<Message>, Profession, Person, Life) -> Unit
}

val QuestionEventList = FC<QuestionEventListProps> { props ->
    var actions: List<Question> = props.selectedView.questions
    var selectedEvents: List<Event> = emptyList()
    var allEvents: List<Event>
    val event = Event(0)

    var selectedProfessions: List<Profession> = emptyList()
    var allProfessions: List<Profession>
    val profession = props.selectedProfession

    if (props.selectedView.viewType == "profession") {
        actions = props.selectedView.getQuestions(profession.objectType)
    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 90.px
            left = 10.px
            fontFamily = FontFamily.cursive
        }

        table {
            css {
                width = 200.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(0.px, LineStyle.solid, NamedColor.white)
                margin = Auto.auto

            }

            tbody {
                css {
                    color = NamedColor.black
                    backgroundColor = NamedColor.white
                    textAlign = TextAlign.start
                }

                for (question in actions) {
                    if (question == props.selectedQuestion) {
                        if (props.selectedView.viewType == "profession") {
                            allProfessions = profession.getProfessionList(question.objectType)
                            for (action in allProfessions) {
                                if (action.professionType == question.objectText) {
                                    selectedProfessions = selectedProfessions.plus(action)
                                }
                            }
                        } else {
                            allEvents = event.getEventList(question.objectType)
                            for (action in allEvents) {
                                if (action.objectType == question.objectText) {
                                    selectedEvents = selectedEvents.plus(action)
                                }
                            }
                        }
                    }

                    tr {
                        css {
                            fontSize = 18.px
                            cursor = Cursor.pointer
                            borderBottom = Border(1.px, LineStyle.solid, NamedColor.white)
                            hover {
                                backgroundColor = NamedColor.lightgray
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
                                                    if (question == props.selectedQuestion)
                                                        NamedColor.lightgreen
                                                    else
                                                        NamedColor.white
                                            }
                                            onClick = {
                                                props.onSelectQuestion(question)
                                            }
                                            +"▶ "
                                        }
                                    } else {
                                        +" "
                                        +question.questionText
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedEvents.isNotEmpty()) {
        p {
            button {

                key = selectedEvents[0].id.toString()
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 10.px
                    left = 10.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                onClick = {
                    selectedEvents[0].isSelected = true
                    props.onSelectEvent(
                        selectedEvents[0],
                        props.selectedView.getNextView(),
                        props.selectedMessages,
                        props.selectedProfession,
                        props.selectedPerson,
                        props.selectedLife
                    )
                }
                +props.selectedView.buttonText
                +" ▶"
            }
        }
    } else if (selectedProfessions.isNotEmpty()) {
        p {
            button {

                key = selectedProfessions[0].id.toString()
                css {
                    display = Display.block
                    position = Position.absolute
                    top = 10.px
                    left = 10.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                onClick = {
                    props.onSelectEvent(
                        event,
                        props.selectedView.getNextView(),
                        props.selectedMessages,
                        selectedProfessions[0],
                        props.selectedPerson,
                        props.selectedLife
                    )
                }
                +props.selectedView.buttonText
                +" ▶"
            }
        }
    }
}

