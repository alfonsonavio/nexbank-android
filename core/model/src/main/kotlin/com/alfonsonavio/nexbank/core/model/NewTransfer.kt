package com.alfonsonavio.nexbank.core.model

/** A transfer the user wants to send, validated before it reaches the network. */
data class NewTransfer(
    val sourceAccountId: AccountId,
    val destinationIban: Iban,
    val beneficiaryName: String,
    val amount: Money,
    val concept: String?,
) {
    init {
        require(beneficiaryName.isNotBlank()) { "Beneficiary name must not be blank" }
        require(beneficiaryName.length <= MAX_BENEFICIARY_NAME_LENGTH) { "Beneficiary name is too long" }
        require(concept == null || concept.length <= MAX_CONCEPT_LENGTH) { "Concept is too long" }
        require(!amount.isNegative && !amount.isZero) { "Transfer amount must be positive" }
    }

    companion object {
        const val MAX_BENEFICIARY_NAME_LENGTH = 70
        const val MAX_CONCEPT_LENGTH = 140
    }
}
