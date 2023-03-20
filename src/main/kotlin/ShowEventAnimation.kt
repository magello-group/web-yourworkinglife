import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowEventAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowEventAnimation = FC<ShowEventAnimationProps> { props ->
    val topPXTree = props.actualStyle.topPXEventAnimation - 20
    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage =  props.actualStyle.streckPilot
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
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
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

            "police" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckHouse02
                    selectedImage02 = props.actualStyle.streckHouse01
                    selectedImage03 = props.actualStyle.streckHouse01
                    selectedTopPX = topPXTree + 17
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPolis
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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
            "fireman" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckSlang01
                    selectedImage02 = props.actualStyle.streckSlang01
                    selectedImage03 = props.actualStyle.streckSlang02
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckFireman
                    selectTop = props.actualStyle.topPXEventAnimation + 10
                    selectMarginLeft = props.actualMarginLeft
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
            "kapten" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckWater
                    selectedImage02 = props.actualStyle.streckWater
                    selectedImage03 = props.actualStyle.streckWater
                    selectedTopPX = topPXTree
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckKapten
                    selectTop = props.actualStyle.topPXEventAnimation + 5
                    selectMarginLeft = props.actualMarginLeft
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
            "rescue" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckSnow
                    selectedImage02 = props.actualStyle.streckSnow
                    selectedImage03 = props.actualStyle.streckSnow
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckSlalom
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft + 2
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
            "travelagent" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckMontain
                    selectedImage02 = props.actualStyle.streckMontain
                    selectedImage03 = props.actualStyle.streckMontain
                    selectedTopPX = topPXTree - 5
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckTravel
                    selectTop = props.actualStyle.topPXEventAnimation + 5
                    selectMarginLeft = props.actualMarginLeft
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
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    selectedImage03 = props.actualStyle.streckTree03
                    selectedTopPX = topPXTree
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckProgrammer
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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
                ShowTree {
                    selectedImage01 = props.actualStyle.streckHouse02
                    selectedImage02 = props.actualStyle.streckHouse01
                    selectedImage03 = props.actualStyle.streckHouse01
                    selectedTopPX = topPXTree + 17
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckBoss
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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
            "musiker", "gardener", "bonde","painter","pt","artist","builder" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    selectedImage03 = props.actualStyle.streckTree03
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    when (props.actualProfession.professionType) {
                        "musiker" -> selectedImage = props.actualStyle.streckMusiker
                        "gardener" -> selectedImage = props.actualStyle.streckGardener
                        "bonde" -> selectedImage = props.actualStyle.streckBonde
                        "painter" -> selectedImage = props.actualStyle.streckPainter
                        "pt" -> selectedImage = props.actualStyle.streckPT
                        "builder" -> selectedImage = props.actualStyle.streckBuilder
                        else -> selectedImage = props.actualStyle.streckMusiker
                    }
                    selectTop = props.actualStyle.topPXEventAnimation + 5
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXEventCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromRight
                    selectTop = (props.actualStyle.topPXEventCloud + 200)
                    selectMarginFrom = 50
                    selectMarginTo = 40
                    selectSeconds = 10
                    selectWidth = 5
                }
            }

            "advokat","teacher", "authority" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXEventStreck
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAdvokat
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXEventStreck
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckDoctor
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXEventStreck
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckGubbe
                    selectTop = props.actualStyle.topPXEventAnimation
                    selectMarginLeft = props.actualMarginLeft
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