package com.tcs.sample.cleanmvvm.di

import com.tcs.sample.cleanmvvm.domain.usecases.GetProductUseCase
import com.tcs.sample.domain.usecases.GetProductsListUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ModuleDependencies {
    fun getProductUseCase(): GetProductUseCase
    fun getProductsListUseCase(): GetProductsListUseCase
}