package com.tcs.sample.cleanmvvm.domain.viewmodel

import com.tcs.sample.cleanmvvm.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductUseCase
import com.tcs.sample.cleanmvvm.ui.product.ProductViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {

    private var getProductUseCase = mockk<GetProductUseCase>()

    private lateinit var productViewModel: ProductViewModel

    @Before
    fun setup() {
        productViewModel = ProductViewModel(getProductUseCase)
    }

    @Test
    fun testProductViewModel() = runBlocking {
        val expectedProduct = ProductDetail(1, "test")
        coEvery { getProductUseCase.getProduct(1) } returns flow {
            emit(expectedProduct)
        }

        productViewModel.getSingleProduct(1)
        var resultProduct = productViewModel.resultProduct.first()

        assertNotNull(resultProduct)
        assert(resultProduct == expectedProduct)
    }
}