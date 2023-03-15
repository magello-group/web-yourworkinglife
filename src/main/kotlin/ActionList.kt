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

    //var workingProfession: Profession
    var onSelectGoal: (Profession) -> Unit

    var workingPerson: Person
    var selectedStyle: Style
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
            top = props.selectedStyle.topPXTable01.px
            left = props.selectedStyle.leftPXTable01.px
            fontFamily = FontFamily.cursive
        }

        table {
            css {
                width = 1000.px
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

                for (question in actions) {
                    if (question == props.selectedAction) {
                        allProfessions = profession.getProfessionList(question.objectType)
                        randomValues = List(1) { Random.nextInt(0, allProfessions.size) }

                        selectedProfessions = selectedProfessions.plus(allProfessions[randomValues[0]])
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
                    top = props.selectedStyle.topPXbutton01.px
                    left = props.selectedStyle.leftPXbutton01.px

                    color = NamedColor.green
                    borderColor = NamedColor.white
                    fontSize = props.selectedStyle.fontMedium.px
                    backgroundColor = NamedColor.white
                    fontFamily = FontFamily.cursive
                }

                onClick = {
                    props.onSelectGoal(selectedProfessions[0])
                }
                +props.selectedView.buttonText
                +" ▶"
            }
        }
    }
}
