package com.example.testapiapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapiapplication.R
import com.example.testapiapplication.databinding.FragmentHomeBinding
import com.example.testapiapplication.response.DataResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

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

        viewModel.fetchData(10.7589616, 106.692952)
        val recyclerView = binding.listItem
        recyclerView.adapter = ItemListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        viewLifecycleOwner.lifecycleScope.launch {
            val osmIds = mutableListOf<String>()
            viewModel.dataResponses.collect { dataResponses ->
                dataResponses?.let {
                    for(item: DataResponse in it) {
                        osmIds.add(item.osm_id.toString())
                    }
                    (recyclerView.adapter as ItemListAdapter).submitList(osmIds)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}