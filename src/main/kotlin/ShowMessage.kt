import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p

external interface ShowMessageProps : Props {
    var selectedMessage: Message
}

val ShowMessage = FC<ShowMessageProps> { props ->

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

    val blinkingPink: AnimationName = keyframes {
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

    if (props.selectedMessage.animation != "") {
        when (props.selectedMessage.animation) {
            "blinking" -> {
                p {

                    css {
                        animationDuration = 3.s
                        animationName = blinking
                        animationFillMode = AnimationFillMode.both
                        color = NamedColor.gold
                    }
                    +props.selectedMessage.messageText
                }
            }

            "blinkingPink" -> {
                p {

                    css {
                        animationDuration = 3.s
                        animationName = blinkingPink
                        animationFillMode = AnimationFillMode.both
                        color = NamedColor.red
                    }
                    +props.selectedMessage.messageText
                }
            }
        }
    } else {
        when (props.selectedMessage.color) {
            "deepskyblue" -> {
                p {
                    css {
                        color = NamedColor.deepskyblue
                    }
                    +props.selectedMessage.messageText
                }
            }

            "lavender" -> {
                p {
                    css {
                        color = NamedColor.blueviolet
                    }
                    +props.selectedMessage.messageText
                }
            }

            "grey" -> {
                p {
                    css {
                        color = NamedColor.yellowgreen
                    }
                    +props.selectedMessage.messageText
                }
            }

            "hotpink" -> {
                p {
                    css {
                        color = NamedColor.hotpink
                    }
                    +props.selectedMessage.messageText
                }
            }

            "orange" -> {
                p {
                    css {
                        color = NamedColor.orange
                    }
                    +props.selectedMessage.messageText
                }
            }

            "" -> {
                p {
                    css {
                        color = NamedColor.green
                    }
                    +props.selectedMessage.messageText
                }
            }
        }
    }
}
