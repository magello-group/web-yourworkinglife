import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.FormEncType
import react.dom.html.FormMethod
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.textarea
import react.dom.html.ReactHTML.tr
import react.key

external interface PersonQuestionListProps : Props {
    var questions: List<Question>
    var selectedQuestion: Question?
    var onSelectQuestion: (Question) -> Unit
}

val PersonQuestionList = FC<PersonQuestionListProps> { props ->

    div {
        css {
            position = Position.sticky
            top = 100.px
            left = 10.px
            fontFamily = FontFamily.cursive
        }
        p {
            form {
                method = FormMethod.post
                encType = FormEncType.multipartFormData
                action = "https:..."
                +"vad heter du"
                input {
                    type = InputType.text
                    id = "fname"
                    name = "fname"
                }
            }
        }
    }
}