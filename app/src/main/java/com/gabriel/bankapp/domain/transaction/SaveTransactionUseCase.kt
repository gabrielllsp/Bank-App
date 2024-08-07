package com.gabriel.bankapp.domain.transaction

import com.gabriel.bankapp.data.model.Transaction
import com.gabriel.bankapp.data.repository.transaction.TransactionDataSourceImpl
import javax.inject.Inject

class SaveTransactionUseCase @Inject constructor(
    private val transactionDataSourceImpl: TransactionDataSourceImpl
) {
    suspend operator fun invoke(transaction: Transaction) {
        transactionDataSourceImpl.saveTransaction(transaction)
    }
}