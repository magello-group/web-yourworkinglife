import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class Union( val memberNumber: Int ) {
    var akassa: Boolean = false
    var incomeInsurance: Boolean = false
    var extraInsurance: Boolean = false
    var unEmployedSalaryAmount: Double = 0.0
    var unEmployedSalary100: Double = 0.0
    var unEmployedSalary150: Double = 0.0
    var unEmployedSalary200: Double = 0.0
    var unEmployedSalary300: Double = 0.0
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

    fun getIncomeInsurance(salary: Double): Double {
        var sum: Double = 0.0
        var month100: Double = 100.0/22.0
        var month150: Double = 150.0/22.0
        var month200: Double = 200.0/22.0
        var leftMonth: Int = 0

        if (this.incomeInsurance && this.extraInsurance) {
            //Arbetslöshetssersättning = procent av lönen
            this.unEmployedSalary200 = if (salary <= 30000.0)
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

            //Arbetslöshetssersättning * antal månader max 200 dagar
            sum = if (this.countUnEmployeeMonth > month200.toInt())
                this.unEmployedSalary200 * month200
            else
                this.unEmployedSalary200 * this.countUnEmployeeMonth

            //Efter 200 dagar till 300 dagar ingår a-kassa
            leftMonth =  this.countUnEmployeeMonth - month200.toInt()

            this.unEmployedSalary300 = this.akassaPercentage300 * salary
            sum += if (leftMonth > month100.toInt())
                this.unEmployedSalary300 * month100
            else
                this.unEmployedSalary300 * leftMonth

        } else if (this.incomeInsurance) {
            //Arbetslöshetssersättning = procent av lönen max 60000 i lön
            sum = if (salary <= 30000.0)
                salary * incomeInsurance30
            else if (salary <= 40000.0)
                salary * incomeInsurance40
            else if (salary <= 50000.0)
                salary * incomeInsurance50
            else
                60000 * incomeInsurance60

            //Arbetslöshetssersättning * antal månader max 150 dagar
            sum = if (this.countUnEmployeeMonth > month150.toInt())
                this.unEmployedSalary150 * month150
            else
                this.unEmployedSalary150 * this.countUnEmployeeMonth.toDouble()

            //Efter 150 dagar till 300 dagar ingår a-kassa
            leftMonth =  this.countUnEmployeeMonth - month150.toInt()

            this.unEmployedSalary300 = this.akassaPercentage300 * salary
            sum += if (leftMonth > month150)
                this.unEmployedSalary300 * month150
            else
                this.unEmployedSalary300 * leftMonth
        }

        return sum
    }

    fun getAkassa(salary: Double): Double {
        var sum100: Double = 0.0
        var sum200: Double = 0.0
        var month100: Double = 100.0/22.0
        var month150: Double = 150.0/22.0
        var month200: Double = 200.0/22.0
        var leftMonth: Int = 0

        if (akassa) {
            if (this.countUnEmployeeMonth > month100.toInt()) {
                //Arbetslöshetssersättning = procent av lönen i 100 dagar
                this.unEmployedSalary100 = if (salary < maxSalaryAkassa100)
                    salary * akassaPercentage100
                else
                    maxSalaryAkassa100

                //Arbetslöshetssersättning * 100 dagar
                sum100 = this.unEmployedSalary100 * month100

                //Arbetslöshetssersättning = procent av lönen resterande 200 dagar
                this.unEmployedSalary300 = if (salary < maxSalaryAkassa300)
                    salary * akassaPercentage300
                else
                    maxSalaryAkassa300

                leftMonth = this.countUnEmployeeMonth - month100.toInt()

                sum200 = if (leftMonth > month200.toInt())
                    this.unEmployedSalary300 * month200
                else
                    this.unEmployedSalary300 * leftMonth
            } else {
                //Arbetslöshetssersättning = procent av lönen i 300 dagar
                this.unEmployedSalary100 = if (salary < maxSalaryNoAkassa)
                    salary * akassaPercentage100
                else
                    maxSalaryNoAkassa

                //Arbetslöshetssersättning * 100 dagar
                sum100 = this.unEmployedSalary100 * this.countUnEmployeeMonth.toDouble()

                //Arbetslöshetssersättning = procent av lönen resterande 200 dagar
                this.unEmployedSalary300 = if (salary < maxSalaryNoAkassa)
                    salary * akassaPercentage300
                else
                    maxSalaryNoAkassa

                leftMonth = this.countUnEmployeeMonth - month100.toInt()

                sum200 = if (leftMonth > month200.toInt())
                    this.unEmployedSalary300 * month200
                else
                    this.unEmployedSalary300 * leftMonth
            }
        }

        return sum100 + sum200
    }

    fun getNoAkassa(salary: Double): Double {
        var month300: Double = 300.0/22.0
        var sum: Double = 0.0
        sum = if (this.countUnEmployeeMonth > month300.toInt())
            this.maxSalaryNoAkassa * month300
        else
            this.maxSalaryNoAkassa * this.countUnEmployeeMonth.toDouble()

        return sum
    }
}

