package com.gabriel.bankapp.data.repository.deposit

import com.gabriel.bankapp.data.model.Deposit

interface DepositDataSource {

    suspend fun saveDeposit(deposit: Deposit): Deposit
}