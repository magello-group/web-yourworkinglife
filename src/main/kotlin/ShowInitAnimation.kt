import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowInitAnimationProps : Props {
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowInitAnimation = FC<ShowInitAnimationProps> { props ->
    val topPXTree = props.actualStyle.topPXInitAnimation - 20
    div {

        ShowTree {
            selectedImage01 = props.actualStyle.streckTree01
            selectedImage02 = props.actualStyle.streckTree02
            selectedImage03 = props.actualStyle.streckTree03
            selectedTopPX = topPXTree
        }

        ShowStreckGubbe {
            marginLeftFrom = 0
            marginLeftTo = props.actualMarginLeft

            selectedTopPX = props.actualStyle.topPXInitAnimation
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