package com.example.testapiapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testapiapplication.databinding.ActivityMainBinding
import com.example.testapiapplication.ui.home.HomeViewModel
import com.example.testapiapplication.ui.profile.ProfileViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val tokenManager: TokenManager by inject()
    private val profileViewModel: ProfileViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val controller = navHostFragment.navController
        val bottomNavigationView = binding.bottomMenu

        bottomNavigationView.setupWithNavController(controller)
        controller.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id) {
                R.id.signInFragment -> bottomNavigationView.visibility = View.GONE
                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }

        if (checkCurrentUser()) {
            controller.navigate(R.id.profileFragment)
        }
    }

    private fun checkCurrentUser(): Boolean {
        return tokenManager.getUserId() != null
    }
}