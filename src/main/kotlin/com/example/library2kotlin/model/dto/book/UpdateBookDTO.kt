package com.example.library2kotlin.model.dto.book

data class UpdateBookDTO(
    var title: String,
    var edition: String,
    var pageCount: Int
)