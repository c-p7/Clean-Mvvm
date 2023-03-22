package com.tcs.sample.cleanmvvm.data

import com.tcs.sample.cleanmvvm.data.remote.ApiService
import com.tcs.sample.cleanmvvm.data.repository.ProductsRepositoryImpl
import com.tcs.sample.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.model.ProductList
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {

    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @MockK
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mockk<ApiService>()
        this.productsRepositoryImpl = ProductsRepositoryImpl(apiService)
    }

    @Test
    fun getProductListData() = runBlocking {
        val expectedList = listOf<ProductDetail>(ProductDetail(1, "test"))
        val expectedProductList = ProductList(expectedList)
        coEvery { productsRepositoryImpl.getProductsList() } returns flow {
            emit(expectedProductList)
        }

        val result = productsRepositoryImpl.getProductsList().first()

        assertNotNull(result)
        assertThat(
            "Received result [$result] & mocked [$expectedProductList] must be matches on each other!",
            result,
            CoreMatchers.`is`(expectedList)
        )
    }
}