package com.tcs.sample.cleanmvvm.ui.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.sample.cleanmvvm.domain.model.Product
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductsListUseCase
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

    private val _resultProductList = Channel<List<Product>?>(Channel.BUFFERED)
    val resultProductList: Flow<List<Product>?> = _resultProductList.receiveAsFlow()

    fun getProducts() {
        Log.d(TAG, "==>> getProducts")

        viewModelScope.launch(Dispatchers.IO) {
            getProductsListUseCase.getProductList().collect {
                Log.d(TAG, "getProducts size ==>> ${it?.size}")
                _resultProductList.send(it)
            }
        }
    }
}