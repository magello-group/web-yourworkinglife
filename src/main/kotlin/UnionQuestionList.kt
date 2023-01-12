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

external interface QuestionListProps : Props {
    var questions: List<Question>
    var selectedQuestion: Question?
    var onSelectQuestion: (Question) -> Unit
}

val QuestionList = FC<QuestionListProps> { props ->

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 170.px
            left = 10.px
            fontFamily = FontFamily.cursive
        }

        table {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(1.px, LineStyle.solid, NamedColor.white)
                margin = Auto.auto
            }

            tbody {
                css {
                    color = NamedColor.black
                    backgroundColor = NamedColor.white
                    textAlign = TextAlign.start
                }

                for (question in props.questions) {
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
                                    }


                                    if (cell.equals(1)) {
                                        button {

                                            key = question.id.toString()
                                            css {
                                                display = Display.block
                                                backgroundColor = if (question == props.selectedQuestion)
                                                    NamedColor.lightgreen else NamedColor.white
                                            }
                                            onClick = {
                                                props.onSelectQuestion(question)
                                            }
                                            if (question == props.selectedQuestion) {
                                                +"✔ "
                                            } else {
                                                +"   "
                                            }
                                        }
                                    } else {
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
}