//package team.karakum.components

import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import react.useState

external interface WelcomeQuestionListProps : Props {
    var title: String
    var name: String
    var age: String
    var questions: List<Question>
}

val WelcomeQuestionList = FC<WelcomeQuestionListProps> { props ->
    var name by useState(props.name)
    var age by useState(props.age)

    h1 {
        css {
            display = Display.block
            position = Position.absolute
            top = 10.px
            left = 10.px
            color = NamedColor.black
            fontSize = 26.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }
        +props.title
    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 70.px
            left = 10.px
            color = NamedColor.black
            fontSize = 18.px
            fontFamily = FontFamily.cursive
        }
        +props.questions[0].questionText
        +" "

        input {
            css {
                marginTop = 5.px
                marginBottom = 5.pc
                fontSize = 18.px
                fontFamily = FontFamily.cursive
            }
            type = InputType.text
            value = name
            onChange = { event ->
                name = event.target.value
            }
        }
    }

    div {
        css {
            display = Display.block
            position = Position.absolute
            top = 120.px
            left = 10.px
            color = NamedColor.black
            fontSize = 18.px
            fontFamily = FontFamily.cursive
        }
        +props.questions[1].questionText
        +" "

        input {
            css {
                marginTop = 5.px
                marginBottom = 5.pc
                fontSize = 18.px
                fontFamily = FontFamily.cursive
            }
            type = InputType.text
            value = age
            onChange = { event ->
                age = event.target.value
            }
        }
    }

    p {
        css {
            display = Display.block
            position = Position.absolute
            top = 580.px
            left = 10.px
            color = NamedColor.black
            fontSize = 18.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }
        if (name.isNullOrEmpty()) {
            +" "
        } else {
            + props.questions[0].objectText
            + ": $name "
        }
    }

    p {
        css {
            display = Display.block
            position = Position.absolute
            top = 610.px
            left = 10.px
            color = NamedColor.black
            fontSize = 18.px
            backgroundColor = NamedColor.white
            fontFamily = FontFamily.cursive
        }
        if (age.isNullOrEmpty()) {
            +" "
        } else {
            + props.questions[1].objectText
            + ": $age "
        }
    }
}
