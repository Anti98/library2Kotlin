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
                id = bookEntity.id!!,
                title = bookEntity.title,
                edition = bookEntity.edition,
                pageCount = bookEntity.pageCount,
                author = AuthorMapper.entityToAuthorShortDto(bookEntity.author!!)
            )

        fun shortPostDtoToEntity(newBookShortDto: NewBookShortDTO) =
            BookEntity(
                id = null,
                title = newBookShortDto.title,
                edition = newBookShortDto.edition,
                pageCount = newBookShortDto.pageCount,
                author = null
            )

        fun entityToBookShortDto(bookEntity: BookEntity) =
            BookShortDTO(
                id = bookEntity.id!!,
                title = bookEntity.title,
                edition = bookEntity.edition,
                pageCount = bookEntity.pageCount
            )

        fun updateDtoToEntity(updateBookDTO: UpdateBookDTO) = BookEntity(
            id = null,
            title = updateBookDTO.title,
            edition = updateBookDTO.edition,
            pageCount = updateBookDTO.pageCount,
            author = null
        )
    }
}