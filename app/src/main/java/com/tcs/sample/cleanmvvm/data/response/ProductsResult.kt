package com.tcs.sample.cleanmvvm.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductsResult(
    @SerializedName("total")
    val total: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("skip")
    val skip: Int,

    @SerializedName("products")
    val products: List<Product>
)
