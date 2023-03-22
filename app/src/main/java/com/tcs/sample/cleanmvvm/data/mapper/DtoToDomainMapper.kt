package com.tcs.sample.cleanmvvm.data.mapper

import com.tcs.sample.cleanmvvm.data.response.ProductDetailDto
import com.tcs.sample.cleanmvvm.data.response.ProductListDto
import com.tcs.sample.cleanmvvm.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.model.ProductList

fun ProductListDto.toDomain() = ProductList(
    products = products.map { it.toDomain() }
)

fun ProductDetailDto.toDomain() = ProductDetail(
    id = id,
    title = title,
    description = description,
    price = price,
    rating = rating,
    discountPercentage = discountPercentage,
    stock = stock,
    brand = brand,
    category = category,
    images = images
)