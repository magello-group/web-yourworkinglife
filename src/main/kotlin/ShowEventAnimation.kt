import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowEventAnimationProps : Props {
    var actualProfession: Profession
}

val ShowEventAnimation = FC<ShowEventAnimationProps> { props ->
    var topPX = 580

    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage ="streckpilot1400.jpg"
                    selectTop = 350
                }
                /*
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck005.jpg"
                    selectedImage03 = "streck006.jpg"
                }

                 */
                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                }
            }
            "detektiv" -> {
                ShowStreckImage {
                    selectedImage ="streckagent1300.jpg"
                    selectTop = 350
                }
                /*
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                }

                 */
                ShowCloud {
                    selectedImage ="regn.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                }
            }
            "police" -> {
                ShowStreckImage {
                    selectedImage ="streckpolis1300.jpg"
                    selectTop = 350
                }
                /*
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                }

                 */
                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                }
            }
            else -> {
                ShowStreckImage {
                    selectedImage = "streckgubbe1300.jpg"
                    selectTop = 350
                }
                /*
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                }

                 */
                ShowCloud {
                    selectedImage = "sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 35
                }
            }
        }
    }
}