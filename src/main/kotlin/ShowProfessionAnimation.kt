import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowProfessionAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
    var actualStyle: Style
    var actualCloudMarginLeftTo: Int
    var isProfessionList: Boolean
}

val ShowProfessionAnimation = FC<ShowProfessionAnimationProps> { props ->
    val topPXTree = props.actualStyle.topPXProfessionAnimation - 20
    div {
        when (props.actualProfession.professionType) {
            "pilote" -> {
                ShowStreckImage {
                    selectedImage =  props.actualStyle.streckPilot
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckLandscape01
                    selectedImage03 = props.actualStyle.streckLandscape01
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "detektiv" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAgent
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckRegn
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }

            "police" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPolis
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "programmer", "solo" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckTree03
                    else
                        selectedImage03 = props.actualStyle.streckTree01
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = "streckprogrammer100.jpg"
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "security", "VD", "ambassador", "chef", "driftchef", "bank", "insurance" -> {
                if (props.isProfessionList) {
                    ShowTree {
                        selectedImage01 = props.actualStyle.streckHouse02
                        selectedImage02 = props.actualStyle.streckHouse01
                        selectedImage03 = props.actualStyle.streckHouse01
                        selectedTopPX = topPXTree + 17
                    }
                } else {
                    ShowStreck {
                        selectedImage01 = props.actualStyle.streck
                        selectedImage02 = props.actualStyle.streckStones01
                        selectedImage03 = props.actualStyle.streckStones02
                        selectedTopPX = props.actualStyle.topPXProfessionStreck
                    }
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckBoss
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "advokat" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAdvokat
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "doctor" -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckDoctor
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            else -> {
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckGubbe
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
        }
    }
}