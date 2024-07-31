package com.gabriel.bankapp.domain.auth

import com.gabriel.bankapp.data.repository.auth.AuthFirebaseDataSourceImpl

class RegisterUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend fun invoke(nome: String, email: String, phone: String, password: String) {
        return authFirebaseDataSourceImpl.register(nome, email, phone, password)
    }
}