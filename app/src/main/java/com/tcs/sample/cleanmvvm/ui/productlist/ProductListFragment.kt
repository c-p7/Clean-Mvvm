package com.tcs.sample.cleanmvvm.ui.productlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.sample.cleanmvvm.ui.HomeActivity
import com.tcs.sample.cleanmvvm.databinding.LayoutProductListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductsAdapter.ProductListItemClickListener {
    private val TAG = ProductListFragment::class.simpleName

    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var binding: LayoutProductListFragmentBinding
    private lateinit var productListItemClickListener: ProductsAdapter.ProductListItemClickListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "==>> onCreateView")

        binding = LayoutProductListFragmentBinding.inflate(inflater, container, false)
        binding.productList.layoutManager = LinearLayoutManager(requireContext())
        productListItemClickListener = this
        (requireActivity() as HomeActivity).showProgressBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "==>> onViewCreated")

        lifecycleScope.launchWhenCreated {
            Log.d(TAG, "launchWhenCreated")
            viewModel.resultProductList.collect { result ->
                Log.d(TAG, "resultProductList ==>> collect")
                (requireActivity() as HomeActivity).hideProgressBar()

                if (result?.products != null && result.products?.isNotEmpty() == true) {
                    Log.d(TAG, "resultProductList ==>> collect : ${result.products?.size}")
                    binding.productList.adapter = ProductsAdapter(result.products, productListItemClickListener)
                    binding.productList.visibility = View.VISIBLE
                    binding.noDataContainer.visibility = View.GONE
                } else {
                    binding.productList.visibility = View.GONE
                    binding.noDataContainer.visibility = View.VISIBLE
                }
            }
        }

        viewModel.getProducts()
    }

    override fun productListItemListClicked(productId: Int) {
        Log.d(TAG, "productId ==>> $productId")
        findNavController().navigate(
            ProductListFragmentDirections.actionNavigationPeopleToNavigationDetail(productId))
    }
}