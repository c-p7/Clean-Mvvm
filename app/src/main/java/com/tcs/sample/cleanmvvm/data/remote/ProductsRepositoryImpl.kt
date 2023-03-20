package com.tcs.sample.cleanmvvm.data.remote

import android.util.Log
import com.tcs.sample.cleanmvvm.data.response.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ProductsRepositoryImpl (private val apiServices: ApiService) : RemoteDataSource {
    private val TAG = ProductsRepositoryImpl::class.simpleName

    override suspend fun getProductsList(): Flow<List<Product>?> {
        Log.d(TAG, "==>> getProductsList")

        return try {
            val result = apiServices.getProductsList()

            flow {
                if (result.isSuccessful) {
                    emit(result.body()?.products)
                    Log.d(TAG, "Success Response ==>> ${result.body().toString()}")
                } else
                    emit(emptyList<Product>())
                Log.d(TAG, "Error Response ==>> ${result.errorBody().toString()}")
            }.flowOn(Dispatchers.IO)
        } catch (exp : Exception) {
            Log.d(TAG, "Exception ==>> ${exp.localizedMessage}")
            flow {
                emit(emptyList<Product>())
            }.flowOn(Dispatchers.IO)
        }
    }

    override suspend fun getProduct(id: Int): Flow<Product?> {
        Log.d(TAG, "getProductsList id ==>> $id")

        return try {
            val result = apiServices.getProduct(id)

            flow {
                if (result.isSuccessful) {
                    emit(result.body())
                    Log.d(TAG, "Success Response ==>> ${result.body().toString()}")
                } else {
                    emit(Product())
                    Log.d(TAG, "Error Response ==>> ${result.errorBody().toString()}")
                }
            }.flowOn(Dispatchers.IO)
        } catch (exp: Exception) {
            Log.d(TAG, "Exception ==>> ${exp.localizedMessage}")

            flow {
                emit(Product())
            }.flowOn(Dispatchers.IO)
        }
    }
}