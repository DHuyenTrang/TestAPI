package com.example.testapiapplication.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapiapplication.R
import com.example.testapiapplication.databinding.FragmentProfileBinding
import com.example.testapiapplication.databinding.FragmentSignInBinding
import com.example.testapiapplication.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.fetchProfile()  // Chờ API này hoàn thành
            homeViewModel.fetchData(10.7589616, 106.692952) // Sau đó mới gọi API này
        }

        binding.btnLogOut.setOnClickListener {
            profileViewModel.logout()
            findNavController().navigate(R.id.signInFragment)
        }

        viewLifecycleOwner.lifecycleScope.run {
            launch { observeProfile() }
            launch { observeHome() }
        }
    }

    private suspend fun observeHome() {
        homeViewModel.dataResponses.collect{ dataResponses ->
            dataResponses?.let {
                binding.tvOsmIdValue.text = it.first().osm_id.toString()
            }
        }
    }

    private suspend fun observeProfile() {
        profileViewModel.profileResponse.collect { profile ->
            profile?.let {
                binding.tvFlagValue.text = it.flag
                binding.tvImageValue.text = it.image
                binding.tvVideoValue.text = it.video
                binding.tvIconValue.text = it.icon
                binding.tvSecureValue.text = it.secure
                binding.tvUpdateValue.text = it.is_update_password
                binding.tvSignInProviderValue.text = it.sign_in_provider
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}