package com.example.harajtask.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.harajtask.databinding.FragmentDetailsBinding
import com.example.harajtask.utils.getDateString

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    
    lateinit var postTitle:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupDetailsViews()

        binding.shareIv.setOnClickListener { shareData() }

    }

    private fun setupDetailsViews() {
        arguments?.let {
            DetailsFragmentArgs.fromBundle(it).post.apply {
                postTitle = this.title
                binding.postTitle.text = postTitle
                binding.username.text = this.username
                binding.postTime.text = this.date.getDateString()
                binding.locationCity.text = this.city
                binding.postBody.text = this.body

                Glide.with(binding.root.context)
                    .load(this.thumbURL)
                    .into(binding.postIV)
            }
        }
    }

    private fun shareData() {
        val sendIntent:Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,postTitle)
            type ="text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)
    }
}