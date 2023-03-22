package com.tcs.sample.domain.usecases

import com.tcs.sample.domain.repository.RemoteDataSource
import javax.inject.Inject

class GetProductUseCase  @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProduct(id: Int) = remoteDataSource.getProduct(id)
}