package com.gabriel.bankapp.data.repository.auth

import com.gabriel.bankapp.data.model.User

interface AuthFirebaseDataSource {

    suspend fun login(email: String, password: String)

    suspend fun register(name: String, email: String,phone: String, password: String): User

    suspend fun recover(email: String)

}