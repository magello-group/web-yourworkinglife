import kotlinx.serialization.Serializable

@Serializable
data class Union( val personId: Int ) {
    var isAkassa: Boolean = false
    var isIncomeInsurance: Boolean = false
    var isExtraInsurance: Boolean = false
    var unEmployedSalaryAmount: Double = 0.0
    var noAkassaSalaryAmount: Double = 0.0
    private var unEmployedSalary100: Double = 0.0
    private var unEmployedSalary150: Double = 0.0
    private var unEmployedSalary200: Double = 0.0
    private var unEmployedSalary300: Double = 0.0
    var countUnEmployeeMonth: Int = 0
    private val akassaPercentage100: Double = 0.80
    private val akassaPercentage300: Double = 0.70
    val maxSalaryAkassa100: Double = 26400.0
    val maxSalaryAkassa300: Double = 22000.0
    val maxSalaryNoAkassa: Double = 11220.0
    private val incomeInsurance100: Double = 0.62
    private val incomeInsurance90: Double = 0.64
    private val incomeInsurance80: Double = 0.66
    private val incomeInsurance70: Double = 0.69
    private val incomeInsurance60: Double = 0.70
    private val incomeInsurance50: Double = 0.76
    private val incomeInsurance40: Double = 0.77
    private val incomeInsurance30: Double = 0.80
    var insurancePercentage: Double = 0.0
    val linkInsurance = "https://www.unionen.se/medlemskapet/inkomstforsakring"
    val linkAkassa = "https://www.kommunalsakassa.se/om-du-blir-arbetslos/rakna-ut-din-a-kassa.html"

    fun getInsurancePercentage(salary: Double): Double {
        val insurancePercentage = if (salary <= 30000.0)
            this.incomeInsurance30
        else if (salary <= 40000.0)
            this.incomeInsurance40
        else if (salary <= 50000.0)
            this.incomeInsurance50
        else if (salary <= 60000.0)
            this.incomeInsurance60
        else if (salary <= 70000.0)
            this.incomeInsurance70
        else if (salary <= 80000.0)
            this.incomeInsurance80
        else if (salary <= 90000.0)
            this.incomeInsurance90
        else
            this.incomeInsurance100

        return insurancePercentage
    }
    fun getIncomeInsurance(salary: Double): Double {
        var sum = 0.0
        val month100: Double = 100.0 / 22.0
        val month150: Double = 150.0 / 22.0
        val month200: Double = 200.0 / 22.0
        val leftMonth: Int

        this.insurancePercentage = this.getInsurancePercentage(salary)

        if (this.isIncomeInsurance && this.isExtraInsurance) {
            //Arbetslöshetssersättning = procent av lönen i 200 dagar

            this.unEmployedSalary200 = salary * this.insurancePercentage

            //Arbetslöshetssersättning * antal månader max 200 dagar
            sum = if (this.countUnEmployeeMonth > month200.toInt())
                this.unEmployedSalary200 * month200
            else
                this.unEmployedSalary200 * this.countUnEmployeeMonth.toDouble()

            //Efter 200 dagar till 300 dagar ingår a-kassa
            leftMonth = this.countUnEmployeeMonth - month200.toInt()

            if (salary >= this.maxSalaryAkassa300)
                this.unEmployedSalary300 = this.maxSalaryAkassa300
            else
                this.unEmployedSalary300 = this.akassaPercentage300 * salary

            sum += if (leftMonth > month100.toInt())
                this.unEmployedSalary300 * month100
            else
                this.unEmployedSalary300 * leftMonth.toDouble()

        } else if (this.isIncomeInsurance) {

            //Arbetslöshetssersättning = procent av lönen max 60000 i lön
            sum = salary * this.insurancePercentage

            //Arbetslöshetssersättning * antal månader max 150 dagar
            sum += if (this.countUnEmployeeMonth > month150.toInt())
                this.unEmployedSalary150 * month150
            else
                this.unEmployedSalary150 * this.countUnEmployeeMonth.toDouble()

            //Efter 150 dagar till 300 dagar ingår a-kassa
            leftMonth = this.countUnEmployeeMonth - month150.toInt()

            if (salary >= this.maxSalaryAkassa300)
                this.unEmployedSalary300 = this.maxSalaryAkassa300
            else
                this.unEmployedSalary300 = this.akassaPercentage300 * salary
            this.unEmployedSalary300 = this.akassaPercentage300 * salary

            sum += if (leftMonth > month150)
                this.unEmployedSalary300 * month150
            else
                this.unEmployedSalary300 * leftMonth.toDouble()
        } else {
            sum = this.getAkassa(salary)
        }

        return sum
    }

