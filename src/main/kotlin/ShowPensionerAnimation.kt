import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface ShowPensionerAnimationProps : Props {
    var actualMarginLeft: Int
    var actualStyle: Style
}

val ShowPensionerAnimation = FC<ShowPensionerAnimationProps> { props ->

    div {

        ShowStreckPensioner {
            selectedTopPX = props.actualStyle.topPXPensionerAnimation
        }
        ShowStreck {
            selectedTopPX = props.actualStyle.topPXPensionerStreck
            selectedImage01 = props.actualStyle.streck
            selectedImage02 = props.actualStyle.streckStones01
            selectedImage03 = props.actualStyle.streckStones03
        }
    }
}