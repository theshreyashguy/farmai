package com.example.farmai.ui.home

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmai.R
import com.example.farmai.databinding.FragmentHomeBinding
import com.example.farmai.ui.Adapter.BlogAdapter
import com.example.farmai.ui.Adapter.BlogItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.blogRecyclerView

        // Create a list of BlogItem objects


        val blogList = listOf(
            BlogItem("The AgriTech Insider","Welcome to \"The AgriTech Insider\" – your gateway to the revolutionary world of agricultural technology. In this blog, we'll be delving deep into the latest trends, innovations, and game-changing technologies that are reshaping the agricultural landscape. From precision farming to crop management and AI-powered solutions, we are your trusted source for staying ahead in the AgriTech industry. Join us as we uncover the secrets and insights that are transforming the way we grow and harvest food.","img1"),
            BlogItem("AI Farmer's Journal","\"AI Farmer's Journal\" is your personal guide to the intersection of artificial intelligence and agriculture. In each entry, we explore the fascinating journey of farmers who are embracing AI to optimize their practices. Discover firsthand accounts of how AI is enhancing crop yields, improving resource management, and fostering sustainable farming. This journal is a testament to the power of human ingenuity and innovation in the age-old profession of farming.","img2"),
            BlogItem(" Smart Farming Today","In \"Smart Farming Today,\" we explore the present and future of farming. Join us as we uncover the latest smart farming techniques and technologies that are making agriculture more efficient, sustainable, and profitable. From IoT sensors in the fields to data-driven decision-making, we're here to show you how farmers are harnessing the power of intelligence to meet the challenges of today and tomorrow.","img3"),
            BlogItem(" Smart Farming Today","Welcome to \"Crop AI Chronicles,\" where we embark on a journey through the digital fields of agriculture. We'll take you on a virtual tour of how artificial intelligence is revolutionizing crop management, disease detection, and yield optimization. Each entry unveils a new story of AI applications in farming and their impact on farmers' lives. Join us as we explore the exciting world of Crop AI and its contribution to a greener, more productive future.","img4")
        )

        // Create and set the adapter
        val adapter = BlogAdapter( blogList, requireContext().applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}