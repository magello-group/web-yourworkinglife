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
    var profession: String = ""
    var countCats: String = ""
    var countDogs: String = ""
    var countHorses: String = ""
    var countBikes: String = ""
    var countCars: String = ""
    var countBabies: String = ""
    var countFriends: String = ""
    var countWalking: String = ""
    var countStrong: String = ""
    var countFishing: String = ""
    var countParties: String = ""
    var countBoats: String = ""
    var countLoves: String = ""
}