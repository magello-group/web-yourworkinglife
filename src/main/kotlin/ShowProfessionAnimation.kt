import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowProfessionAnimationProps : Props {
    var actualProfession: Profession
    var actualMarginLeft: Int
    var actualStyle: Style
    var actualCloudMarginLeftTo: Int
    var isProfessionList: Boolean
    var topPXCloud: Int
    var topPXAnimation: Int
    var topPXStreck: Int
}

val ShowProfessionAnimation = FC<ShowProfessionAnimationProps> { props ->
    val topPXTree = props.topPXAnimation - 20
    
    div {
        when (props.actualProfession.professionType) {
            "pilote","yoga" -> {
                ShowStreckImage {
                    when (props.actualProfession.professionType) {
                        "pilot" -> selectedImage =  props.actualStyle.streckPilot
                        "yoga" -> selectedImage = props.actualStyle.streckYoga
                        else -> selectedImage = props.actualStyle.streckPilot
                    }
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckLandscape01
                    selectedImage03 = props.actualStyle.streckLandscape01
                    selectedTopPX = props.topPXStreck
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "detektiv" -> {
                if (props.isProfessionList) {
                    ShowTree {
                        selectedImage01 = props.actualStyle.streckHouse02
                        selectedImage02 = props.actualStyle.streckLada
                        selectedImage03 = props.actualStyle.streckLada
                        selectedTopPX = topPXTree + 17
                    }
                } else {
                    ShowStreck {
                        selectedImage01 = props.actualStyle.streck
                        selectedImage02 = props.actualStyle.streckStones01
                        selectedImage03 = props.actualStyle.streckStones02
                        selectedTopPX = props.topPXStreck
                    }
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAgent
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckCloud
                    selectedImage2 = props.actualStyle.streckRegn
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
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
                        selectedTopPX = props.topPXStreck
                    }
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPolis
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
            "fireman", "gardener" -> {
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
                    when (props.actualProfession.professionType) {
                        "fireman" -> selectedImage = props.actualStyle.streckFireman
                        "gardener" -> selectedImage = props.actualStyle.streckGardener
                        else -> selectedImage = props.actualStyle.streckFireman
                    }
                    selectTop = props.topPXAnimation + 10
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
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
                    selectTop = props.topPXAnimation + 5
                    selectMarginLeft = props.actualMarginLeft - 2
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
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
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft + 2
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
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
                    selectTop = props.topPXAnimation + 5
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
            "programmer"-> {
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
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "security", "VD", "ambassador", "chef", "driftchef", "bank", "insurance", "authority", "architect"   -> {
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
                        selectedTopPX = props.topPXStreck
                    }
                }

                ShowStreckImage {
                    when (props.actualProfession.professionType) {
                        "security" -> selectedImage = props.actualStyle.streckSecurity
                        "VD" -> selectedImage = props.actualStyle.streckVD
                        "ambassador" -> selectedImage = props.actualStyle.streckAmbassador
                        "insurance" -> selectedImage = props.actualStyle.streckVD
                        "architect" -> selectedImage = props.actualStyle.streckAdvokat
                        else -> selectedImage = props.actualStyle.streckAdvokat
                    }
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
            "musiker", "bonde","painter","builder","socialworker", "writer","actor", "solo"-> {
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
                        "bonde" -> selectedImage = props.actualStyle.streckBonde
                        "painter" -> selectedImage = props.actualStyle.streckPainter
                        "builder" -> selectedImage = props.actualStyle.streckBuilder
                        "socialworker" -> selectedImage = props.actualStyle.streckSocial
                        "writer" -> selectedImage = props.actualStyle.streckWriter
                        "actor" -> selectedImage = props.actualStyle.streckActress
                        "solo" -> selectedImage = props.actualStyle.streckSolo
                        else -> selectedImage = props.actualStyle.streckMusiker
                    }
                    selectTop = props.topPXAnimation + 7
                    selectMarginLeft = props.actualMarginLeft - 2
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
                if (props.isProfessionList) {
                    ShowStreckObject {
                        selectedImage = props.actualStyle.streckHumlaFromRight
                        selectTop = (props.topPXCloud + 200)
                        selectMarginFrom = 50
                        selectMarginTo = 40
                        selectSeconds = 10
                        selectWidth = 5
                    }
                }
            }
            "comedian" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckTree03
                    else
                        selectedImage03 = props.actualStyle.streckTree01
                    selectedTopPX = topPXTree
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowSparkcykel{
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXAnimation + 5
                    selectedImage01 = props.actualStyle.streckClown
                    selectedImage02 = props.actualStyle.streckClown
                    selectedWidth01 = 10
                    selectedWidth02 = 10
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckIcecream
                    selectTop = (props.topPXStreck - 120)
                    selectMarginFrom = 50
                    selectMarginTo = 35
                    selectSeconds = 5
                    selectWidth = 3
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckChair
                    selectTop = (props.topPXStreck - 25)
                    selectMarginFrom = 50
                    selectMarginTo = 40
                    selectSeconds = 5
                    selectWidth = 5
                }

            }
            "riding" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckTree03
                    else
                        selectedImage03 = props.actualStyle.streckTree01
                    selectedTopPX = topPXTree
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowSparkcykel{
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXAnimation + 5
                    selectedImage01 = props.actualStyle.streckRide
                    selectedImage02 = props.actualStyle.streckRide
                    selectedWidth01 = 12
                    selectedWidth02 = 12
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckBird
                    selectTop = (props.topPXStreck - 120)
                    selectMarginFrom = 0
                    selectMarginTo = 23
                    selectSeconds = 5
                    selectWidth = 5
                }
            }

            "pt" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    if (props.isProfessionList)
                        selectedImage03 = props.actualStyle.streckTraining
                    else
                        selectedImage03 = props.actualStyle.streckTree01
                    selectedTopPX = topPXTree
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPT
                    selectTop = props.topPXAnimation + 7
                    selectMarginLeft = props.actualMarginLeft + 5
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
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
                ShowSparkcykel{
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXAnimation + 5
                    selectedImage01 = props.actualStyle.streckArtist02
                    selectedImage02 = props.actualStyle.streckArtist01
                    selectedWidth01 = 13
                    selectedWidth02 = 13
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
                if (props.isProfessionList) {
                    ShowStreckObject {
                        selectedImage = props.actualStyle.streckHumlaFromRight
                        selectTop = (props.topPXCloud + 200)
                        selectMarginFrom = 50
                        selectMarginTo = 40
                        selectSeconds = 10
                        selectWidth = 5
                    }
                }
            }

            "advokat" -> {
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
                        selectedTopPX = props.topPXStreck
                    }
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckAdvokat
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "teacher" -> {
                if (props.isProfessionList) {
                    ShowTree {
                        selectedImage01 = props.actualStyle.streckHouse02
                        selectedImage02 = props.actualStyle.streckSkola
                        selectedImage03 = props.actualStyle.streckSkola
                        selectedTopPX = topPXTree + 17
                    }
                } else {
                    ShowStreck {
                        selectedImage01 = props.actualStyle.streck
                        selectedImage02 = props.actualStyle.streckStones01
                        selectedImage03 = props.actualStyle.streckStones02
                        selectedTopPX = props.topPXStreck
                    }
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckBoss
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "professor" -> {
                if (props.isProfessionList) {
                    ShowTree {
                        selectedImage01 = props.actualStyle.streckHouse02
                        selectedImage02 = props.actualStyle.streckFabric
                        selectedImage03 = props.actualStyle.streckFabric
                        selectedTopPX = topPXTree + 17
                    }

                    ShowStreckImage {
                        selectedImage = props.actualStyle.streckProfessor
                        selectTop = props.topPXAnimation
                        selectMarginLeft = props.actualMarginLeft + 8
                    }

                    ShowCloud {
                        selectedImage1 = props.actualStyle.streckCloud
                        selectedImage2 = props.actualStyle.streckRegn
                        marginLeftFrom = 0
                        marginLeftTo = props.actualCloudMarginLeftTo - 10
                        selectedTopPX = props.topPXCloud
                        selectedWidth = props.actualStyle.streckWidth
                    }

                } else {
                    ShowStreck {
                        selectedImage01 = props.actualStyle.streck
                        selectedImage02 = props.actualStyle.streckStones01
                        selectedImage03 = props.actualStyle.streckStones02
                        selectedTopPX = props.topPXStreck
                    }

                    ShowStreckImage {
                        selectedImage = props.actualStyle.streckProfessor
                        selectTop = props.topPXAnimation
                        selectMarginLeft = props.actualMarginLeft
                    }

                    ShowCloud {
                        selectedImage1 = props.actualStyle.streckCloud
                        selectedImage2 = props.actualStyle.streckRegn
                        marginLeftFrom = 0
                        marginLeftTo = props.actualCloudMarginLeftTo
                        selectedTopPX = props.topPXCloud
                        selectedWidth = props.actualStyle.streckWidth
                    }
                }


                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMouse
                    selectTop = (props.topPXStreck - 30)
                    selectMarginFrom = 0
                    selectMarginTo = 30
                    selectSeconds = 7
                    selectWidth = 5
                }
            }

            "doctor" -> {

                ShowTree {
                    selectedImage01 = props.actualStyle.streckHospital
                    selectedImage02 = props.actualStyle.streckHospital
                    selectedImage03 = props.actualStyle.streckHospital
                    selectedTopPX = topPXTree + 17
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckDoctor
                    selectTop = props.topPXAnimation + 17
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            else -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.topPXStreck
                }
                ShowStreckImage {
                    selectedImage = props.actualStyle.streckGubbe
                    selectTop = props.topPXAnimation
                    selectMarginLeft = props.actualMarginLeft
                }
                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualCloudMarginLeftTo
                    selectedTopPX = props.topPXCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
        }
    }
}