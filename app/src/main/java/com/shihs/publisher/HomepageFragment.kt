package com.shihs.publisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shihs.publisher.data.Article
import com.shihs.publisher.databinding.FragmentHomepageBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null

    private lateinit var viewModel : HomepageVM

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        val adapter =  ArticleAdapter()
        viewModel = HomepageVM()


        binding.postRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.postRV.adapter = adapter

        viewModel.articleList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            Log.d("SS", "articleList ${it}")
            Log.d("SS", "articleList ${it.size}")
        }

        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}