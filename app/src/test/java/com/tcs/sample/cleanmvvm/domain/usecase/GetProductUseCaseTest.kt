package com.tcs.sample.cleanmvvm.domain.usecase

import com.tcs.sample.cleanmvvm.data.repository.ProductsRepositoryImpl
import com.tcs.sample.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetProductUseCaseTest {

    private var repositoryImpl = mockk<ProductsRepositoryImpl>()

    private lateinit var getProductUseCase: GetProductUseCase

    @Before
    fun setUp() {
        getProductUseCase = GetProductUseCase(repositoryImpl)
    }

    @Test
    fun testProductUseCase() = runBlocking {

        coEvery { getProductUseCase.getProduct(1) } returns flow { emit(ProductDetail(1, "test")) }

        val result = getProductUseCase.getProduct(1).first()

        assertNotNull(result)
        if (result != null) {
            assertEquals("test", result.title)
        }
    }
}