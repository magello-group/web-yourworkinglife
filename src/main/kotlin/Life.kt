import kotlinx.serialization.Serializable

@Serializable
data class Life ( val personId: Int) {
    var person = Person(personId)
    var age = 0
    var parent = Parent(personId)
    var employee = Employee(1)
    var professionId: Int = 0
    var accountPension = Account(1, "pensionskonto")
    var accountSalary = Account(2, "lönekonto")
    var accountDepot = Account(3, "depå")
    var accountTax = Account(4, "skatt")
    var accountNoAkassa = Account(5, "noakassa")
    var messageList: List<Message> = emptyList()
    var isPandemi = false
    var isBoom = false
    var isRecession = false
    var isQuestion = false
    var questionMessageId = 0
    var isNewProfession = false
    var professionMessageId = 0
    var firstSalary: Float = 0.0F
}