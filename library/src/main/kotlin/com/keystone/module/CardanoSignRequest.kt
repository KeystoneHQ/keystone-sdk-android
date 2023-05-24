package com.keystone.module

data class CardanoUtxo(
    val transactionHash: String,
    val index: Int,
    val amount: Int,
    val xfp: String,
    val hdPath: String,
    val address: String,
)

data class CardanoCertKey(
    val keyHash: String,
    val xfp: String,
    val keyPath: String = "",
)

data class CardanoSignRequest(
    val requestId: String,
    val signData: String,
    val utxos: List<CardanoUtxo>,
    val certKeys: List<CardanoCertKey>,
    val origin: String = "",
)
