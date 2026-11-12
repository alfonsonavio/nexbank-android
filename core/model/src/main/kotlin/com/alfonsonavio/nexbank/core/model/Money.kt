package com.alfonsonavio.nexbank.core.model

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Currency

/**
 * An amount of money in a specific currency.
 *
 * The amount is always a [BigDecimal] normalised to the currency's minor units
 * (2 decimals for EUR), so "10", "10.0" and "10.00" are the same value.
 * Arithmetic and comparison between different currencies is rejected.
 */
@ConsistentCopyVisibility
data class Money private constructor(val amount: BigDecimal, val currency: Currency) : Comparable<Money> {
    val isZero: Boolean get() = amount.signum() == 0

    val isNegative: Boolean get() = amount.signum() < 0

    operator fun plus(other: Money): Money {
        requireSameCurrency(other)
        return Money(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        requireSameCurrency(other)
        return Money(amount - other.amount, currency)
    }

    operator fun unaryMinus(): Money = Money(amount.negate(), currency)

    override fun compareTo(other: Money): Int {
        requireSameCurrency(other)
        return amount.compareTo(other.amount)
    }

    private fun requireSameCurrency(other: Money) {
        require(currency == other.currency) {
            "Currency mismatch: $currency and ${other.currency}"
        }
    }

    companion object {
        fun of(amount: BigDecimal, currency: Currency): Money {
            val fractionDigits = currency.defaultFractionDigits
            require(fractionDigits >= 0) { "$currency has no minor units" }

            val normalised =
                try {
                    amount.setScale(fractionDigits, RoundingMode.UNNECESSARY)
                } catch (e: ArithmeticException) {
                    throw IllegalArgumentException(
                        "$amount has more decimals than $currency allows",
                        e,
                    )
                }
            return Money(normalised, currency)
        }

        fun of(amount: String, currencyCode: String): Money = of(BigDecimal(amount), Currency.getInstance(currencyCode))

        fun zero(currency: Currency): Money = of(BigDecimal.ZERO, currency)
    }
}
