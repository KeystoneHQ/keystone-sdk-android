package com.keystone.module

data class NearSignRequest(
    val requestId: String,
    val signData: ArrayList<String>,
    val path: String,
    val xfp: String,
    val account: String = "",
    val origin: String = "",
)
