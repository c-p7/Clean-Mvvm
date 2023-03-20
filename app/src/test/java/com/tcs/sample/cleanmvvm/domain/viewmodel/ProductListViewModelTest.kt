package com.tcs.sample.cleanmvvm.domain.viewmodel

import com.tcs.sample.cleanmvvm.domain.model.Product
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductsListUseCase
import com.tcs.sample.cleanmvvm.ui.productlist.ProductListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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