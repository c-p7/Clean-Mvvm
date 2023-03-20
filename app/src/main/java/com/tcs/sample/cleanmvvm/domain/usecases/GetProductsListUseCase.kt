package com.tcs.sample.cleanmvvm.domain.usecases

import com.tcs.sample.cleanmvvm.data.remote.RemoteDataSource
import com.tcs.sample.cleanmvvm.data.response.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProductList(): Flow<List<Product>?> = remoteDataSource.getProductsList()
}