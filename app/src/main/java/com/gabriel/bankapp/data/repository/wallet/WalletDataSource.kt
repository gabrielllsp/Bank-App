package com.gabriel.bankapp.data.repository.wallet

import com.gabriel.bankapp.data.model.Wallet

interface WalletDataSource {

    suspend fun initWallet(wallet: Wallet)

    suspend fun getWallet(): Wallet
}