//package team.karakum.components

import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2

external interface ViewTitleProps : Props {
    var titleH1: String
    var titleH2: String
}

val ViewTitle = FC<ViewTitleProps> { props ->

    h1 {
        css {
            display = Display.block
            //backgroundImage = url("background.jpg")
            position = Position.absolute
            top = 10.px
            left = 10.px
            color = NamedColor.black
            fontSize = 26.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }
        +props.titleH1
    }

    h2 {
        css {
            display = Display.block
            //backgroundImage = url("background.jpg")
            position = Position.absolute
            top = 50.px
            left = 10.px
            color = NamedColor.black
            fontSize = 18.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }
        + props.titleH2
    }
/*
    div {
        /*
        button {
            onClick = { createUser(jso { id = "${nextInt()}" }) }

            +"Create New"
        }

         */
        css {
            display = Display.block
            //backgroundImage = url("background.jpg")
            position = Position.absolute
            top = 100.px
            left = 10.px
            fontFamily = FontFamily.cursive
        }

        table {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(2.px, solid, NamedColor.white)
                margin = auto
            }

            thead {
                css {
                    color = NamedColor.white
                    fontSize = 18.px
                    backgroundColor = NamedColor.white
                }

                for (row in 1..1) {
                    tr {
                        for (header in 1 .. 1) {
                            th {
                                css {
                                    fontWeight = FontWeight.normal
                                    padding = Padding(4.px, 12.px)
                                    borderRight = Border(1.px, solid, NamedColor.white)
                                    borderBottom = Border(1.px, solid, NamedColor.white)
                                    color = NamedColor.black

                                    lastChild {
                                        borderRight = none
                                    }
                                }

                                + props.title
                            }
                        }
                    }
                }
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
                            borderBottom = Border(1.px, solid, NamedColor.white)
                            hover {
                                backgroundColor = NamedColor.lightgray
                            }
                        }

                        //onClick = { "klick" }

                        for (cell in 1..2) {
                            td {
                                css {
                                    padding = Padding(10.px, 12.px)
                                }

                                if (cell.equals(1))    {
                                    button {

                                        onClick = {
                                            props.onSelectQuestion(question)
                                        }
                                        if (question == props.selectedQuestion) {
                                            css {
                                                display = Display.block
                                                backgroundColor = NamedColor.lightgreen
                                            }
                                            +"✔ "
                                        } else {
                                            css {
                                                display = Display.block
                                                backgroundColor = NamedColor.white
                                            }
                                            +" "
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

 */
}
