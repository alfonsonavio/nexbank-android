package com.alfonsonavio.nexbank.core.model

import org.junit.Assert.assertThrows
import org.junit.Test

class NewTransferTest {
    @Test
    fun `valid transfer is accepted`() {
        newTransfer()
    }

    @Test
    fun `concept is optional`() {
        newTransfer(concept = null)
    }

    @Test
    fun `blank beneficiary name is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            newTransfer(beneficiaryName = "   ")
        }
    }

    @Test
    fun `beneficiary name longer than the limit is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            newTransfer(beneficiaryName = "a".repeat(NewTransfer.MAX_BENEFICIARY_NAME_LENGTH + 1))
        }
    }

    @Test
    fun `concept longer than the limit is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            newTransfer(concept = "a".repeat(NewTransfer.MAX_CONCEPT_LENGTH + 1))
        }
    }

    @Test
    fun `zero amount is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            newTransfer(amount = Money.of("0", "EUR"))
        }
    }

    private fun newTransfer(
        beneficiaryName: String = "María López",
        amount: Money = Money.of("250", "EUR"),
        concept: String? = "Alquiler noviembre",
    ) = NewTransfer(
        sourceAccountId = AccountId("acc-1"),
        destinationIban = Iban.of("ES7921000813610123456789"),
        beneficiaryName = beneficiaryName,
        amount = amount,
        concept = concept,
    )
}