    fun showIncomeInsurance(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.isIncomeInsurance && this.isExtraInsurance) {
            storyId += 1
            storyList =
                 storyList.plus(
                    Message(
                        storyId,
                        "Med inkomst- och tilläggsförsäkring får du i 200 dagar",
                        "hotpink",
                        ""
                    )
                )

            storyId += 1
            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "per månad: ${this.unEmployedSalary200.toInt().formatDecimalSeparator()} SEK.",
                        "hotpink",
                        ""
                    )
                )

            storyId += 1
            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "vilket är: ${this.insurancePercentage * 100}% av lönen.",
                        "hotpink",
                        ""
                    )
                )

            if (this.countUnEmployeeMonth > 200) {
                storyId += 1
                storyList = storyList.plus(
                    Message(
                        storyId,
                        "De sista 100 dagarna får du ut av a-kassan ${
                            this.unEmployedSalary300.toInt().formatDecimalSeparator()
                        } SEK",
                        "hotpink",
                        ""
                    )
                )
            }

        } else if (this.isIncomeInsurance) {
            storyId += 1
            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "Med inkomstförsäkring får du i 150 dagar",
                        "hotpink",
                        ""
                    )
                )

            storyId += 1
            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "per månad: ${this.unEmployedSalary150.toInt().formatDecimalSeparator()} SEK.",
                        "hotpink",
                        ""
                    )
                )

            storyId += 1
            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "vilket är: ${this.insurancePercentage * 100}% av lönen.",
                        "hotpink",
                        ""
                    )
                )

            storyId += 1
            if (this.countUnEmployeeMonth > 150) {

                storyList = storyList.plus(
                    Message(
                        storyId,
                        "De sista 150 dagarna får du ut av a-kassan ${
                            this.unEmployedSalary300.toInt().formatDecimalSeparator()
                        } SEK.",
                        "hotpink",
                        ""
                    )
                )
            }
        } else {
            this.showAkassa(storyList, storyId)
        }

        return storyList
    }

    fun getAkassa(salary: Double): Double {
        var sum100 = 0.0
        var sum200 = 0.0
        val month100: Double = 100.0 / 22.0
        val month200: Double = 200.0 / 22.0
        val leftMonth: Int

        if (this.isAkassa) {
            if (salary < this.maxSalaryAkassa100)
                insurancePercentage = this.akassaPercentage100

            if (this.countUnEmployeeMonth > month100.toInt()) {
                //Arbetslöshetssersättning = procent av lönen i 100 dagar
                this.unEmployedSalary100 = if (salary < this.maxSalaryAkassa100)
                    salary * this.akassaPercentage100
                else
                    this.maxSalaryAkassa100

                //Arbetslöshetssersättning * 100 dagar
                sum100 = this.unEmployedSalary100 * month100

                //Arbetslöshetssersättning = procent av lönen resterande 200 dagar
                this.unEmployedSalary300 = if (salary < this.maxSalaryAkassa300)
                    salary * this.akassaPercentage300
                else
                    this.maxSalaryAkassa300

                leftMonth = this.countUnEmployeeMonth - month100.toInt()

                sum200 = if (leftMonth > month200.toInt())
                    this.unEmployedSalary300 * month200
                else
                    this.unEmployedSalary300 * leftMonth.toDouble()
            } else {
                //Arbetslöshetssersättning = procent av lönen i 300 dagar
                this.unEmployedSalary100 = if (salary < this.maxSalaryNoAkassa)
                    salary * this.akassaPercentage100
                else
                    this.maxSalaryNoAkassa

                //Arbetslöshetssersättning * 100 dagar
                sum100 = this.unEmployedSalary100 * this.countUnEmployeeMonth.toDouble()

                //Arbetslöshetssersättning = procent av lönen resterande 200 dagar
                this.unEmployedSalary300 = if (salary < this.maxSalaryNoAkassa)
                    salary * this.akassaPercentage300
                else
                    this.maxSalaryNoAkassa

                leftMonth = this.countUnEmployeeMonth - month100.toInt()

                sum200 = if (leftMonth > month200.toInt())
                    this.unEmployedSalary300 * month200
                else
                    this.unEmployedSalary300 * leftMonth.toDouble()
            }
        }

        return sum100 + sum200
    }

    fun showAkassa(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                 storyId,
                "Med a-kassan får du i 100 dagar",
                "hotpink",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "per månad: ${this.unEmployedSalary100.toInt().formatDecimalSeparator()} SEK.",
                "hotpink",
                ""
            )
        )

        storyId += 1
        storyList =
            storyList.plus(
                Message(
                    storyId,
                    "vilket är: ${this.insurancePercentage * 100}% av lönen.",
                    "hotpink",
                    ""
                )
            )

        if (this.countUnEmployeeMonth > 200) {
            storyId += 1
            storyList = storyList.plus(
                Message(
                    storyId,
                    "De sista 200 dagarna fick du ut av a-kassan ${
                        this.unEmployedSalary300.toInt().formatDecimalSeparator()
                    } SEK.",
                    "hotpink",
                    ""
                )
            )
        }

        return storyList
    }

    fun getNoAkassa(salary: Double): Double {
        val sum: Double
        val month300: Double = 300.0 / 22.0

        this.unEmployedSalary300 = if (salary < this.maxSalaryNoAkassa)
            salary * this.akassaPercentage300
        else
            this.maxSalaryNoAkassa

        sum = if (this.countUnEmployeeMonth > month300.toInt())
            this.unEmployedSalary300 * month300
        else
            this.unEmployedSalary300 * this.countUnEmployeeMonth.toDouble()

        return sum
    }

    fun showNoAkassa(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyId += 1
        storyList = storyList.plus(
            Message(
                 storyId,
                "Utan A-kassa får du ut i 300 dagar",
                "orange",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "per månad: ${this.unEmployedSalary300.toInt().formatDecimalSeparator()} SEK.",
                "orange",
                ""
            )
        )

        return storyList
    }

    fun showCountUnEmployeeMonth(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        val storyId = messageId + 1

        storyList = storyList.plus(
            Message(
                storyId,
                "Åh nej :( du är arbetslös i ${this.countUnEmployeeMonth * 22} dagar.",
                "orange",
                ""
            )
        )

        return storyList
    }
}

