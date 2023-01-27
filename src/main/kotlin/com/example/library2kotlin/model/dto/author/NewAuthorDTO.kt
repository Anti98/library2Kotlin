package com.example.library2kotlin.model.dto.author

import java.time.LocalDate

data class NewAuthorDTO(
    var name: String,
    var lastName: String,
    var secondName: String,
    var birthDate: LocalDate
)
