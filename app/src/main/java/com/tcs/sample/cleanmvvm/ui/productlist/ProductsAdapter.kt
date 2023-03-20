package com.tcs.sample.cleanmvvm.ui.productlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcs.sample.cleanmvvm.R
import com.tcs.sample.cleanmvvm.domain.model.Product
import com.tcs.sample.cleanmvvm.databinding.LayoutProductItemRowBinding

class ProductsAdapter(private val products:List<Product>?, private val itemClick: ProductListItemClickListener) : RecyclerView.Adapter<ProductsAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {

        val binding = LayoutProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        products?.get(position)?.let { holder.bindView(it) }
    }

    override fun getItemCount(): Int {

        return if (products != null && products.isNotEmpty())
            products?.size
        else 0
    }

    class PeopleViewHolder(private val binding: LayoutProductItemRowBinding,  private val itemClick: ProductListItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = PeopleViewHolder::class.simpleName

        fun bindView(item: Product) {

            Log.d(TAG, "==>> bindView ${binding.product}")
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