package com.tcs.sample.cleanmvvm.data

import com.tcs.sample.cleanmvvm.data.remote.ApiService
import com.tcs.sample.cleanmvvm.data.repository.ProductsRepositoryImpl
import com.tcs.sample.cleanmvvm.domain.model.Product
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
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
        MockKAnnotations.init(this) //for initialization
        this.productsRepositoryImpl = ProductsRepositoryImpl(apiService)
    }

    @Test
    fun getProductListData() = runBlocking {
        val expectedList = listOf<Product>(Product(1, "test"))
        coEvery { productsRepositoryImpl.getProductsList() } returns flow { emit(listOf<Product>(
            Product(1, "test")
        )) }

        val result = productsRepositoryImpl.getProductsList().first()

        assertNotNull(result)
        assertThat(
            "Received result [$result] & mocked [$expectedList] must be matches on each other!",
            result,
            CoreMatchers.`is`(expectedList)
        )
    }
}