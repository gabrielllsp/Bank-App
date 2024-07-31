package com.gabriel.bankapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule  {

    @Singleton
    @Provides
    fun providesFirebaseDataBase() : FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}