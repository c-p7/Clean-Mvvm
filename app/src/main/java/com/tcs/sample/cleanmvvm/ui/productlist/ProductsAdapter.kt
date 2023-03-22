package com.tcs.sample.cleanmvvm.ui.productlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcs.sample.cleanmvvm.R
import com.tcs.sample.cleanmvvm.databinding.LayoutProductItemRowBinding
import com.tcs.sample.cleanmvvm.domain.model.ProductDetail

class ProductsAdapter(private val products:List<ProductDetail>?, private val itemClick: ProductListItemClickListener) : RecyclerView.Adapter<ProductsAdapter.PeopleViewHolder>() {
    private val TAG = ProductsAdapter::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        Log.d(TAG, "onCreateViewHolder ==>>")

        val binding = LayoutProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        Log.d(TAG, "onCreateViewHolder position ==>> $position")
        products?.get(position)?.let { holder.bindView(it) }
    }

    override fun getItemCount(): Int {

        return if (products != null && products.isNotEmpty())
            products.size
        else 0
    }

    class PeopleViewHolder(private val binding: LayoutProductItemRowBinding,  private val itemClick: ProductListItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = PeopleViewHolder::class.simpleName

        fun bindView(item: ProductDetail) {

            Log.d(TAG, "bindView ==>> thumbnail ${item.thumbnail}")
            binding.product = item
            Glide.with(binding.productLogo)
                .load(item.thumbnail)
                .placeholder(R.drawable.ic_product_holder)
                .into(binding.productLogo)
            binding.container.setOnClickListener { itemClick.productListItemListClicked(item.id) }
        }
    }

    interface ProductListItemClickListener {
        fun productListItemListClicked(productId: Int)
    }
}