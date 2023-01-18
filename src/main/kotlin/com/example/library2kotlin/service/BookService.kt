package com.example.library2kotlin.service

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.mapper.BookMapper
import com.example.library2kotlin.model.dto.book.BookAuthorShortDTO
import com.example.library2kotlin.model.dto.book.BookListDTO
import com.example.library2kotlin.model.dto.book.NewBookShortDTO
import com.example.library2kotlin.model.entity.BookEntity
import com.example.library2kotlin.repository.AuthorRepository
import com.example.library2kotlin.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class BookService(val bookRepository: BookRepository, val authorRepository: AuthorRepository) {
    fun postBook(newBookShortDTO: NewBookShortDTO): BookAuthorShortDTO {
        val author = authorRepository.findById(newBookShortDTO.authorId!!)
            .orElseThrow { NoEntityException("No author with that id") }
        val book = BookMapper.shortPostDtoToEntity(newBookShortDTO)
        book.author = author
        return BookMapper.entityToBookGetDto(bookRepository.save(book))
    }

    fun getBookById(id: Long) = BookMapper.entityToBookGetDto(
        bookRepository.findById(id).orElseThrow { NoEntityException("No book with that Id") })

    fun getAllBooks(): BookListDTO = BookListDTO(
        bookRepository.findAll().stream()
            .map(BookMapper::entityToBookGetDto)
            .collect(Collectors.toList())
    )

    fun deleteBook(id: Long): BookAuthorShortDTO {
        val bookEntity: BookEntity =
            bookRepository.findById(id).orElseThrow { NoEntityException("No book with that id") }
        bookRepository.deleteById(id)
        return BookMapper.entityToBookGetDto(bookEntity)
    }
}