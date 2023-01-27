package com.example.library2kotlin.model.dto.author

import com.example.library2kotlin.model.dto.book.BookShortDTO
import java.time.LocalDate

data class AuthorDTO(
    var id: Long,
    var name: String,
    var lastName: String,
    var secondName: String,
    var birthDate: LocalDate,
    var books: Set<BookShortDTO>
)