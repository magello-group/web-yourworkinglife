import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowInitAnimationProps : Props {
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowInitAnimation = FC<ShowInitAnimationProps> { props ->
    div {
        ShowStreckGubbe {
            marginLeftFrom = 0
            marginLeftTo = props.actualMarginLeft

            selectedTopPX = props.actualStyle.topPXInitAnimation
        }

        ShowStreck {
            selectedImage01 = props.actualStyle.streck
            selectedImage02 = props.actualStyle.streckStones01
            selectedImage03 = props.actualStyle.streckStones02
            selectedTopPX = props.actualStyle.topPXInitStreck
        }

        ShowCloud {
            selectedImage1 = props.actualStyle.streckCloud
            selectedImage2 = props.actualStyle.streckSol
            marginLeftFrom = 0
            marginLeftTo = props.actualMarginLeft
            selectedTopPX = props.actualStyle.topPXInitCloud
            selectedWidth = props.actualStyle.streckWidth
        }
    }
}