import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowProfessionAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
}

val ShowProfessionAnimation = FC<ShowProfessionAnimationProps> { props ->

    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage ="streckpilot1400.jpg"
                    selectTop = 350
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck005.jpg"
                    selectedImage03 = "streck006.jpg"
                }
                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                }
            }
            "detektiv" -> {
                ShowStreckImage {
                    selectedImage ="streckagent1300.jpg"
                    selectTop = 350
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                }
                ShowCloud {
                    selectedImage ="regn.png"
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                }
            }
            "police" -> {
                ShowStreckImage {
                    selectedImage ="streckpolis1300.jpg"
                    selectTop = 350
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                }
                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                }
            }
            "programmer" -> {
                ShowStreckImage {
                    selectedImage ="streckprogrammer100.jpg"
                    selectTop = 350
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                }
                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                }
            }
            else -> {
            ShowStreckImage {
                selectedImage ="streckgubbe1300.jpg"
                selectTop = 350
                selectMarginLeft = props.actualMarginLeft
            }
            ShowStreck {
                selectedImage01 = "streck002.jpg"
                selectedImage02 = "streck003.jpg"
                selectedImage03 = "streck007.jpg"
            }
            ShowCloud {
                selectedImage ="sol.png"
                marginLeftFrom = 0
                marginLeftTo = props.actualMarginLeft
            }
        }
        }
    }
}