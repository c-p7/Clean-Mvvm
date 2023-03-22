package com.tcs.sample.cleanmvvm.domain.usecases

import com.tcs.sample.cleanmvvm.domain.repository.RemoteDataSource
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProductList() = remoteDataSource.getProductsList()
}