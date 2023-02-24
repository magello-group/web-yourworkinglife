import kotlinx.serialization.Serializable

@Serializable
data class Hobby (
    var hobbyType: String = "",
    var age: Int = 0,
    var maxAge: Int = 0,
    var cost: Float = 0.0F
)
{
    fun getAllHobbies(): List<Hobby> {
        return listOf(
            Hobby("cat",0,18,1000.0F),
            Hobby("dog",0,15,2000.0F),
            Hobby("horse",0,30,6000.0F),
            Hobby("party",0,1,3000.0F),
            Hobby("car",0,10,5000.0F),
            Hobby("bike",0,10,3000.0F),
            Hobby("boat",0,10,1000.0F)
        )

    }

    fun costHobby(): Float {
        var cost: Float = 0.0F

        for (hobby in this.getAllHobbies()) {
            if (this.hobbyType == hobby.hobbyType)
                cost = this.cost
        }

        return cost
    }

    fun getHobby(hobbyType: String): Hobby {
        var hobby = Hobby("")
        val allHobbies = hobby.getAllHobbies()

        for (next in allHobbies) {
            if (hobbyType == next.hobbyType)
                hobby = next
        }

        return hobby
    }
}