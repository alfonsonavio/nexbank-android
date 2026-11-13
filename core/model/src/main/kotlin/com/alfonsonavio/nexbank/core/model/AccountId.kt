package com.alfonsonavio.nexbank.core.model

/**
 * Identifier of an account, as issued by the backend.
 * Treated as opaque: the app never parses or builds it.
 */
@JvmInline
value class AccountId(val value: String) {
    init {
        require(value.isNotBlank()) { "AccountId must not be blank" }
    }

    override fun toString(): String = value
}
