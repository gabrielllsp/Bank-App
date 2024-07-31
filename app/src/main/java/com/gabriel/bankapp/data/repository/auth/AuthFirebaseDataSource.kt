package com.gabriel.bankapp.data.repository.auth

import com.google.firebase.auth.FirebaseUser

interface AuthFirebaseDataSource {

    suspend fun login(email: String, password: String)

    suspend fun register(nome: String, email: String, phone: String, password: String): FirebaseUser

    suspend fun recover(email: String)

}