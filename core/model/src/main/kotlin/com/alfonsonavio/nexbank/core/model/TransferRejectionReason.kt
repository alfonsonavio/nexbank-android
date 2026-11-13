package com.alfonsonavio.nexbank.core.model

/** Why the bank rejected a transfer. [OTHER] covers codes the app does not know yet. */
enum class TransferRejectionReason {
    INSUFFICIENT_FUNDS,
    ACCOUNT_BLOCKED,
    SAME_ACCOUNT,
    INVALID_IBAN,
    LIMIT_EXCEEDED,
    OTHER,
}
