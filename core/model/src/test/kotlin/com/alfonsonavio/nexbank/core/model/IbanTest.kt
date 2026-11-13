package com.alfonsonavio.nexbank.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Test

class IbanTest {
    @Test
    fun `spaces and lower case are normalised`() {
        assertEquals("ES9121000418450200051332", Iban.of("es91 2100 0418 4502 0005 1332").value)
    }

    @Test
    fun `formatted groups characters in fours`() {
        assertEquals("ES91 2100 0418 4502 0005 1332", Iban.of("ES9121000418450200051332").formatted())
    }

    @Test
    fun `valid IBANs from several countries are accepted`() {
        Iban.of("ES7921000813610123456789")
        Iban.of("GB82WEST12345698765432")
        Iban.of("DE89370400440532013000")
    }

    @Test
    fun `wrong check digits are rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            Iban.of("ES9121000418450200051333")
        }
    }

    @Test
    fun `too short input is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            Iban.of("ES91")
        }
    }

    @Test
    fun `characters outside the IBAN alphabet are rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            Iban.of("ES91-2100-0418-4502-0005-1332")
        }
    }

    @Test
    fun `error message does not leak the account number`() {
        val error =
            assertThrows(IllegalArgumentException::class.java) {
                Iban.of("ES9121000418450200051333")
            }

        assertFalse(error.message.orEmpty().contains("2100"))
    }
}
