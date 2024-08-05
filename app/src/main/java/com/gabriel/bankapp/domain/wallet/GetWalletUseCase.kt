package com.gabriel.bankapp.domain.wallet

import com.gabriel.bankapp.data.model.Wallet
import com.gabriel.bankapp.data.repository.wallet.WalletDataSourceImpl
import javax.inject.Inject

class GetWalletUseCase @Inject constructor(
    private val walletDataSourceImpl: WalletDataSourceImpl
) {
    suspend operator fun invoke(): Wallet {
        return walletDataSourceImpl.getWallet()
    }
}