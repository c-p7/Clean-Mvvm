package com.tcs.sample.cleanmvvm.data.repository

import android.util.Log
import com.tcs.sample.cleanmvvm.data.mapper.toDomain
import com.tcs.sample.cleanmvvm.data.remote.ApiService
import com.tcs.sample.domain.model.ProductDetail
import com.tcs.sample.domain.model.ProductList
import com.tcs.sample.domain.repository.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ProductsRepositoryImpl (private val apiServices: ApiService) : RemoteDataSource {
    private val TAG = ProductsRepositoryImpl::class.simpleName

    override suspend fun getProductsList(): Flow<ProductList?> {
        Log.d(TAG, "==>> getProductsList")

        return try {
            val result = apiServices.getProductsList()

            flow {
                if (result.isSuccessful) {
                    Log.d(TAG, "Success Response ==>> ${result.body().toString()}")
                    result.body()?.let { emit(it.toDomain()) }
                } else {
                    Log.d(TAG, "Error Response ==>> ${result.errorBody().toString()}")
                    emit(ProductList())
                }
            }.flowOn(Dispatchers.IO)

        } catch (exp : Exception) {
            Log.d(TAG, "Exception ==>> ${exp.localizedMessage}")

            flow {
                emit(ProductList())
            }.flowOn(Dispatchers.IO)
        }
    }

    override suspend fun getProduct(id: Int): Flow<ProductDetail?> {
        Log.d(TAG, "getProductsList id ==>> $id")

        return try {
            val result = apiServices.getProduct(id)

            flow {
                if (result.isSuccessful) {
                    Log.d(TAG, "Success Response ==>> ${result.body().toString()}")
                    result.body()?.let { emit(it.toDomain()) }
                } else {
                    Log.d(TAG, "Error Response ==>> ${result.errorBody().toString()}")
                    emit(ProductDetail())
                }
            }.flowOn(Dispatchers.IO)

        } catch (exp: Exception) {
            Log.d(TAG, "Exception ==>> ${exp.localizedMessage}")

            flow {
                emit(ProductDetail())
            }.flowOn(Dispatchers.IO)
        }
    }
}