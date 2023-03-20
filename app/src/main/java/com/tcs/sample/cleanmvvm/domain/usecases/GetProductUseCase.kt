package com.tcs.sample.cleanmvvm.domain.usecases

import com.tcs.sample.cleanmvvm.data.remote.RemoteDataSource
import javax.inject.Inject

class GetProductUseCase  @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProduct(id: Int) = remoteDataSource.getProduct(id)
}