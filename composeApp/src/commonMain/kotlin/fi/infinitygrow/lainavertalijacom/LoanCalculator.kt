package fi.infinitygrow.lainavertalijacom


import kotlin.math.pow
import kotlin.math.round


// Define LoanCalculator class
class LoanCalculator(
    val principal: Int,
    val interestRate: Double,
    val loanTerm: Double
) {
    /**
     * Calculates monthly payment using the loan amount, annual interest rate, and the number of payments (months)
     * @return Monthly payment amount
     */
    fun calculateMonthlyPayment(): Double {
        val monthlyInterestRate = interestRate / 12 / 100 // Convert annual interest rate to monthly rate
        val numberOfPayments = loanTerm * 12
        return if (monthlyInterestRate == 0.0) {
            principal.toDouble() / numberOfPayments
        } else {
            val factor = (1 + monthlyInterestRate).pow(numberOfPayments)
            principal * monthlyInterestRate * factor / (factor - 1)
        }
    }


    /**
     * Calculates the interest portion of the monthly payment.
     * @return Monthly interest portion
     */
    fun calculateMonthlyInterest(): Double {
        val monthlyInterestRate = interestRate / 12 / 100 // Convert to decimal
        return principal * monthlyInterestRate
    }

    /**
     * Calculates the capital portion of the monthly payment.
     * @return Monthly capital portion
     */
    fun calculateMonthlyCapital(): Double {
        val monthlyPayment = calculateMonthlyPayment()
        return monthlyPayment - calculateMonthlyInterest()
    }

    fun roundToTwoDecimals(value: Double): Double {
        return round(value * 100) / 100
    }
}



