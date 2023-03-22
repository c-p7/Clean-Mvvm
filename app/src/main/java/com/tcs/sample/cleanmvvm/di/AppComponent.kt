package com.tcs.sample.cleanmvvm.di

import androidx.fragment.app.Fragment
import com.tcs.sample.cleanmvvm.ui.product.ProductFragment
import com.tcs.sample.cleanmvvm.ui.productlist.ProductListFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ModuleDependencies::class])
interface AppComponent {
    fun inject(fragment: ProductFragment)
    fun inject(fragment: ProductListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: ModuleDependencies,
            @BindsInstance fragment: Fragment
        ): AppComponent
    }
}