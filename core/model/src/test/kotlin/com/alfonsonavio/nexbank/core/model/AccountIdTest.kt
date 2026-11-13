package com.alfonsonavio.nexbank.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class AccountIdTest {
    @Test
    fun `blank id is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            AccountId(" ")
        }
    }

    @Test
    fun `ids with the same value are equal`() {
        assertEquals(AccountId("acc-1"), AccountId("acc-1"))
    }
}
