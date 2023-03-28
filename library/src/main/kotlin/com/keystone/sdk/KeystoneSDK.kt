package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.MultiAccounts

class KeystoneSDK(chains: Array<ChainType>? = ChainType.values()): KeystoneBaseSDK() {
    lateinit var eth: KeystoneEthereumSDK
    lateinit var sol: KeystoneSolanaSDK
    private var chains: Array<ChainType> = ChainType.values()

    enum class ChainType {
        ETH, SOL
    }

    init {
        if (chains != null) {
            this.chains = chains
        }
        this.chains.forEach { initChain(it) }
    }

    private fun initChain(chainType: ChainType) {
        when (chainType) {
            ChainType.ETH -> eth = KeystoneEthereumSDK()
            ChainType.SOL -> sol = KeystoneSolanaSDK()
        }
    }

    private fun setChainMaxFragmentLen(chainType: ChainType) {
        when (chainType) {
            ChainType.ETH -> eth.maxFragmentLen = maxFragmentLen
            ChainType.SOL -> sol.maxFragmentLen = maxFragmentLen
        }
    }

    override var maxFragmentLen: Int = 100
    set(value) {
        field = value
        chains.forEach { setChainMaxFragmentLen(it) }
    }

    fun parseMultiAccounts(cborHex: String): MultiAccounts {
        val jsonStr = native.parseCryptoMultiAccounts(cborHex)
        val result = Gson().fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }
}
