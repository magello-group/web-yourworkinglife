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
import react.useState
import kotlin.random.Random

external interface ActionListProps : Props {
    var actions: List<Question>
    var selectedAction: Question?
    var onSelectAction: (Question) -> Unit

    var allProfessions: List<Profession>
    var workingProfession: Profession
    var onSelectProfession: (Profession) -> Unit

    var actionAge: String
}

val ActionList = FC<ActionListProps> { props ->
    var selectedProfessions: List<Profession> = emptyList()
    val randomValues = List(10) { Random.nextInt(0, 100) }

    if (props.workingProfession.id == 999) {
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

                    for (question in props.actions) {
                        if (question == props.selectedAction) {

                            for ((professionIndex, profession) in props.allProfessions.withIndex()) {
                                if (question.objectType == profession.objectType) {
                                    if (randomValues[0] < 50 && professionIndex % 2 == 0)
                                        selectedProfessions = selectedProfessions.plus(profession)
                                    else if (randomValues[0] >= 50 && professionIndex % 2 != 0)
                                        selectedProfessions = selectedProfessions.plus(profession)
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
                    +" Start arbetslivet"
                    +" ▶"
                }
            }

            ShowAction {
                actualProfession = selectedProfessions[0]
                actualAge = props.actionAge
            }
        }
    } else {
        StartWorkingLife {
            selectedProfession = props.workingProfession
            selectedAge = props.actionAge
        }
    }
}