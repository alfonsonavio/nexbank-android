package com.alfonsonavio.nexbank.core.model

import java.time.Instant

/**
 * A bank account owned by the signed-in user.
 *
 * [balance] is the booked balance; [availableBalance] is what can be spent right now
 * (booked balance minus holds and pending operations). Both use the same currency.
 */
data class Account(
    val id: AccountId,
    val iban: Iban,
    val alias: String?,
    val type: AccountType,
    val status: AccountStatus,
    val balance: Money,
    val availableBalance: Money,
    val createdAt: Instant,
) {
    init {
        require(balance.currency == availableBalance.currency) {
            "Balance and available balance must use the same currency"
        }
    }

    val currency get() = balance.currency

    /** Only active accounts can be the source of a transfer. */
    val canSendMoney: Boolean get() = status == AccountStatus.ACTIVE
}
