package com.tcs.sample.cleanmvvm.data.remote

import com.tcs.sample.cleanmvvm.Constants
import com.tcs.sample.cleanmvvm.data.response.ProductDetailDto
import com.tcs.sample.cleanmvvm.data.response.ProductListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.PRODUCT_LIST)
    suspend fun getProductsList(): Response<ProductListDto>

    @GET(Constants.PRODUCT)
    suspend fun getProduct(@Path("id") id: Int): Response<ProductDetailDto>
}