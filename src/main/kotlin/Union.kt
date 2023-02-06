import kotlinx.serialization.Serializable

@Serializable
data class Union( val personId: Int ) {
    var akassa: Boolean = false
    var incomeInsurance: Boolean = false
    var extraInsurance: Boolean = false
    var unEmployedSalaryAmount: Double = 0.0
    var unEmployedSalary100: Double = 0.0
    var unEmployedSalary150: Double = 0.0
    var unEmployedSalary200: Double = 0.0
    var unEmployedSalary300: Double = 0.0
    var countUnEmployeeMonth: Int = 0
    val akassaPercentage100: Double = 0.80
    val akassaPercentage300: Double = 0.70
    val maxSalaryAkassa100: Double = 26400.0
    val maxSalaryAkassa300: Double = 22000.0
    val maxSalaryNoAkassa: Double = 11220.0
    val incomeInsurance100: Double = 0.62
    val incomeInsurance90: Double = 0.64
    val incomeInsurance80: Double = 0.66
    val incomeInsurance70: Double = 0.69
    val incomeInsurance60: Double = 0.70
    val incomeInsurance50: Double = 0.76
    val incomeInsurance40: Double = 0.77
    val incomeInsurance30: Double = 0.80
    val linkInsurance = "https://www.unionen.se/medlemskapet/inkomstforsakring"
    val linkAkassa = "https://www.kommunalsakassa.se/om-du-blir-arbetslos/rakna-ut-din-a-kassa.html"

    fun getIncomeInsurance(salary: Double): Double {
        var sum = 0.0
        val month100: Double = 100.0 / 22.0
        val month150: Double = 150.0 / 22.0
        val month200: Double = 200.0 / 22.0
        var leftMonth = 0

        if (this.incomeInsurance && this.extraInsurance) {

            //Arbetslöshetssersättning = procent av lönen i 200 dagar
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
                this.unEmployedSalary200 * this.countUnEmployeeMonth.toDouble()

            //Efter 200 dagar till 300 dagar ingår a-kassa
            leftMonth = this.countUnEmployeeMonth - month200.toInt()

            if (salary >= maxSalaryAkassa300)
                this.unEmployedSalary300 = maxSalaryAkassa300
            else
                this.unEmployedSalary300 = this.akassaPercentage300 * salary

            sum += if (leftMonth > month100.toInt())
                this.unEmployedSalary300 * month100
            else
                this.unEmployedSalary300 * leftMonth.toDouble()

        } else if (this.incomeInsurance) {

            //Arbetslöshetssersättning = procent av lönen max 60000 i lön
            sum = if (salary <= 30000.0)
                salary * incomeInsurance30
            else if (salary <= 40000.0)
                salary * incomeInsurance40
            else if (salary <= 50000.0)
                salary * incomeInsurance50
            else
                60000.0 * incomeInsurance60

            //Arbetslöshetssersättning * antal månader max 150 dagar
            sum += if (this.countUnEmployeeMonth > month150.toInt())
                this.unEmployedSalary150 * month150
            else
                this.unEmployedSalary150 * this.countUnEmployeeMonth.toDouble()

            //Efter 150 dagar till 300 dagar ingår a-kassa
            leftMonth = this.countUnEmployeeMonth - month150.toInt()

            if (salary >= maxSalaryAkassa300)
                this.unEmployedSalary300 = maxSalaryAkassa300
            else
                this.unEmployedSalary300 = this.akassaPercentage300 * salary
            this.unEmployedSalary300 = this.akassaPercentage300 * salary

            sum += if (leftMonth > month150)
                this.unEmployedSalary300 * month150
            else
                this.unEmployedSalary300 * leftMonth.toDouble()
        }
        return sum
    }

    fun showIncomeInsurance(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        if (this.incomeInsurance && this.extraInsurance) {

            storyList =
                 storyList.plus(
                    Message(
                        storyId,
                        "Grattis! med inkomst- och tilläggsförsäkring får du i 200 dagar",
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

        } else if (this.incomeInsurance) {

            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "Grattis! med inkomstförsäkring får du i 150 dagar",
                        "hotpink",
                        ""
                    )
                )

            storyList =
                storyList.plus(
                    Message(
                        storyId,
                        "per månad: ${this.unEmployedSalary150.toInt().formatDecimalSeparator()} SEK.",
                        "hotpink",
                        ""
                    )
                )

            if (this.countUnEmployeeMonth > 150) {
                storyId += 1

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
        }

        return storyList
    }

    fun getAkassa(salary: Double): Double {
        var sum100 = 0.0
        var sum200 = 0.0
        val month100: Double = 100.0 / 22.0
        val month200: Double = 200.0 / 22.0
        var leftMonth = 0

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
                    this.unEmployedSalary300 * leftMonth.toDouble()
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
                    this.unEmployedSalary300 * leftMonth.toDouble()
            }
        }

        return sum100 + sum200
    }

    fun showAkassa(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                 storyId,
                "Grattis! från a-kassan får du i 100 dagar",
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
        var sum = 0.0
        val month300: Double = 300.0 / 22.0

        if (countUnEmployeeMonth > month300.toInt())
            sum = maxSalaryNoAkassa * month300
        else
            sum = maxSalaryNoAkassa * countUnEmployeeMonth.toDouble()

        return sum
    }

    fun showNoAkassa(messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                 storyId,
                "Oj! ingen a-kassa, du får ut i 100 dagar",
                "orange",
                ""
            )
        )

        storyId += 1
        storyList = storyList.plus(
            Message(
                storyId,
                "per månad: ${this.unEmployedSalary100.toInt().formatDecimalSeparator()} SEK.",
                "orange",
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
                    "orange",
                    ""
                )
            )
        }

        return storyList
    }

    fun showCountUnEmployeeMonth(age: Int, messageList: List<Message>, messageId: Int): List<Message> {
        var storyList = messageList
        var storyId = messageId

        storyList = storyList.plus(
            Message(
                storyId,
                "Åh nej :( du är arbetslös i ${ this.countUnEmployeeMonth * 22} dagar.",
                "orange",
                ""
            )
        )

        return storyList
    }
}

