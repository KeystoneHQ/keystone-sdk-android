package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.CashTx
import com.keystone.module.KeystoneSignRequest
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneDashSDK: KeystoneChainSDK() {
    fun generateSignRequest(signRequest: KeystoneSignRequest<CashTx>): UREncoder {
        val jsonStr = native.generateKeystoneSignRequest(
            signRequest.requestId,
            CoinType.DASH.value,
            toJson(signRequest.signData),
            signRequest.xfp,
            signRequest.origin,
            getTimestamp()
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
