package com.tcs.sample.domain.usecases

import com.tcs.sample.domain.repository.RemoteDataSource
import javax.inject.Inject

class GetProductsListUseCase  @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProductsList() = remoteDataSource.getProductsList()
}