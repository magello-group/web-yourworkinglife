import kotlinx.serialization.Serializable

@Serializable
data class Status (
    val statusId: Int
) {
    var age: String = ""
    var event: String = ""
    var employeeSalary: String = ""
    var accountSalaryAmount: String = ""
    var accountDepotAmount: String = ""
    var accountPensionAmount: String = ""
    var houseHireAmount: String = ""
    var houseAmount: String = ""
    var houseLoanAmount: String = ""
    var loanMonthPayment: String = ""
    var interestMonthPayment: String = ""
    var profession: String = ""
    var countCats: String = "0"
    var countDogs: String = "0"
    var countHorses: String = "0"
    var countBikes: String = "0"
    var countCars: String = "0"
    var countBabies: String = "0"
    var countFriends: String = "0"
    var countWalking: String = "0"
    var countStrong: String = "0"
    var countFishing: String = "0"
    var countParties: String = "0"
    var countBoats: String = "0"
    var countLoves: String = "0"
    var luck: String = ""
    var luckPoint: Int = 0
}