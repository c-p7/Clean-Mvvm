package com.tcs.sample.domain.model

data class ProductDetail(
    val id: Int = 0,

    val title: String? = null,

    val description: String? = null,

    val price: String? = null,

    val rating: String? = null,

    val discountPercentage: String? = null,

    val stock: String? = null,

    val brand: String? = null,

    val category: String? = null,

    val thumbnail: String? = null,

    val images: List<String>? = null
)
