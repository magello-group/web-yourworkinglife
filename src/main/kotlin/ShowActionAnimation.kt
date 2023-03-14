import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowActionAnimationProps : Props {
    var selectedAction: String
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowActionAnimation = FC<ShowActionAnimationProps> { props ->

    div {
        ShowSparkcykel {
            selectedTopPX = props.actualStyle.topPXInitAnimation
        }
        ShowStreck {
            selectedImage01 = props.actualStyle.streck
            selectedImage02 = props.actualStyle.streckStones01
            selectedImage03 = props.actualStyle.streckStones02
            selectedTopPX = props.actualStyle.topPXInitStreck
        }

        when (props.selectedAction) {
            "adventure" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckRegn
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMyraFromLeft
                    selectTop = (props.actualStyle.topPXInitStreck - 20)
                    selectMarginFrom = 0
                    selectMarginTo = 23
                    selectSeconds = 7
                    selectWidth = 5
                }
                /*
                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMyraFromLeft
                    selectTop = (props.actualStyle.topPXInitStreck + 15)
                    selectMarginFrom = 0
                    selectMarginTo = 15
                    selectSeconds = 5
                    selectWidth = 5
                }

                 */
                ShowStreckObject {
                    selectedImage = props.actualStyle.streckMyraFromLeft
                    selectTop = (props.actualStyle.topPXInitStreck + 30)
                    selectMarginFrom = 0
                    selectMarginTo = 15
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
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }

            "vacation" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "chilla" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckOnlySol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft + 5
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckOnlySolWidth
                }

                ShowStreckObject {
                    selectedImage = props.actualStyle.streckIcecream
                    selectTop = (props.actualStyle.topPXInitStreck - 125)
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

            "salary" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "pension" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            "fun" -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
                }

                ShowCloud {
                    selectedImage1 = props.actualStyle.streckSol
                    selectedImage2 = props.actualStyle.streckSol
                    marginLeftFrom = 0
                    marginLeftTo = props.actualMarginLeft
                    selectedTopPX = props.actualStyle.topPXInitCloud
                    selectedWidth = props.actualStyle.streckWidth
                }
            }
            else -> {
                ShowStreck {
                    selectedImage01 = props.actualStyle.streck
                    selectedImage02 = props.actualStyle.streckStones01
                    selectedImage03 = props.actualStyle.streckStones02
                    selectedTopPX = props.actualStyle.topPXInitStreck
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
            }
        }

    }
}