import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowActionAnimationProps : Props {
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

        ShowCloud {
            selectedImage1 = props.actualStyle.streckSol
            selectedImage2 = props.actualStyle.streckSol
            marginLeftFrom = 0
            marginLeftTo = props.actualMarginLeft
            selectedTopPX = props.actualStyle.topPXInitCloud
            selectedWidth = props.actualStyle.streckWidth
        }
    }
}