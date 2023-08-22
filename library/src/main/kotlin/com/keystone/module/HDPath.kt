package com.keystone.module

data class PathItem(
    val index: Int,
    val hardened: Boolean,
)

data class HDPath(
    val purpose: PathItem?,
    val coinType: PathItem?,
    val account: PathItem?,
    val change: PathItem?,
    val addressIndex: PathItem?,
)
