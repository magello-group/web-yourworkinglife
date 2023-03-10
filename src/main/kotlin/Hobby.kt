import kotlinx.serialization.Serializable

@Serializable
data class Hobby (
    var hobbyType: String = "",
    var age: Int = 0,
    var maxAge: Int = 0,
    var cost: Float = 0.0F,
    var point: Int = 0,
    var description: String = ""
)
{
    fun getAllHobbies(): List<Hobby> {
        return listOf(
            Hobby("cat",0,18,1000.0F,1,"Man blir lycklig och välmående av katter"),
            Hobby("dog",0,15,2000.0F,2,"Hunden ger dig både motion och en vän"),
            Hobby("horse",0,30,6000.0F,6,"Hästen ger dig vänner, socialsammanhang, välmående och du blir stark"),
            Hobby("party",0,1,3000.0F,3,"Partyt livar upp och ger dig många vänner"),
            Hobby("car",0,10,5000.0F,5,"Bilen ger dig både frihet och bekvämlighet"),
            Hobby("bike",0,10,3000.0F,3,"Motorcykeln ger dig en frihetskänsla och snabbhet"),
            Hobby("boat",0,10,1000.0F,1,"Båten ger dig en härlig känsla på böljan det blå"),

            Hobby("friend",0,0,0.0F,1,"Vänner ger dig gemenskap och välmående"),
            Hobby("strong",0,0,0.0F,1,"Träning gör dig stark, välmående och pigg"),
            Hobby("fish",0,0,0.0F,1,"En fisketur på böljan det blå ger frisk luft och välmående"),
            Hobby("love",0,0,0.0F,1,"Sambon betalar halva hyran!"),
            Hobby("walk",0,0,0.0F,1,"Att bara få vandra själv ger insikt, motion och välmående")
        )
    }

    fun costHobby(): Float {
        var cost = 0.0F

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