package com.shihs.publisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shihs.publisher.data.Article
import com.shihs.publisher.data.Author
import com.shihs.publisher.databinding.DialogAddarticleBinding
import java.util.*

class AddDialog : DialogFragment() {

    lateinit var binding : DialogAddarticleBinding
    var me = Author(email = "test@gamil", id = "Steven123", name = "Steven")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddarticleBinding.inflate(inflater,container,false)

        btnSetup()


        return binding.root
    }

    fun btnSetup(){
        binding.button.setOnClickListener {
            addData()
            dismiss()
        }
    }

    fun addData() {
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        val document = articles.document()
        val title = binding.TitleET.text?.toString()
        val content = binding.contentET.text?.toString()
        val catagory = binding.catagoryET.text?.toString()
        val data = hashMapOf(
            "author" to me,
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance().time.toString(),
            "id" to document.id,
            "category" to catagory
        )
        document.set(data)
    }
}