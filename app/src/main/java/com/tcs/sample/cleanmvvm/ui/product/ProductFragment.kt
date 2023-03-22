package com.tcs.sample.cleanmvvm.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tcs.sample.cleanmvvm.ui.HomeActivity
import com.tcs.sample.cleanmvvm.R
import com.tcs.sample.cleanmvvm.databinding.LayoutProductDetailsFragmentBinding
import com.tcs.sample.cleanmvvm.di.DaggerAppComponent
import com.tcs.sample.cleanmvvm.di.ModuleDependencies
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private val TAG = ProductFragment::class.simpleName

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: LayoutProductDetailsFragmentBinding
    private val args: ProductFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")

        initCoreDependentInjection()
        binding = LayoutProductDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreateView")

        (requireActivity() as HomeActivity).showProgressBar()
        binding.noDataContainer.visibility = View.GONE
        binding.dataContainer.visibility = View.GONE

        lifecycleScope.launchWhenCreated {
            viewModel.resultProduct.collect { result ->

                (requireActivity() as HomeActivity).hideProgressBar()

                if (result.id == 0) {
                    emptyData()

                } else {
                    binding.product = result
                    Log.d(TAG, "viewModel resultProduct result size ==>> $result")
                    setUpDataContainerVisibleUi()

                    if (result.images != null && result.images!!.isNotEmpty())
                        Glide.with(binding.productImage)
                            .load(result.images!![0])
                            .placeholder(R.drawable.ic_product_holder)
                            .into(binding.productImage)
                }
            }
        }

        viewModel.getSingleProduct(args.id)
    }

    private fun emptyData() {
        binding.noDataContainer.visibility = View.VISIBLE
        binding.dataContainer.visibility = View.GONE
    }

    private fun setUpDataContainerVisibleUi() {
        binding.noDataContainer.visibility = View.GONE
        binding.dataContainer.visibility = View.VISIBLE
    }

    private fun initCoreDependentInjection() {

        val coreModuleDependencies = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            ModuleDependencies::class.java
        )

        DaggerAppComponent.factory().create(
            dependentModule = coreModuleDependencies,
            fragment = this
        ).inject(this)
    }
}