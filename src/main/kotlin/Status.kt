import kotlinx.serialization.Serializable

@Serializable
data class Status (
    val statusId: Int
) {
    var age: String = ""
    var event: String = ""
    var pension: String = ""
    var employeeSalary: String = ""
    var accountSalaryAmount: String = ""
    var accountDepotAmount: String = ""
    var accountPensionAmount: String = ""
    var houseHireAmount: String = ""
    var houseAmount: String = ""
    var houseLoanAmount: String = ""
    var profession: String = ""
}