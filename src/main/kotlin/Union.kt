import kotlin.random.Random

data class Union(
    var unionId: String?,
    val member: Person?,
    var akassa: Boolean = false,
    var memberNumber: String?,
    var incomeInsurance: Boolean = false,
    var unEmployedSalaryAmount: Double = 0.0
) {
    fun registerUnion() {
        //Insert union in db
    }

    fun updateUnion() {
        //Update union in db
    }

    fun getUnion() {
        //Select union information
    }
}