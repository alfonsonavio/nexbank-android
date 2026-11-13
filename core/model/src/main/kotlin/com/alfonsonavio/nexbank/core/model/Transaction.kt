package com.alfonsonavio.nexbank.core.model

import java.time.Instant
import java.time.LocalDate

/**
 * A movement on an account.
 *
 * [amount] is always positive; whether money left or entered the account is given by
 * [direction]. Use [signedAmount] when the sign matters (lists, totals).
 */
data class Transaction(
    val id: TransactionId,
    val accountId: AccountId,
    val direction: TransactionDirection,
    val category: TransactionCategory,
    val status: TransactionStatus,
    val amount: Money,
    val balanceAfter: Money?,
    val description: String,
    val counterpartyName: String?,
    val counterpartyIban: Iban?,
    val transferId: TransferId?,
    val bookedAt: Instant,
    val valueDate: LocalDate,
) {
    init {
        require(!amount.isNegative && !amount.isZero) { "Transaction amount must be positive" }
        require(balanceAfter == null || balanceAfter.currency == amount.currency) {
            "Balance after must use the same currency"
        }
    }

    /** Negative for debits, positive for credits. */
    val signedAmount: Money
        get() =
            when (direction) {
                TransactionDirection.DEBIT -> -amount
                TransactionDirection.CREDIT -> amount
            }

    val isPending: Boolean get() = status == TransactionStatus.PENDING
}
