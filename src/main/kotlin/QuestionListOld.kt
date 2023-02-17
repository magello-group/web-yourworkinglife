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
    var selectedView: View
    var selectedQuestion: Question?
    var clickedQuestions: List<Question>

    var onSelectQuestion: (Question) -> Unit
}

val QuestionList = FC<QuestionListProps> { props ->
    val questions: List<Question> = props.selectedView.questions

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 220.px
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

                for (newQuestion in questions) {
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

                                            key = newQuestion.id.toString()
                                            css {
                                                display = Display.block
                                                border = Border(2.px, LineStyle.solid, NamedColor.black)
                                                height = 25.px
                                                width = 25.px
                                                backgroundColor =
                                                    if (newQuestion == props.selectedQuestion) {
                                                        if (props.clickedQuestions.contains(newQuestion))
                                                            NamedColor.lightgreen
                                                        else
                                                            NamedColor.white
                                                    } else if (props.clickedQuestions.isEmpty())
                                                        NamedColor.white
                                                    else if (props.clickedQuestions.contains(newQuestion))
                                                        NamedColor.lightgreen
                                                    else NamedColor.white
                                            }
                                            onClick = {
                                                props.onSelectQuestion(newQuestion)
                                            }
                                            if (newQuestion == props.selectedQuestion) {
                                                if (props.clickedQuestions.contains(newQuestion))
                                                    +"✔"
                                                else
                                                    +" "
                                            } else if (props.clickedQuestions.isEmpty())
                                                +" "
                                            else if (props.clickedQuestions.contains(newQuestion))
                                                +"✔"
                                            else
                                                +" "
                                        }
                                    } else {
                                        +" "
                                        +newQuestion.questionText
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