import kotlinx.serialization.Serializable

@Serializable
data class Person (val name: String) {
    var luck: Boolean = false
    var magellit: Boolean = false
    var age: Int = 0

    var accounts = mutableListOf<Account>()

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