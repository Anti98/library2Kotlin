package com.example.library2kotlin.mapper

import com.example.library2kotlin.model.dto.author.AuthorDTO
import com.example.library2kotlin.model.dto.author.AuthorShortDTO
import com.example.library2kotlin.model.dto.author.AuthorUpdateDTO
import com.example.library2kotlin.model.dto.author.NewAuthorDTO
import com.example.library2kotlin.model.entity.AuthorEntity
import org.springframework.stereotype.Component

@Component
class AuthorMapper {
    companion object {
        fun entityToDto(authorEntity: AuthorEntity) = AuthorDTO(
            authorEntity.id!!,
            authorEntity.name,
            authorEntity.lastName,
            authorEntity.secondName,
            authorEntity.birthDate,
            authorEntity.books.map { BookMapper.entityToBookShortDto(it) }
                .toSet()
        )

        fun postDtoToEntity(newAuthorDTO: NewAuthorDTO) = AuthorEntity(
            id = null,
            newAuthorDTO.name,
            newAuthorDTO.secondName,
            newAuthorDTO.lastName,
            newAuthorDTO.birthDate,
            books = emptySet()
        )

        fun entityToAuthorShortDto(authorEntity: AuthorEntity) = AuthorShortDTO(
            authorEntity.id!!,
            authorEntity.name,
            authorEntity.lastName,
            authorEntity.secondName,
            authorEntity.birthDate
        )
        fun updateDtoToEntity(authorUpdateDTO: AuthorUpdateDTO) = AuthorEntity(
            id = null,
            authorUpdateDTO.name,
            authorUpdateDTO.secondName,
            authorUpdateDTO.lastName,
            authorUpdateDTO.birthDate,
            books = emptySet()
        )
    }
}