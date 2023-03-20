package com.tcs.sample.cleanmvvm.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tcs.sample.cleanmvvm.data.response.Product
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductsListUseCase
import com.tcs.sample.cleanmvvm.ui.productlist.ProductListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class ProductListViewModelTest {

    private var getProductsListUseCase = mockk<GetProductsListUseCase>()

    private lateinit var mainViewModel: ProductListViewModel


    @Before
    fun setup() {
        mainViewModel = ProductListViewModel(getProductsListUseCase)
    }

    @Test
    fun testProductListViewModel() = runBlocking {

        coEvery { getProductsListUseCase.getProductList() } returns flow {
            emit(listOf<Product>(Product(1)))
        }
        mainViewModel.getProducts()
        var productResult = mainViewModel.resultProductList.first()

        if (productResult != null) {
            assert(productResult.isNotEmpty())
        }
        if (productResult != null) {
            assert(productResult.size == 1)
        }
    }
}