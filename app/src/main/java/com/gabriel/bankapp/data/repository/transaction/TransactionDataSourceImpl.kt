package com.gabriel.bankapp.data.repository.transaction

import com.gabriel.bankapp.data.model.Transaction
import com.gabriel.bankapp.util.FirebaseHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class TransactionDataSourceImpl @Inject constructor(
    val database: FirebaseDatabase
) : TransactionDataSource {

    private val transactionReference = database.reference
        .child("transaction")
        .child(FirebaseHelper.getUserId())

    override suspend fun saveTransaction(transaction: Transaction) {
        return suspendCoroutine { continuation ->
            transactionReference
                .child(transaction.id)
                .setValue(transaction).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val dateReference = transactionReference
                            .child(transaction.id)
                            .child("date")
                        dateReference.setValue(ServerValue.TIMESTAMP)
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
        }
    }
}