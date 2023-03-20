package com.tcs.sample.cleanmvvm.domain.usecases

import com.tcs.sample.cleanmvvm.domain.repository.RemoteDataSource
import javax.inject.Inject

class GetProductUseCase  @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProduct(id: Int) = remoteDataSource.getProduct(id)
}