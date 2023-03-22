package com.tcs.sample.cleanmvvm.data.response

import com.google.gson.annotations.SerializedName

data class ProductListDto(
    @SerializedName("total")
    val total: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("skip")
    val skip: Int,

    @SerializedName("products")
    val `products`: List<ProductDetailDto>
)
