package com.alfonsonavio.nexbank.core.model

import java.time.Instant

/** A transfer ordered by the user, as returned by the bank. */
class Transfer(
    val id: TransferId,
    val sourceAccountId: AccountId,
    val destinationIban: Iban,
    val beneficiaryName: String,
    val amount: Money,
    val concept: String?,
    val status: TransferStatus,
    val createdAt: Instant,
) {
    init {
        require(!amount.isNegative && !amount.isZero) { "Transfer amount must be positive" }
    }

    /** True once the bank has finished processing the transfer, either way. */
    val isFinal: Boolean get() = status !is TransferStatus.Pending
}
