package com.tcs.sample.cleanmvvm.ui.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.sample.cleanmvvm.domain.model.ProductDetail
import com.tcs.sample.cleanmvvm.domain.usecases.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private var getProductUseCase: GetProductUseCase): ViewModel() {
    private val TAG = ProductViewModel::class.simpleName

    private val _resultProduct = Channel<ProductDetail>(Channel.BUFFERED)
    val resultProduct: Flow<ProductDetail> = _resultProduct.receiveAsFlow()

    fun getSingleProduct(id: Int) {
        Log.d(TAG, "getSingleProduct id ==>> $id")

        viewModelScope.launch(Dispatchers.IO) {

            getProductUseCase.getProduct(id).collect {
                Log.d(TAG, "getProducts ==>> $it")
                if (it != null) {
                    _resultProduct.send(it)
                }
            }
        }
    }
}