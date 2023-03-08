import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowEventAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
}

val ShowEventAnimation = FC<ShowEventAnimationProps> { props ->

    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage ="streckpilot1400.jpg"
                    selectTop = 507
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck005.jpg"
                    selectedImage03 = "streck006.jpg"
                    selectedTopPX = 700
                }

                ShowCloud {
                    selectedImage = "heart.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
            "detektiv" -> {
                ShowStreckImage {
                    selectedImage ="streckagent1300.jpg"
                    selectTop = 507
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck007.jpg"
                    selectedTopPX = 700
                }


                ShowCloud {
                    selectedImage = "heart.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
            "police" -> {
                ShowStreckImage {
                    selectedImage ="streckpolis1300.jpg"
                    selectTop = 507
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                    selectedTopPX = 700
                }

                ShowCloud {
                    selectedImage = "heart.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
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
                    selectedImage03 = "streck004.jpg"
                }
                ShowCloud {
                    selectedImage = "heart.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                }
            }
            else -> {
                ShowStreckImage {
                    selectedImage = "streckgubbe1300.jpg"
                    selectTop = 507
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedImage01 = "streck002.jpg"
                    selectedImage02 = "streck003.jpg"
                    selectedImage03 = "streck004.jpg"
                    selectedTopPX = 700
                }
                ShowCloud {
                    selectedImage = "heart.png"
                    marginLeftFrom = 0
                    marginLeftTo = 30
                    selectedTopPX = 357
                }
            }
        }
    }
}