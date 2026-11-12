package com.alfonsonavio.nexbank.core.model

import java.util.Currency
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MoneyTest {
    private val eur = Currency.getInstance("EUR")

    @Test
    fun `amounts with different scale are equal`() {
        assertEquals(Money.of("10", "EUR"), Money.of("10.00", "EUR"))
    }

    @Test
    fun `amount is normalised to the currency minor units`() {
        assertEquals("10.50", Money.of("10.5", "EUR").amount.toPlainString())
    }

    @Test
    fun `plus adds amounts in the same currency`() {
        assertEquals(Money.of("15.75", "EUR"), Money.of("10.50", "EUR") + Money.of("5.25", "EUR"))
    }

    @Test
    fun `minus can produce a negative amount`() {
        val result = Money.of("5", "EUR") - Money.of("7.50", "EUR")

        assertEquals(Money.of("-2.50", "EUR"), result)
        assertTrue(result.isNegative)
    }

    @Test
    fun `unary minus negates the amount`() {
        assertEquals(Money.of("-3.00", "EUR"), -Money.of("3", "EUR"))
    }

    @Test
    fun `zero has the currency scale and is zero`() {
        val zero = Money.zero(eur)

        assertEquals("0.00", zero.amount.toPlainString())
        assertTrue(zero.isZero)
        assertFalse(zero.isNegative)
    }

    @Test
    fun `comparison orders by amount`() {
        assertTrue(Money.of("1.99", "EUR") < Money.of("2", "EUR"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `more decimals than the currency allows is rejected`() {
        Money.of("1.005", "EUR")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `arithmetic between different currencies is rejected`() {
        Money.of("1", "EUR") + Money.of("1", "USD")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `comparison between different currencies is rejected`() {
        Money.of("1", "EUR") < Money.of("1", "USD")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `invalid currency code is rejected`() {
        Money.of("1", "EURO")
    }
}
