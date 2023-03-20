import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowActionAnimationProps : Props {
    var selectedAction: String
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowActionAnimation = FC<ShowActionAnimationProps> { props ->
    val topPXTree = props.actualStyle.topPXInitAnimation - 20

    div {

        when (props.selectedAction) {
            "adventure" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    selectedImage03 = props.actualStyle.streckTree03
                    selectedTopPX = topPXTree
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckRegn
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }

                ShowStreckGubbe {
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitAnimation + 5
                    selectedImage01 = "streckgubbe1000.jpg"
                    selectedImage02 = "streckgubbe1100.jpg"
                    selectedImage03 = "streckgubbeadventure.jpg"
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMyraFromLeft
                    selectTop = (props.actualStyle.topPXInitStreck + 25)
                    selectMarginFrom = 0
                    selectMarginTo = 23
                    selectSeconds = 7
                    selectWidth = 5
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMyraFromRight
                    selectTop = (props.actualStyle.topPXInitStreck - 30)
                    selectMarginFrom = 50
                    selectMarginTo = 38
                    selectSeconds = 5
                    selectWidth = 5
                }
            }

            "family" -> {

                ShowTree {
                    selectedImage01 = props.actualStyle.streckHouse02
                    selectedImage02 = props.actualStyle.streckHouse01
                    selectedImage03 = props.actualStyle.streckHouse01
                    selectedTopPX = topPXTree + 17
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckPolis
                    selectTop = props.actualStyle.topPXInitAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }

            "vacation" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckWater
                    selectedImage02 = props.actualStyle.streckWater
                    selectedImage03 = props.actualStyle.streckWater
                    selectedTopPX = topPXTree - 5
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckSurfer
                    selectTop = props.actualStyle.topPXInitAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromLeft
                    selectTop = (props.actualStyle.topPXInitCloud + 200)
                    selectMarginFrom = 0
                    selectMarginTo = 20
                    selectSeconds = 10
                    selectWidth = 5
                }

            }
            "chilla" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree02
                    selectedImage03 = props.actualStyle.streckTree03
                    selectedTopPX = topPXTree
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckMusiker
                    selectTop = props.actualStyle.topPXInitAnimation + 5
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckOnlySol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromRight
                    selectTop = (props.actualStyle.topPXInitCloud + 200)
                    selectMarginFrom = 50
                    selectMarginTo = 40
                    selectSeconds = 10
                    selectWidth = 5
                }
            }

            "salary" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckHouse02
                    selectedImage02 = props.actualStyle.streckHouse01
                    selectedImage03 = props.actualStyle.streckHouse01
                    selectedTopPX = topPXTree + 17
                }

                ShowStreckImage {
                    selectedImage = props.actualStyle.streckBoss
                    selectTop = props.actualStyle.topPXInitAnimation
                    selectMarginLeft = props.actualMarginLeft
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }
            }
            "pension" -> {
                ShowStreckPensioner {
                    selectedTopPX = props.actualStyle.topPXInitAnimation
                }
                ShowStreck {
                    selectedTopPX = props.actualStyle.topPXInitStreck
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckgreen
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft + 10
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromLeft
                    selectTop = (props.actualStyle.topPXInitCloud + 200)
                    selectMarginFrom = 0
                    selectMarginTo = 26
                    selectSeconds = 15
                    selectWidth = 5
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromLeft
                    selectTop = (props.actualStyle.topPXInitCloud + 250)
                    selectMarginFrom = 0
                    selectMarginTo = 20
                    selectSeconds = 15
                    selectWidth = 5
                }
            }
            "fun" -> {
                ShowTree {
                    selectedImage01 = props.actualStyle.streckTree01
                    selectedImage02 = props.actualStyle.streckTree01
                    selectedImage03 = props.actualStyle.streckTree03
                    selectedTopPX = topPXTree
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft + 5
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowStreckGubbe {
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitAnimation + 5
                    selectedImage01 = "streckgubbe1000.jpg"
                    selectedImage02 = "streckgubbe1100.jpg"
                    selectedImage03 = "streckgubbeicecream.jpg"
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckIcecream
                    selectTop = (props.actualStyle.topPXInitStreck - 120)
                    selectMarginFrom = 50
                    selectMarginTo = 35
                    selectSeconds = 5
                    selectWidth = 3
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckChair
                    selectTop = (props.actualStyle.topPXInitStreck - 25)
                    selectMarginFrom = 50
                    selectMarginTo = 40
                    selectSeconds = 5
                    selectWidth = 5
                }

            }
            else -> {

                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowSparkcykel {
                    selectedTopPX = props.actualStyle.topPXInitAnimation
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromLeft
                    selectTop = (props.actualStyle.topPXInitCloud + 200)
                    selectMarginFrom = 0
                    selectMarginTo = 20
                    selectSeconds = 10
                    selectWidth = 5
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckHumlaFromRight
                    selectTop = (props.actualStyle.topPXInitCloud - 200)
                    selectMarginFrom = 50
                    selectMarginTo = 26
                    selectSeconds = 10
                    selectWidth = 5
                }
            }
        }

    }
}