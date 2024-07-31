package com.gabriel.bankapp.domain.auth

import com.gabriel.bankapp.data.repository.auth.AuthFirebaseDataSourceImpl

class LoginUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend fun invoke(email: String, password: String){
        return authFirebaseDataSourceImpl.login(email, password)
    }
}