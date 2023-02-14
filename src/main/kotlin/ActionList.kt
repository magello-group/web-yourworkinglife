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
import kotlin.random.Random

external interface ActionListProps : Props {
    var selectedView: View
    var selectedAction: Question?
    var onSelectAction: (Question) -> Unit

    var workingProfession: Profession
    var onSelectProfession: (Profession) -> Unit

    var workingPerson: Person
}

val ActionList = FC<ActionListProps> { props ->
    val actions: List<Question> = props.selectedView.questions
    var selectedProfessions: List<Profession> = emptyList()
    var randomValues: List<Int>
    var allProfessions: List<Profession>
    val profession = Profession(0)

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
                width = 600.px
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
                    if (question == props.selectedAction) {
                        allProfessions = profession.getProfessionList(question.objectType)
                        randomValues = List(1) { Random.nextInt(0, allProfessions.size) }

                        selectedProfessions = selectedProfessions.plus(allProfessions[randomValues[0]])
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
                                                    if (question == props.selectedAction)
                                                        NamedColor.lightgreen
                                                    else
                                                        NamedColor.white
                                            }
                                            onClick = {
                                                props.onSelectAction(question)
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

    if (selectedProfessions.isNotEmpty()) {
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
                    props.onSelectProfession(selectedProfessions[0])
                }
                +props.selectedView.buttonText
                +" ▶"
            }
        }

        ShowAction {
            actualProfession = selectedProfessions[0]
            actualAge = props.workingPerson.startWorkingAge.toString()
        }
    }
}
