package com.example.testapiapplication.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapiapplication.R
import com.example.testapiapplication.databinding.FragmentProfileBinding
import com.example.testapiapplication.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

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

        viewModel.fetchProfile()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profileResponse.collect { profile ->
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

        binding.btnLogOut.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.signInFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}