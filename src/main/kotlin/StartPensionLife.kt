import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key
import kotlin.Float
import kotlin.random.Random
import kotlin.collections.List

external interface StartPensionLifeProps : Props {
    var selectedView: View
    var selectedPerson: Person
    var selectedMessages: List<Message>

    var onSelectPension: (View, List<Message>, Person) -> Unit
}

val StartPensionLife = FC<StartPensionLifeProps> { props ->

    // Init person

    val reloadView: View = props.selectedView.getNextView(props.selectedView)
    val person = props.selectedPerson

    //Init story
    var messageList = props.selectedMessages
    var leftMessages: List<Message> = emptyList()
    val maxMessages = 6

    if (messageList.isNotEmpty()) {

        div {
            val blinking: AnimationName = keyframes {
                0.pct {
                    color = NamedColor.darkgoldenrod
                }
                10.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                20.pct {
                    color = NamedColor.darkgoldenrod
                }
                30.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                40.pct {
                    color = NamedColor.darkgoldenrod
                }
                50.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                60.pct {
                    color = NamedColor.darkgoldenrod
                }
                70.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                80.pct {
                    color = NamedColor.darkgoldenrod
                }
                90.pct {
                    color = NamedColor.lightgoldenrodyellow
                }
                100.pct {
                    color = NamedColor.darkgoldenrod
                }
            }
            val blinkingRed: AnimationName = keyframes {
                0.pct {
                    color = NamedColor.hotpink
                }
                10.pct {
                    color = NamedColor.lightpink
                }
                20.pct {
                    color = NamedColor.hotpink
                }
                30.pct {
                    color = NamedColor.lightpink
                }
                40.pct {
                    color = NamedColor.hotpink
                }
                50.pct {
                    color = NamedColor.lightpink
                }
                60.pct {
                    color = NamedColor.hotpink
                }
                70.pct {
                    color = NamedColor.lightpink
                }
                80.pct {
                    color = NamedColor.hotpink
                }
                90.pct {
                    color = NamedColor.lightpink
                }
                100.pct {
                    color = NamedColor.hotpink
                }
            }
            css {
                display = Display.block
                position = Position.absolute
                top = 90.px
                left = 10.px

                color = NamedColor.green
                borderColor = NamedColor.white
                fontSize = 18.px
                backgroundColor = NamedColor.white
                fontFamily = FontFamily.cursive
            }

            for ((messageIndex, message) in messageList.withIndex()) {
                if (messageIndex == maxMessages && message.messageText.contains("Det har gått")) {
                    leftMessages = leftMessages.plus(message)
                } else if (messageIndex <= maxMessages) {

                    if (message.animation != "") {
                        when (message.animation) {
                            "blinking" -> {
                                p {

                                    css {
                                        animationDuration = 3.s
                                        animationName = blinking
                                        animationFillMode = AnimationFillMode.both
                                        color = NamedColor.gold
                                    }
                                    +message.messageText
                                }
                            }
                            "blinkingRed" -> {
                                p {

                                    css {
                                        animationDuration = 3.s
                                        animationName = blinkingRed
                                        animationFillMode = AnimationFillMode.both
                                        color = NamedColor.red
                                    }
                                    +message.messageText
                                }
                            }
                        }
                    } else {
                        when (message.color) {
                            "deepskyblue" -> {
                                p {
                                    css {
                                        color = NamedColor.deepskyblue
                                    }
                                    +message.messageText
                                }
                            }
                            "lavender" -> {
                                p {
                                    css {
                                        color = NamedColor.blueviolet
                                    }
                                    +message.messageText
                                }
                            }
                            "grey" -> {
                                p {
                                    css {
                                        color = NamedColor.yellowgreen
                                    }
                                    +message.messageText
                                }
                            }
                            "hotpink" -> {
                                p {
                                    css {
                                        color = NamedColor.hotpink
                                    }
                                    +message.messageText
                                }
                            }
                            "orange" -> {
                                p {
                                    css {
                                        color = NamedColor.orange
                                    }
                                    +message.messageText
                                }
                            }
                            "" -> {
                                p {
                                    css {
                                        color = NamedColor.green
                                    }
                                    +message.messageText
                                }
                            }
                        }
                    }
                } else {
                    leftMessages = leftMessages.plus(message)
                }
            }
        }
/*
        //Show animation
        div {
            when (props.selectedProfession.professionType) {
                "pilote" -> {
                    ShowStreckImage {selectedImage ="streckpilot1400.jpg"}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck005.jpg"
                        selectedImage03 = "streck006.jpg"
                    }
                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 0
                        marginLeftTo = 30
                    }
                }
                "agent" -> {
                    ShowStreckImage {selectedImage ="streckagent1300.jpg"}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }
                    ShowCloud {
                        selectedImage ="regn.png"
                        marginLeftFrom = 0
                        marginLeftTo = 30
                    }
                }
                "police" -> {
                    ShowStreckImage {selectedImage ="streckpolis1300.jpg"}
                    ShowStreck {
                        selectedImage01 = "streck002.jpg"
                        selectedImage02 = "streck003.jpg"
                        selectedImage03 = "streck004.jpg"
                    }
                    ShowCloud {
                        selectedImage ="sol.png"
                        marginLeftFrom = 0
                        marginLeftTo = 30
                    }
                }
            }


        }


 */
        p {
            button {

                key = messageList[0].id.toString()
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

                if (leftMessages.size >= maxMessages ) {
                    onClick = {
                        props.onSelectPension(
                            props.selectedView,
                            leftMessages,
                            person
                        )
                    }
                    +props.selectedView.buttonText
                } else {
                    onClick = {
                        props.onSelectPension(
                            reloadView.getNextView(reloadView),
                            leftMessages,
                            person
                        )
                    }
                    +reloadView.buttonText
                }
                +" ▶"
            }
        }
    }

    p {
        css {
            color = NamedColor.orange
        }
        +"Slut"
    }

    /*
        ShowWorkingLife {
            actualProfession = props.selectedProfession
            actualPerson = person
            newTitle = currentTitle
        }

     */
}
/*
fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
}

 */










