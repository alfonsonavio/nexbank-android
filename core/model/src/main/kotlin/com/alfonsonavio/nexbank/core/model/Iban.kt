package com.alfonsonavio.nexbank.core.model

/**
 * International Bank Account Number (ISO 13616) in electronic format:
 * upper case, without spaces. Construction validates the format and the mod-97 check digits.
 */
@JvmInline
value class Iban private constructor(val value: String) {
    /** Groups of four characters, as shown on statements and cards. */
    fun formatted(): String = value.chunked(4).joinToString(" ")

    override fun toString(): String = value

    companion object {
        private val FORMAT = Regex("[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}")

        fun of(raw: String): Iban {
            val normalised = raw.replace(" ", "").uppercase()
            require(FORMAT.matches(normalised)) { "Invalid IBAN format" }
            require(hasValidCheckDigits(normalised)) { "Invalid IBAN check digits" }
            return Iban(normalised)
        }

        /**
         * ISO 7064 mod-97: move the first four characters to the end, replace letters
         * with numbers (A = 10 … Z = 35) and check that the result mod 97 equals 1.
         * The remainder is computed digit by digit, so no BigInteger is needed.
         */
        private fun hasValidCheckDigits(iban: String): Boolean {
            val rearranged = iban.drop(4) + iban.take(4)
            var remainder = 0
            for (char in rearranged) {
                remainder =
                    if (char.isDigit()) {
                        (remainder * 10 + char.digitToInt()) % 97
                    } else {
                        (remainder * 100 + (char - 'A' + 10)) % 97
                    }
            }
            return remainder == 1
        }
    }
}
