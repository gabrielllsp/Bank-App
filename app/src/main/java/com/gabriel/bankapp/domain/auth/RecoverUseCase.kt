package com.gabriel.bankapp.domain.auth

import com.gabriel.bankapp.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RecoverUseCase@Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend fun invoke(email: String) {
        return authFirebaseDataSourceImpl.recover(email)
    }
}