package com.keystone.utils

import com.keystone.module.HDPath
import com.keystone.sdk.KeystoneBaseSDK

class CryptoPath: KeystoneBaseSDK() {
    public fun parseHDPath(hdPath: String): HDPath {
        val jsonStr = native.parseHDPath(hdPath);
        return fromJson(jsonStr, HDPath::class.java)
    }
}
