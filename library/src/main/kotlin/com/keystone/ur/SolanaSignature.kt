package com.keystone.ur

import com.google.gson.annotations.SerializedName

public data class SolanaSignature(
    @SerializedName("request_id") val requestId: String,
    val signature: String,
)

//
//{
//    @SerializedName("request_id")
//    val requestId;
//    val signature;
//
//    public getRequestId(){
//        return this.requestId
//    }
//
//    public getSignature(){
//        return this.signature
//    }
//}