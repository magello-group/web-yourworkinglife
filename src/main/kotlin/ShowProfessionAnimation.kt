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
            "pilote","yoga" -> {
                ShowStreckImage {
                    when (props.actualProfession.professionType) {
                        "pilot" -> selectedImage =  props.actualStyle.streckPilot
                        "yoga" -> selectedImage = props.actualStyle.streckYoga
                        else -> selectedImage = props.actualStyle.streckPilot
                    }
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
                    selectedImage = props.actualStyle.streckPolis
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
            "fireman" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckSlang01
                    selectedImage02 = props.actualStyle.streckSlang01
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckSlang02
                    else
                        selectedImage03 = props.actualStyle.streckSlang01
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckFireman
                    selectTop = props.actualStyle.topPXProfessionAnimation + 10
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
            "kapten" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckWater
                    selectedImage02 = props.actualStyle.streckWater
                    selectedImage03 = props.actualStyle.streckWater
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckKapten
                    selectTop = props.actualStyle.topPXProfessionAnimation + 5
                    selectMarginLeft = props.actualMarginLeft - 2
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
            "rescue" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckSnow
                    selectedImage02 = props.actualStyle.streckSnow
                    selectedImage03 = props.actualStyle.streckSnow
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckSlalom
                    selectTop = props.actualStyle.topPXProfessionAnimation
                    selectMarginLeft = props.actualMarginLeft + 2
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
            "travelagent" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckMontain
                    selectedImage02 = props.actualStyle.streckMontain
                    selectedImage03 = props.actualStyle.streckMontain
                    selectedTopPX = topPXTree - 5
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckTravel
                    selectTop = props.actualStyle.topPXProfessionAnimation + 5
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
                    selectedImage = props.actualStyle.streckProgrammer
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

            "security", "VD", "ambassador", "chef", "driftchef", "bank", "insurance", "pt" -> {
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
                    when (props.actualProfession.professionType) {
                        "pt" -> selectedImage = props.actualStyle.streckPT
                        else -> props.actualStyle.streckBoss
                    }
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
            "musiker", "gardener", "bonde","painter","builder" -> {
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
                    when (props.actualProfession.professionType) {
                        "musiker" -> selectedImage = props.actualStyle.streckMusiker
                        "gardener" -> selectedImage = props.actualStyle.streckGardener
                        "bonde" -> selectedImage = props.actualStyle.streckBonde
                        "painter" -> selectedImage = props.actualStyle.streckPainter
                        "builder" -> selectedImage = props.actualStyle.streckBuilder
                        else -> selectedImage = props.actualStyle.streckMusiker
                    }
                    selectTop = props.actualStyle.topPXProfessionAnimation + 7
                    selectMarginLeft = props.actualMarginLeft - 2
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
                if (props.isProfessionList) {
                    ShowStreckObject {
                        selectedImage = props.actualStyle.streckHumlaFromRight
                        selectTop = (props.actualStyle.topPXProfessionCloud + 200)
                        selectMarginFrom = 50
                        selectMarginTo = 40
                        selectSeconds = 10
                        selectWidth = 5
                    }
                }
            }
            "artist" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckTree03
                    else
                        selectedImage03 = props.actualStyle.streckTree01
                    selectedTopPX = topPXTree
                }
                ShowStreckGubbe {
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXProfessionAnimation + 5
                    selectedImage01 =  props.actualStyle.streckArtist01
                    selectedImage02 = props.actualStyle.streckArtist02
                    selectedImage03 = props.actualStyle.streckArtist01
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXProfessionCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
                if (props.isProfessionList) {
                    ShowStreckObject {
                        selectedImage = props.actualStyle.streckHumlaFromRight
                        selectTop = (props.actualStyle.topPXProfessionCloud + 200)
                        selectMarginFrom = 50
                        selectMarginTo = 40
                        selectSeconds = 10
                        selectWidth = 5
                    }
                }
            }

            "advokat", "teacher", "authority" -> {
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
                    selectedImage = props.actualStyle.streckAdvokat
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

            "doctor" -> {
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
                    selectedImage = props.actualStyle.streckDoctor
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

            else -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXProfessionStreck
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckGubbe
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
        }
    }
}