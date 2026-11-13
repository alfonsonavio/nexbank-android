package com.alfonsonavio.nexbank.core.model

import java.time.Instant
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class TransactionTest {
    @Test
    fun `debit has a negative signed amount`() {
        val transaction = transaction(direction = TransactionDirection.DEBIT, amount = Money.of("45.90", "EUR"))

        assertEquals(Money.of("-45.90", "EUR"), transaction.signedAmount)
    }

    @Test
    fun `credit has a positive signed amount`() {
        val transaction = transaction(direction = TransactionDirection.CREDIT, amount = Money.of("1500", "EUR"))

        assertEquals(Money.of("1500", "EUR"), transaction.signedAmount)
    }

    @Test
    fun `pending status is exposed`() {
        assertTrue(transaction(status = TransactionStatus.PENDING).isPending)
        assertFalse(transaction(status = TransactionStatus.BOOKED).isPending)
    }

    @Test
    fun `zero amount is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            transaction(amount = Money.of("0", "EUR"))
        }
    }

    @Test
    fun `negative amount is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            transaction(amount = Money.of("-10", "EUR"))
        }
    }

    @Test
    fun `balance after in another currency is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            transaction(amount = Money.of("10", "EUR"), balanceAfter = Money.of("100", "USD"))
        }
    }

    private fun transaction(
        direction: TransactionDirection = TransactionDirection.DEBIT,
        status: TransactionStatus = TransactionStatus.BOOKED,
        amount: Money = Money.of("45.90", "EUR"),
        balanceAfter: Money? = Money.of("1204.50", "EUR"),
    ) = Transaction(
        id = TransactionId("tx-1"),
        accountId = AccountId("acc-1"),
        direction = direction,
        category = TransactionCategory.CARD_PAYMENT,
        status = status,
        amount = amount,
        balanceAfter = balanceAfter,
        description = "Supermercado",
        counterpartyName = null,
        counterpartyIban = null,
        transferId = null,
        bookedAt = Instant.parse("2026-11-10T18:42:00Z"),
        valueDate = LocalDate.parse("2026-11-10"),
    )
}
