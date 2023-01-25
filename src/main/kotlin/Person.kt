import kotlinx.serialization.Serializable

@Serializable
data class Person (val id: Int) {
    var name: String = ""
    var age: Int = 0
    var pension: Double = 0.0
    var luck: Boolean = false
    var magellit: Boolean = false
    var sick: Boolean = false

    var union: Union = Union(id)
    var professions: List<Profession> = emptyList()
    var employees: List<Employee> = emptyList()
    var accounts: List<Account> = emptyList()
    var insurances: List<Insurance> = emptyList()

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