package com.tcs.sample.cleanmvvm.domain.repository

import com.tcs.sample.cleanmvvm.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getProductsList(): Flow<List<Product>?>
    suspend fun getProduct(id: Int): Flow<Product?>
}