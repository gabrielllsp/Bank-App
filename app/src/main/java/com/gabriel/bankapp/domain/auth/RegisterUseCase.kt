package com.gabriel.bankapp.domain.auth

import com.gabriel.bankapp.data.model.User
import com.gabriel.bankapp.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RegisterUseCase@Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend fun invoke(user: User): User {
        return authFirebaseDataSourceImpl.register(user)
    }
}