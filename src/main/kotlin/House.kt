import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class House(val personId: Int, val houseType: String)
{
    var isMortgage: Boolean = false
    var houseLoan: Loan = Loan(personId, houseType)
    var houseAmount: Float = 0.0F
    var houseMonthPayment: Float = 0.0F
    var description: String = ""
    var ownType: String = ""

    fun raiseTheRent(): Float {
        val randomValues = List(1) { Random.nextInt(1, 2) }

        return this.houseMonthPayment * (randomValues[0].toFloat() / 100.0F)
    }
    
    fun getHouse(personId: Int, type: String, salary: Float = 0.0F): House {
        val house: House
        var randomValues: List<Int>
        var currentAmount: Float
        
        when (type) {
            "rosehouse" -> {
                //Event(0, "Kul! du köper ett hus på landet med doftande rosor.","rosehouse","home"),

                house = House(personId, "rosehouse")
                house.description = "ett hus på landet med doftande rosor"
                house.ownType = "own"
                randomValues = List(1) { Random.nextInt(1000000, 5000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(2000, 5000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "castel" -> {
                //Event(1, "Kul! du köper ett slott med tinar och torn.","castel","home"),

                house = House(personId, "castel")
                house.description = "ett slott med tinar och torn"
                house.ownType = "own"


                randomValues = List(1) { Random.nextInt(5000000, 30000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(2000, 5000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "house" -> {
                //Event(2, "Kul! du köper ett minimalistiskt hus med raka linjer.","house","home"),

                house = House(personId, "house")
                house.description = "ett minimalistiskt hus med raka linjer"
                house.ownType = "own"

                randomValues = List(1) { Random.nextInt(2000000, 10000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(2000, 5000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "koja" -> {
                //Event(3, "Kul! du köper en koja i skogen.","koja","home"),

                house = House(personId, "koja")
                house.description = "en koja i skogen"
                house.ownType = "own"
                
                randomValues = List(1) { Random.nextInt(500000, 1000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(1000, 3000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "department" -> {
                //Event(5, "Kul! du köper ett bostadsrättsradhus i en förort.","department","home"),

                house = House(personId, "department")
                house.description = "ett bostadsrättsradhus i en förort"
                house.ownType = "own"

                randomValues = List(1) { Random.nextInt(1000000, 5000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(1000, 7000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "departmentcity" -> {
                //Event(4, "Kul! du köper en bostadsrätt mitt i staden.","departmentcity","home"),

                house = House(personId, "departmentcity")
                house.description = "en bostadsrätt mitt i staden"
                house.ownType = "own"

                randomValues = List(1) { Random.nextInt(5000000, 15000000) }
                house.houseAmount = randomValues[0].toFloat()

                randomValues = List(1) { Random.nextInt(1000, 7000) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "hirecity" -> {
                //Event(6, "Kul! du skaffar dig en hyresrätt mitt i staden.","hirecity","home"),

                house = House(personId, "hirecity")
                house.houseAmount = 0.0F
                house.description = "en hyresrätt mitt i staden"
                house.ownType = "hire"

                currentAmount = salary / 2
                randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "hire" -> {
                //Event(7, "Kul! du skaffar dig en hyresrätt i en förort.","hire","home"),

                house = House(personId, "hire")
                house.houseAmount = 0.0F
                house.description = "en hyresrätt i en förort"
                house.ownType = "hire"
                
                currentAmount = salary / 2
                randomValues = List(1) { Random.nextInt(4000, currentAmount.toInt()) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "hirehouse" -> {
                //Event(8, "Kul! du hyr i andra hand ett hus på landet.","hirehouse","home"),

                house = House(personId, "hirehouse")
                house.houseAmount = 0.0F
                house.description = "hyr i andra hand ett hus på landet"
                house.ownType = "hire"

                currentAmount = salary / 2
                randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }

            "hiredepartment" -> {
                //Event(9, "Kul! du hyr i andra hand ett lägenhet mitt i staden.","hiredepartment","home"),

                house = House(personId, "hiredepartment")
                house.houseAmount = 0.0F
                house.description = " hyr i andra hand en lägenhet mitt i staden"
                house.ownType = "hire"
                
                currentAmount = salary / 2
                randomValues = List(1) { Random.nextInt(5000, currentAmount.toInt()) }
                house.houseMonthPayment = randomValues[0].toFloat()
            }
            else -> {
                house = House(personId, "")
            }
        }
        
        return house
    }
}