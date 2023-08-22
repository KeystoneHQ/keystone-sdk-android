package com.keystone.utils

import com.keystone.module.HDPath
import com.keystone.module.NativeResult
import com.keystone.sdk.KeystoneBaseSDK

class CryptoPath: KeystoneBaseSDK() {
    public fun parseHDPath(hdPath: String): HDPath {
        val jsonStr = native.parseHDPath(hdPath);
        return fromJson(jsonStr, NativeResult::class.java).result as HDPath
    }
}
