package com.shihs.publisher.data

import com.google.firebase.Timestamp
import java.util.*

data class Article(
    var author : Author? = null,
    var title : String? = null,
    var content: String? = null,
    var createdTime: String? = null,
    var id: String? = null,
    var category: String? = null
)

data class Author (
    var email: String? = null,
    var id: String? = null,
    var name: String? = null
)
