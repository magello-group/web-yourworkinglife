import csstype.*
import emotion.css.keyframes
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p

external interface ShowCloudProps : Props {
    var selectedImage: String
    var marginLeftFrom: Int // =26
    var marginLeftTo: Int   //=0
    var selectedTopPX: Int // 200
}

val ShowCloud = FC<ShowCloudProps> { props ->
    var topPX = 200

    if (props.selectedTopPX > 0) topPX = props.selectedTopPX

    p {

        val moln: AnimationName = keyframes {
            0.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = props.marginLeftFrom.pc
                width = 13.pc
                height = 9.pc
            }
            100.pct {
                backgroundImage = url(props.selectedImage)
                marginLeft = props.marginLeftTo.pc
                width = 13.pc
                height = 9.pc
            }
        }

        css {
            animationDuration = 6.s
            animationName = moln
            animationFillMode = AnimationFillMode.both
            display = Display.flex
            position = Position.absolute
            top = topPX.px
            left = 1.px
            width = 10.px
            height = 10.px
        }
    }
}