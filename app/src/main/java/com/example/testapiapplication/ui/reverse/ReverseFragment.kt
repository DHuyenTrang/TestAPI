package com.example.testapiapplication.ui.reverse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapiapplication.databinding.FragmentReverseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReverseFragment : Fragment() {
    private var _binding: FragmentReverseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DataReverseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReverseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData(21.001118, 105.795889)

        val recyclerView = binding.listItem
        val adapter = DataReverseAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        observeDataReverse()
    }

    private fun observeDataReverse() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataReverse.collect { dataReverse ->
                (binding.listItem.adapter as DataReverseAdapter).submitList(dataReverse)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}