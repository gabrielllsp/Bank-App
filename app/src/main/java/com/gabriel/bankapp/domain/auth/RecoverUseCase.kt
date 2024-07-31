package com.gabriel.bankapp.domain.auth

import com.gabriel.bankapp.data.repository.auth.AuthFirebaseDataSourceImpl

class RecoverUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend fun invoke(email: String) {
        return authFirebaseDataSourceImpl.recover(email)
    }
}