package com.tcs.sample.cleanmvvm.data.remote

import com.tcs.sample.cleanmvvm.data.response.Product
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getProductsList(): Flow<List<Product>?>
    suspend fun getProduct(id: Int): Flow<Product?>
}