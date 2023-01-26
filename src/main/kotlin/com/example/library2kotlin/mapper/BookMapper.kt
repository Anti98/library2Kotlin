package com.example.library2kotlin.mapper

import com.example.library2kotlin.model.dto.book.BookAuthorShortDTO
import com.example.library2kotlin.model.dto.book.BookShortDTO
import com.example.library2kotlin.model.dto.book.NewBookShortDTO
import com.example.library2kotlin.model.dto.book.UpdateBookDTO
import com.example.library2kotlin.model.entity.BookEntity
import org.springframework.stereotype.Component

@Component
class BookMapper {
    companion object {
        fun entityToBookGetDto(bookEntity: BookEntity) =
            BookAuthorShortDTO(
                bookEntity.id!!,
                bookEntity.title,
                bookEntity.edition,
                bookEntity.pageCount,
                AuthorMapper.entityToAuthorShortDto(bookEntity.author!!)
            )

        fun shortPostDtoToEntity(newBookShortDto: NewBookShortDTO) =
            BookEntity(null, newBookShortDto.title, newBookShortDto.edition, newBookShortDto.pageCount, null)

        fun entityToBookShortDto(bookEntity: BookEntity) =
            BookShortDTO(bookEntity.id!!, bookEntity.title, bookEntity.edition, bookEntity.pageCount)

        fun updateDtoToEntity(updateBookDTO: UpdateBookDTO)=BookEntity(
            null,
            updateBookDTO.title,
            updateBookDTO.edition,
            updateBookDTO.pageCount,
            null
        )
    }
}