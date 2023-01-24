import kotlinx.serialization.Serializable

@Serializable
data class Person (val id: Int) {
    var name: String = ""
    var luck: Boolean = false
    var magellit: Boolean = false
    var age: Int = 0
    var sick: Boolean = false

    var union: Union = Union(id)
    var professions: List<Profession> = emptyList()
    var accounts: List<Account> = emptyList()
    var insurances: List<Insurance> = emptyList()
    var pension: Double = 0.0

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