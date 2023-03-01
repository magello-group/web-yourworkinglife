import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowEventAnimationProps : Props {
    var actualProfession: Profession
}

val ShowEventAnimation = FC<ShowEventAnimationProps> { props ->

    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage ="streckpilot1400.jpg"
                    selectTop = 507
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck005.jpg"
                    selectedImage03 = "streck006.jpg"
                    selectedTopPX = 700
                }

                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
            "detektiv" -> {
                ShowStreckImage {
                    selectedImage ="streckagent1300.jpg"
                    selectTop = 507
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                    selectedTopPX = 700
                }


                ShowCloud {
                    selectedImage ="regn.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
            "police" -> {
                ShowStreckImage {
                    selectedImage ="streckpolis1300.jpg"
                    selectTop = 507
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                    selectedTopPX = 700
                }

                ShowCloud {
                    selectedImage ="sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
            else -> {
                ShowStreckImage {
                    selectedImage = "streckgubbe1300.jpg"
                    selectTop = 507
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                    selectedTopPX = 700
                }
                ShowCloud {
                    selectedImage = "sol.png"
                    marginLeftFrom = 0
                    marginLeftTo = 35
                    selectedTopPX = 357
                }
            }
        }
    }
}