import kotlin.random.Random

data class Union( val memberNumber: Int ) {
    var akassa: Boolean = false
    var incomeInsurance: Boolean = false
    var unEmployedSalaryAmount: Double = 0.0

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