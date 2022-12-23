import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.p


external interface GetPreparedProps : Props {
//    var onWatchedButtonPressed: (Video) -> Unit
    var insurance: Insurance
    var union: Union
    var person: Person
    var images: String
    var akassa: Boolean
    var incomeInsurance: Boolean
    var healthInsurance:Boolean

}

val getPrepared = FC<GetPreparedProps> { props ->

    h3 {
        +"Gör dig redo"
        //+"${props.video.speaker}: ${props.video.title}"
    }
    p {
        +"Går du med i a-kassan?"
        button {
            css {
                display = Display.block
                backgroundColor = NamedColor.lightgreen
            }
            onClick = {
                props.akassa = true
            }
            if (props.akassa) {
                +"☑ "
            } else {
                +"✔ "
            }
        }
    }

    p {
        +"Går du med i facket och får inkomstförsäkring?"
        button {
            css {
                display = Display.block
                backgroundColor = NamedColor.lightgreen
            }

            onClick = {
                props.incomeInsurance = true
            }
            if (props.incomeInsurance) {
                +"☑ "
            } else {
                +"✔ "
            }
        }
    }

    p {
        +"Tecknar du olycksfallsförsäkring?"
        button {
            css {
                display = Display.block
                backgroundColor = NamedColor.lightgreen
            }
            onClick = {
                props.healthInsurance = true
            }
            if (props.healthInsurance) {
                +"☑ "
            } else {
                +"✔ "
            }
        }
    }
}

