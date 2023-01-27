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
            id = authorEntity.id!!,
            name = authorEntity.name,
            lastName = authorEntity.lastName,
            secondName = authorEntity.secondName,
            birthDate = authorEntity.birthDate,
            books = authorEntity.books.map { BookMapper.entityToBookShortDto(it) }
                .toSet()
        )

        fun postDtoToEntity(newAuthorDTO: NewAuthorDTO) = AuthorEntity(
            id = null,
            name = newAuthorDTO.name,
            secondName = newAuthorDTO.secondName,
            lastName = newAuthorDTO.lastName,
            birthDate = newAuthorDTO.birthDate,
            books = emptySet()
        )

        fun entityToAuthorShortDto(authorEntity: AuthorEntity) = AuthorShortDTO(
            id = authorEntity.id!!,
            name=authorEntity.name,
            lastName = authorEntity.lastName,
            secondName = authorEntity.secondName,
            birthDate = authorEntity.birthDate
        )

        fun updateDtoToEntity(authorUpdateDTO: AuthorUpdateDTO) = AuthorEntity(
            id = null,
            name=authorUpdateDTO.name,
            secondName = authorUpdateDTO.secondName,
            lastName = authorUpdateDTO.lastName,
            birthDate = authorUpdateDTO.birthDate,
            books = emptySet()
        )
    }
}