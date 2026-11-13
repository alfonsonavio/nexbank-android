package com.alfonsonavio.nexbank.core.model

/** Identifier of a transfer, as issued by the backend. Opaque to the app. */
@JvmInline
value class TransferId(val value: String) {
    init {
        require(value.isNotBlank()) { "TransferId must not be blank" }
    }

    override fun toString(): String = value
}
