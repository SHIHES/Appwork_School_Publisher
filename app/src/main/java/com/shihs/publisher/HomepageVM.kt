package com.shihs.publisher

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.shihs.publisher.data.Article

class HomepageVM : ViewModel() {

    val articleList = MutableLiveData<List<Article>>()

    init {
        loadData()
    }

    val emptyList = mutableListOf<Article>()

    fun loadData() {

        val articles = FirebaseFirestore.getInstance().collection("articles")
            .orderBy("createdTime", Query.Direction.DESCENDING)

        articles
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.d("SS", "Loading Error ${e}")
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    emptyList.clear()
                    Log.d("SS", "New post ${snapshot}")
                    for (document in snapshot.toObjects<Article>()) {

                        emptyList.add(document)
                        articleList.value = emptyList
                        Log.d("SS", "addSnapshotListener ${document.id}")
                    }
                }
            }
    }
}