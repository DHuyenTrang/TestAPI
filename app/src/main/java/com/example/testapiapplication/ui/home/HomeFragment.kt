package com.example.testapiapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapiapplication.databinding.FragmentHomeBinding
import com.example.testapiapplication.data.response.DataResponse
import com.example.testapiapplication.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.listItem
        recyclerView.adapter = ItemListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewLifecycleOwner.lifecycleScope.run {
            launch { observeProfile() }
            launch { observeOsmID() }
        }
    }

    private suspend fun observeProfile() {
        profileViewModel.profileResponse.collect { profile ->
            profile?.let {
                binding.tvSignInProviderValue.text = it.sign_in_provider
            }
        }
    }
    private suspend fun observeOsmID() {
        val osmIds = mutableListOf<String>()
        homeViewModel.dataResponses.collect { dataResponses ->
            dataResponses?.let {
                for(item: DataResponse in it) {
                    osmIds.add(item.osm_id.toString())
                }
                (binding.listItem.adapter as ItemListAdapter).submitList(osmIds)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}