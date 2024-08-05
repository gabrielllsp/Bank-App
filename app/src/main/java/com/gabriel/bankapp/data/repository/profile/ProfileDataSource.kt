package com.gabriel.bankapp.data.repository.profile

import com.gabriel.bankapp.data.model.User

interface ProfileDataSource {

    suspend fun saveProfile(user: User)
}