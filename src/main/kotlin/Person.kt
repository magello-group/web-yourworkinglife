
data class Person(
    val personId: Int,
    var personName: String = "",
    var luck: Boolean = false,
    var magellit: Boolean = false,
    var age: Int = 20,
    var profession: Profession?
) {

    val accounts = mutableListOf<Account>()
    val insurances = mutableListOf<Insurance>()

    fun registerPerson()
    {
        //Insert in database
    }

    fun updatePerson()
    {
        //Update in database
    }

    fun getPerson(){
        //Select person status
    }
}