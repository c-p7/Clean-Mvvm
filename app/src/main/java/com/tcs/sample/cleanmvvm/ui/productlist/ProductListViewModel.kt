package com.tcs.sample.cleanmvvm.ui.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.sample.domain.usecases.GetProductsListUseCase
import com.tcs.sample.domain.model.ProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private var getProductsListUseCase: GetProductsListUseCase) : ViewModel() {
    private val TAG = ProductListViewModel::class.simpleName

    private val _resultProductList = Channel<ProductList?>(Channel.BUFFERED)
    val resultProductList: Flow<ProductList?> = _resultProductList.receiveAsFlow()

    fun getProducts() {
        Log.d(TAG, "==>> getProducts")

        viewModelScope.launch(Dispatchers.IO) {
            getProductsListUseCase.getProductsList().collect {
                Log.d(TAG, "getProducts size ==>> ${it?.products?.size}")
                _resultProductList.send(it)
            }
        }
    }
}