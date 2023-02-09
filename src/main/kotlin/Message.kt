import kotlinx.serialization.Serializable

@Serializable
data class Message (
    val id: Int,
    val messageText: String = "",
    val color: String = "",
    val animation: String =""
) {
    var age: Int = 0
    var event: String = ""
    var actualPension: String = ""
    var actualSalary: String = ""
    var actualSalaryAmount: String = ""
    var actualDepotAmount: String = ""
    var actualPensionAmount: String = ""
    var actualHireAmount: String = ""
    var actualHouseAmount: String = ""
    var actualLoanAmount: String = ""
}
