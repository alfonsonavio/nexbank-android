package com.alfonsonavio.nexbank.core.model

/** Identifier of a transaction, as issued by the backend. Opaque to the app. */
@JvmInline
value class TransactionId(val value: String) {
    init {
        require(value.isNotBlank()) { "TransactionId must not be blank" }
    }

    override fun toString(): String = value
}
