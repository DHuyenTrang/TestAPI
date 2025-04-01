package com.example.testapiapplication.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapiapplication.R
import com.example.testapiapplication.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignin.setOnClickListener {
            val phoneNumber = binding.edtPhoneNumber.text.toString()
            val password = binding.edtPassword.text.toString()

            if (phoneNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.fetchUser(phoneNumber, password)
        }

        // Observe login result
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoginSuccessful.collect { isSuccess ->
                isSuccess?.let {
                    if (it) {
                        findNavController().navigate(R.id.action_signInFragment_to_profileFragment)
                    } else {
                        Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
