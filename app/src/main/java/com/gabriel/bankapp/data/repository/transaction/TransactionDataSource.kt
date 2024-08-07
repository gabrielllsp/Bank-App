package com.gabriel.bankapp.data.repository.transaction

import com.gabriel.bankapp.data.model.Transaction

interface TransactionDataSource {

    suspend fun saveTransaction(transaction: Transaction)

    suspend fun getTransaction(): List<Transaction>
}