package com.gabriel.bankapp.data.repository.profile

import com.gabriel.bankapp.data.model.User

interface ProfileRepository {

    suspend fun saveProfile(user: User)
}