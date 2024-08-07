package com.gabriel.bankapp.data.model

import com.gabriel.bankapp.data.transaction_enum.TransactionOperation
import com.gabriel.bankapp.data.transaction_enum.TransactionType

data class Transaction(
    var id: String = "",
    val operation: TransactionOperation? = null,
    val date: Long = 0,
    val amount: Float = 0f,
    val type: TransactionType? = null
)