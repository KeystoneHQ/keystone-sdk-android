package com.keystone.module

data class UTXO(
    val publicKey: String,
    val value: Long,
    val script: String = "",
)

data class Input(
    val hash: String,
    val index: Int,
    val utxo: UTXO,
    val ownerKeyPath: String
)

data class Output(
    val address: String,
    val value: Long,
    val isChange: Boolean = false,
    val changeAddressPath: String = ""
)

data class LtcTx(
    val inputs: ArrayList<Input>,
    val outputs: ArrayList<Output>,
    val fee: Long,
    val memo: String = "",
    private val dustThreshold: Int = 5460
)

data class CashInput(
    val hash: String,
    val index: Int,
    val value: Long,
    val pubkey: String,
    val ownerKeyPath: String
)

data class CashTx(
    val inputs: ArrayList<CashInput>,
    val outputs: ArrayList<Output>,
    val fee: Long,
    val memo: String = "",
    private val dustThreshold: Int = 546
)
