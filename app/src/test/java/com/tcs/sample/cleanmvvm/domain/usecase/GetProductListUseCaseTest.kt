package com.tcs.sample.cleanmvvm.domain.usecase

import com.tcs.sample.cleanmvvm.data.remote.ProductsRepositoryImpl
import com.tcs.sample.cleanmvvm.data.response.Product
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductsListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class GetProductListUseCaseTest {

    private var repositoryImpl = mockk<ProductsRepositoryImpl>()

    private lateinit var getProductsListUseCase: GetProductsListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.getProductsListUseCase = GetProductsListUseCase(repositoryImpl)
    }

    @Test
    fun testProductListUseCase() = runBlocking {
        val expectedList = listOf<Product>(Product(1, "test"))
        coEvery { getProductsListUseCase.getProductList() } returns flow { emit(listOf<Product>(Product(1, "test"))) }

        val result = getProductsListUseCase.getProductList().first()

        assertNotNull(result)
        if (result != null) {
            assert(expectedList == result)
            MatcherAssert.assertThat(
                "Received result [$result] & mocked [$expectedList] must be matches on each other!",
                result,
                CoreMatchers.`is`(expectedList))
        }
    }
}