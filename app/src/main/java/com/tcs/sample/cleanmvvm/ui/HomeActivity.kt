package com.tcs.sample.cleanmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.tcs.sample.cleanmvvm.R
import com.tcs.sample.cleanmvvm.databinding.LayoutHomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val TAG = HomeActivity::class.simpleName

    private lateinit var binding: LayoutHomeActivityBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==>> onCreate")

        binding = LayoutHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        Log.d(TAG, "==>> setupBottomNavigation")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_product_list)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    public fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    public fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}