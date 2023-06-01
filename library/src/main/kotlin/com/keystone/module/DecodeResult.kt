package com.keystone.module

import com.sparrowwallet.hummingbird.UR

data class DecodeResult(
    val progress: Int,
    val ur: UR? = null
)
