package com.example.library2kotlin.service

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.mapper.BookMapper
import com.example.library2kotlin.model.dto.book.BookAuthorShortDTO
import com.example.library2kotlin.model.dto.book.BookListDTO
import com.example.library2kotlin.model.dto.book.NewBookShortDTO
import com.example.library2kotlin.model.dto.book.UpdateBookDTO
import com.example.library2kotlin.model.entity.BookEntity
import com.example.library2kotlin.repository.AuthorRepository
import com.example.library2kotlin.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository, private val authorRepository: AuthorRepository) {
    fun postBook(newBookShortDTO: NewBookShortDTO): BookAuthorShortDTO {
        val author = authorRepository.findById(newBookShortDTO.authorId)
            .orElseThrow { NoEntityException("No author with id=${newBookShortDTO.authorId}") }
        val book = newBookShortDTO.let { BookMapper.shortPostDtoToEntity(it) }
        book.author = author
        return bookRepository.save(book).let { BookMapper.entityToBookGetDto(it) }
    }

    fun getBookById(id: Long) = bookRepository.findById(id).orElseThrow { NoEntityException("No book with id=$id") }
        .let { BookMapper.entityToBookGetDto(it) }

    fun getAllBooks(): BookListDTO = BookListDTO(
        bookRepository.findAll()
            .map(BookMapper::entityToBookGetDto)
    )

    fun deleteBook(id: Long): BookAuthorShortDTO {
        val bookEntity: BookEntity =
            bookRepository.findById(id).orElseThrow { NoEntityException("No book with id=$id") }
        bookRepository.deleteById(id)
        return bookEntity.let { BookMapper.entityToBookGetDto(it) }
    }

    fun updateBook(id: Long, updateBookDTO: UpdateBookDTO): BookAuthorShortDTO {
        val oldBookEntity: BookEntity =
            bookRepository.findById(id).orElseThrow { NoEntityException("No book with id=$id") }
        val newBookEntity: BookEntity = updateBookDTO.let { BookMapper.updateDtoToEntity(it) }
        newBookEntity.id = id
        newBookEntity.author = oldBookEntity.author
        return bookRepository.save(newBookEntity).let { BookMapper.entityToBookGetDto(it) }
    }
}