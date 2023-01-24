import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class Union( val memberNumber: Int ) {
    var akassa: Boolean = false
    var incomeInsurance: Boolean = false
    var extraInsurance: Boolean = false
    var unEmployedSalaryAmount: Double = 0.0
    var countUnEmployeeMonth: Int = 0
    val akassaPercentage100 = 0.80
    val akassaPercentage300 = 0.70
    val maxSalaryAkassa100 = 26400.0
    val maxSalaryAkassa300 = 22000.0
    val maxSalaryNoAkassa = 11220.0
    val incomeInsurance100 = 0.62
    val incomeInsurance90 = 0.64
    val incomeInsurance80 = 0.66
    val incomeInsurance70 = 0.69
    val incomeInsurance60 = 0.70
    val incomeInsurance50 = 0.76
    val incomeInsurance40 = 0.77
    val incomeInsurance30 = 0.80
    val linkInsurance = "https://www.unionen.se/medlemskapet/inkomstforsakring"
    val linkAkassa = "https://www.kommunalsakassa.se/om-du-blir-arbetslos/rakna-ut-din-a-kassa.html"

    fun setIncomeInsurance(salary: Double) {
        var sum: Double = 0.0
        val leftMonth = this.countUnEmployeeMonth.toDouble() - (200.0 / 22.0)

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
            sum *= if (this.countUnEmployeeMonth.toDouble() > (200.0 / 22.0))
                (200.0 / 22.0)
            else
                this.countUnEmployeeMonth.toDouble()

            //Efter 200 dagar till 300 dagar ingår a-kassa
            sum += if (leftMonth > (100.0 / 22.0))
                this.akassaPercentage300 * salary * (100.0 / 22.0)
            else
                this.akassaPercentage300 * salary * leftMonth

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
            sum *= if (this.countUnEmployeeMonth.toDouble() > (150.0 / 22.0))
                (150.0 / 22.0)
            else
                this.countUnEmployeeMonth.toDouble()
        }
        this.unEmployedSalaryAmount = sum
    }

    fun setAkassa(salary: Double) {
        var sum100: Double = 0.0
        var sum200: Double = 0.0
        var leftMonth: Double = 0.0

        if (akassa) {
            sum100 = if (salary < maxSalaryAkassa100)
                salary * akassaPercentage100
            else
                maxSalaryAkassa100

            if (this.countUnEmployeeMonth.toDouble() > (100.0 / 22.0)) {
                sum100 *= (100 / 22).toInt()

                leftMonth = this.countUnEmployeeMonth.toDouble() - (100.0 / 22.0)

                sum200 = if (salary < maxSalaryAkassa300)
                    salary * akassaPercentage300
                else
                    maxSalaryAkassa300

                sum200 *= if (leftMonth > (200.0 / 22.0))
                    (200.0 / 22.0)
                else
                    leftMonth
            } else
                sum100 *= this.countUnEmployeeMonth.toDouble()
        }

        this.unEmployedSalaryAmount = sum100 + sum200
    }

    fun setNoAkassa(salary: Double) {

        if (this.countUnEmployeeMonth.toDouble() > (300.0 / 22.0))
                this.unEmployedSalaryAmount = this.maxSalaryNoAkassa * (300.0 / 22.0)
        else
            this.unEmployedSalaryAmount = this.maxSalaryNoAkassa * this.countUnEmployeeMonth.toDouble()
    }
}

