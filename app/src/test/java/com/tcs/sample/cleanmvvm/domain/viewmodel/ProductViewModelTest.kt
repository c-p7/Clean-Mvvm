package com.tcs.sample.cleanmvvm.domain.viewmodel

import android.util.Log
import com.tcs.sample.cleanmvvm.domain.model.Product
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductUseCase
import com.tcs.sample.cleanmvvm.ui.product.ProductViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {

    @MockK
    private lateinit var getProductUseCase: GetProductUseCase

    private lateinit var productViewModel: ProductViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        this.productViewModel = ProductViewModel(getProductUseCase)
    }

    @Test
    fun testProductViewModel() = runBlocking {
        val expectedList = Product(1, "test")
        coEvery { getProductUseCase.getProduct(1) } returns flow {
            emit(Product(1, "test"))
        }
        productViewModel.getSingleProduct(1)
        var productResult = productViewModel.resultProduct.first()

        Log.d("sdkjfh", "" + {productResult == expectedList})
        assertNotNull(productResult)
        assert(productResult == expectedList)
    }
}