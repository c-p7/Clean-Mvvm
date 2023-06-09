package com.tcs.sample.cleanmvvm.data.response

import com.google.gson.annotations.SerializedName

data class ProductDetailDto(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("rating")
    val rating: String? = null,

    @SerializedName("discountPercentage")
    val discountPercentage: String? = null,

    @SerializedName("stock")
    val stock: String? = null,

    @SerializedName("brand")
    val brand: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("images")
    val images: List<String>? = null
)
