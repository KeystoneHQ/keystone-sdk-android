package com.keystone.module

data class SuiAccount(
    val path: String,
    val xfp: String,
    val address: String = "",
)

data class SuiSignRequest(
    val requestId: String,
    val intentMessage: String,
    val accounts: List<SuiAccount>,
    val origin: String = "",
)
