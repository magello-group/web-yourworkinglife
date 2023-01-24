import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class Union( val memberNumber: Int ) {
    var akassa: Boolean = false
    var incomeInsurance: Boolean = false
    var extraInsurance: Boolean = false
    var unEmployedSalary: Double = 0.0
    var countUnEmployeeMonth: Int = 0
    val akassaPercentage100 = 0.80
    val akassaPercentage300 = 0.70
    val maxSalaryAkassa100 = 26400.0
    val maxSalaryAkassa300 = 22000.0
    val incomeInsurance100 = 0.62
    val incomeInsurance90 = 0.64
    val incomeInsurance80 = 0.66
    val incomeInsurance70 = 0.69
    val incomeInsurance60 = 0.70
    val incomeInsurance50 = 0.76
    val incomeInsurance40 = 0.77
    val incomeInsurance30 = 0.80

    fun getIncomeInsurance(salary: Double, sumMonth: Int): Double {
        var sum: Double = 0.0
        if (this.incomeInsurance && this.extraInsurance) {
            sum = if (salary <= 30000.0)
                salary * incomeInsurance30
            else if (salary <= 40000.0)
                salary * incomeInsurance40
            else if (salary <= 50000.0)
                salary * incomeInsurance50
            else if (salary <= 60000.0)
                salary * incomeInsurance60
            else if (salary <= 70000.0)
                salary * incomeInsurance70
            else if (salary <= 80000.0)
                salary * incomeInsurance80
            else if (salary <= 90000.0)
                salary * incomeInsurance90
            else
                salary * incomeInsurance100

            //200 dagar ingår
            sum = if (sumMonth > 200 / 22) sum * (200 / 22).toInt()
            else sum * sumMonth

        } else if (this.incomeInsurance) {
            sum = if (salary <= 30000.0)
                salary * incomeInsurance30
            else if (salary <= 40000.0)
                salary * incomeInsurance40
            else if (salary <= 50000.0)
                salary * incomeInsurance50
            else
                60000 * incomeInsurance60

            //150 dagar ingår
            sum = if (sumMonth > 150 / 22) sum * (150 / 22).toInt()
            else sum * sumMonth
        }
        return sum
    }

    fun getAkassa(salary: Double, sumMonth: Int): Double {
        var sum100: Double = 0.0
        var sum200: Double = 0.0
        var leftMonth: Int = 0

        if (akassa) {
            sum100 = if (salary < maxSalaryAkassa100)
                salary * akassaPercentage100
            else
                maxSalaryAkassa100

            if (sumMonth > 100 / 22) {
                sum100 *= (100 / 22).toInt()

                leftMonth = sumMonth - (100 / 22).toInt()

                sum200 = if (salary < maxSalaryAkassa300)
                    salary * akassaPercentage300
                else
                    maxSalaryAkassa300

                sum200 *= if (leftMonth > 200 / 22)
                    (200 / 22).toInt()
                else
                    leftMonth
            } else
                sum100 *= sumMonth
        }

        return sum100 + sum200
    }
}
