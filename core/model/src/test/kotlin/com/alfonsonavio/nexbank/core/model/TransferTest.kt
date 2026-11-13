package com.alfonsonavio.nexbank.core.model

import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class TransferTest {
    @Test
    fun `pending transfer is not final`() {
        assertFalse(transfer(status = TransferStatus.Pending).isFinal)
    }

    @Test
    fun `completed and rejected transfers are final`() {
        assertTrue(transfer(status = TransferStatus.Completed(Instant.parse("2026-11-13T09:00:05Z"))).isFinal)
        assertTrue(transfer(status = TransferStatus.Rejected(TransferRejectionReason.INSUFFICIENT_FUNDS)).isFinal)
    }

    @Test
    fun `rejection reason is carried by the rejected state`() {
        val status = transfer(status = TransferStatus.Rejected(TransferRejectionReason.LIMIT_EXCEEDED)).status

        val reason = (status as TransferStatus.Rejected).reason

        assertEquals(TransferRejectionReason.LIMIT_EXCEEDED, reason)
    }

    @Test
    fun `zero amount is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            transfer(amount = Money.of("0", "EUR"))
        }
    }

    private fun transfer(status: TransferStatus = TransferStatus.Pending, amount: Money = Money.of("250", "EUR")) =
        Transfer(
            id = TransferId("tr-1"),
            sourceAccountId = AccountId("acc-1"),
            destinationIban = Iban.of("ES7921000813610123456789"),
            beneficiaryName = "María López",
            amount = amount,
            concept = "Alquiler noviembre",
            status = status,
            createdAt = Instant.parse("2026-11-13T09:00:00Z"),
        )
}
