package com.keystone.module

import com.google.gson.annotations.SerializedName

public data class CryptoMultiAccounts(
    val device: String,
    val keys: Array<CryptoHDKey>,
    @SerializedName("master_fingerprint") val masterFingerprint: String,
) {
    override fun hashCode(): Int {
        var result = device.hashCode()
        // The use of 31 is a best practice that helps to ensure a good distribution of hash codes
        // and reduce the likelihood of collisions.
        result = 31 * result + keys.contentHashCode()
        result = 31 * result + masterFingerprint.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CryptoMultiAccounts

        if (device != other.device) return false
        if (!keys.contentEquals(other.keys)) return false
        if (masterFingerprint != other.masterFingerprint) return false

        return true
    }
}
