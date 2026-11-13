package com.alfonsonavio.nexbank.core.model

import java.time.Instant

/** Lifecycle of a transfer. Each state carries only the data that exists in that state. */
sealed interface TransferStatus {
    data object Pending : TransferStatus

    data class Completed(val executedAt: Instant) : TransferStatus

    data class Rejected(val reason: TransferRejectionReason) : TransferStatus
}
