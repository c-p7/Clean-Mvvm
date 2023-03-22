package com.tcs.sample.cleanmvvm.domain.usecase

import com.tcs.sample.cleanmvvm.data.repository.ProductsRepositoryImpl
import com.tcs.sample.cleanmvvm.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.model.ProductList
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductsListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class GetProductListUseCaseTest {

    private var repositoryImpl = mockk<ProductsRepositoryImpl>()

    private lateinit var getProductsListUseCase: GetProductsListUseCase

    @Before
    fun setUp() {
        this.getProductsListUseCase = GetProductsListUseCase(repositoryImpl)
    }

    @Test
    fun testProductListUseCase() = runBlocking {
        val expectedList = listOf<ProductDetail>(ProductDetail(1, "test"))
        val expectedProductList = ProductList(expectedList)
        coEvery { getProductsListUseCase.getProductList() } returns flow {
            emit(expectedProductList)
        }

        val result = getProductsListUseCase.getProductList().first()

        assertNotNull(result)
        if (result != null) {
            assert(expectedProductList == result)
            MatcherAssert.assertThat(
                "Received result [$result] & mocked [$expectedProductList] must be matches on each other!",
                result,
                CoreMatchers.`is`(expectedProductList))
        }
    }
}