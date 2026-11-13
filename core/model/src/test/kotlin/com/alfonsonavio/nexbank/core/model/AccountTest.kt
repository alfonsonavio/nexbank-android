package com.alfonsonavio.nexbank.core.model

import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountTest {
    @Test
    fun `active account can send money`() {
        assertTrue(account(status = AccountStatus.ACTIVE).canSendMoney)
    }

    @Test
    fun `blocked and closed accounts cannot send money`() {
        assertFalse(account(status = AccountStatus.BLOCKED).canSendMoney)
        assertFalse(account(status = AccountStatus.CLOSED).canSendMoney)
    }

    @Test
    fun `currency comes from the balance`() {
        assertEquals("EUR", account().currency.currencyCode)
    }

    @Test
    fun `available balance can be lower than the booked balance`() {
        val account = account(balance = Money.of("100", "EUR"), availableBalance = Money.of("80", "EUR"))

        assertTrue(account.availableBalance < account.balance)
    }

    @Test
    fun `balances in different currencies are rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            account(balance = Money.of("100", "EUR"), availableBalance = Money.of("100", "USD"))
        }
    }

    private fun account(
        status: AccountStatus = AccountStatus.ACTIVE,
        balance: Money = Money.of("1250.40", "EUR"),
        availableBalance: Money = balance,
    ) = Account(
        id = AccountId("acc-1"),
        iban = Iban.of("ES9121000418450200051332"),
        alias = "Nómina",
        type = AccountType.CHECKING,
        status = status,
        balance = balance,
        availableBalance = availableBalance,
        createdAt = Instant.parse("2026-01-15T10:00:00Z"),
    )
}
