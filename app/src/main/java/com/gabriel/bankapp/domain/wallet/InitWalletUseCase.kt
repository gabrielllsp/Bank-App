package com.gabriel.bankapp.domain.wallet

import com.gabriel.bankapp.data.model.Wallet
import com.gabriel.bankapp.data.repository.wallet.WalletDataSourceImpl
import javax.inject.Inject

class InitWalletUseCase @Inject constructor(
    private val walletDataSourceImpl: WalletDataSourceImpl
) {
    suspend operator fun invoke(wallet: Wallet){
        return walletDataSourceImpl.initWallet(wallet)
    }
}