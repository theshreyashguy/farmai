package com.example.farmai.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmai.R
import com.example.farmai.databinding.FragmentHomeBinding
import com.example.farmai.databinding.FragmentSettingBinding
import com.example.farmai.ui.Adapter.BlogAdapter
import com.example.farmai.ui.Adapter.BlogAdapter1
import com.example.farmai.ui.Adapter.BlogItem
import com.example.farmai.ui.home.HomeViewModel


class Setting : Fragment() {
    private var _binding: FragmentSettingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.blogRecyclerView

        // Create a list of BlogItem objects


        val blogList = listOf(
            BlogItem("90 Days Temperature Variation","","a1"),
            BlogItem("Average Temperature Distribution","","a2"),
            BlogItem("Fertilizer Analysis","","a3"),
            BlogItem("Crop Co-Occurence Matrix","","a4"),
            BlogItem("Anomaly Detection","","a5"),
            BlogItem("Crop Rotation Data","","a6"),


        )

        // Create and set the adapter
        val adapter = BlogAdapter1( blogList, requireContext().applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}