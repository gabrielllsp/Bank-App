package com.gabriel.bankapp.data.transaction_enum

enum class TransactionOperation {
    DEPOSIT;

    companion object{
        fun getOperation(operation: TransactionOperation): String {
            return when (operation) {
                DEPOSIT -> "DEPÓSITO"
            }
        }
    }
}