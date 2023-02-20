import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowProfessionAnimationProps : Props {
    var actualProfession: Profession
}

val ShowProfessionAnimation = FC<ShowProfessionAnimationProps> { props ->
    var topPX = 580

    div {
        when (props.actualProfession.professionType) {
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
            "detektiv" -> {
                ShowStreckImage {selectedImage ="streckagent1300.jpg"}
                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
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
}