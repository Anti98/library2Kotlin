package com.example.library2kotlin.model.dto.book

import com.example.library2kotlin.model.dto.author.AuthorShortDTO

data class BookAuthorShortDTO(
    var id: Long,
    val title: String,
    var edition: String,
    var pageCount: Int,
    var author: AuthorShortDTO
)