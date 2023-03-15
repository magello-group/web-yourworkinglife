import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowEventAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowEventAnimation = FC<ShowEventAnimationProps> { props ->

    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPilot
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckLandscape01
                    selectedImage03 = props.actualStyle.streckLandscape01
                    selectedTopPX = props.actualStyle.topPXEventStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "detektiv" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAgent
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }


                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "police" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPolis
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "programmer", "solo" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckProgrammer
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "security", "VD", "ambassador", "chef", "driftchef", "bank", "insurance" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckBoss
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "advokat" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAdvokat
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "doctor" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckDoctor
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            else -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckGubbe
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXEventStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckCloudHeart
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
        }
    }
}