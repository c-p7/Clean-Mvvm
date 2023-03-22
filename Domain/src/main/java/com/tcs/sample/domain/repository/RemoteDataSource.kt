package com.tcs.sample.domain.repository

import com.tcs.sample.domain.model.ProductDetail
import com.tcs.sample.domain.model.ProductList
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getProductsList(): Flow<ProductList?>
    suspend fun getProduct(id: Int): Flow<ProductDetail?>
}