package com.tcs.sample.cleanmvvm.domain.viewmodel

import com.tcs.sample.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.model.ProductList
import com.tcs.sample.domain.usecases.GetProductsListUseCase
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

        val expectedList = listOf<ProductDetail>(ProductDetail(1, "test"))
        val expectedProductList = ProductList(expectedList)

        coEvery { getProductsListUseCase.getProductList() } returns flow {
            emit(expectedProductList)
        }
        mainViewModel.getProducts()
        var productResult = mainViewModel.resultProductList.first()

        if (productResult?.products != null) {
            productResult?.products?.isNotEmpty()?.let { assert(it) }
        }
        if (productResult != null) {
            assert(productResult?.products?.size == 1)
        }
    }
}