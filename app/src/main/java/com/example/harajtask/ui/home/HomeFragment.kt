package com.example.harajtask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.adapter.PostsRecyclerAdapter
import com.example.harajtask.databinding.FragmentHomeBinding
import com.example.harajtask.model.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),PostsRecyclerAdapter.Interaction {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: PostsRecyclerAdapter

    @VisibleForTesting
    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.fetchAllData()

        viewModel.result.observe(viewLifecycleOwner){
            it?.apply {
                mAdapter.submitList(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.postsRv.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            mAdapter =
                PostsRecyclerAdapter(this@HomeFragment)
            adapter = mAdapter
        }
    }

    private fun goToDetails(post: Post){
        val action =
            HomeFragmentDirections.actionGoToDetails(post)
        findNavController().navigate(action)
    }


    override fun onItemSelected(position: Int, item: Post) {
        goToDetails(item)
    }


}