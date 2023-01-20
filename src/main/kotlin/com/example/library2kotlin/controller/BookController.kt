package com.example.library2kotlin.controller

import com.example.library2kotlin.model.dto.book.BookAuthorShortDTO
import com.example.library2kotlin.model.dto.book.BookListDTO
import com.example.library2kotlin.model.dto.book.NewBookShortDTO
import com.example.library2kotlin.service.BookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book")
@Tag(name = "Книги", description = "API для работы с книгами")
class BookController(private val bookService: BookService) {
    @GetMapping("/{id}")
    @Operation(summary = "Получение книги по id", description = "Получение книги по id")
    fun getBookById(@PathVariable id: Long) = bookService.getBookById(id)

    @PostMapping
    @Operation(summary = "Добавление книги", description = "Добавление книги")
    fun postBook(@RequestBody newBookShortDTO: NewBookShortDTO) = bookService.postBook(newBookShortDTO)

    @GetMapping
    @Operation(summary = "Получение всех книг", description = "Получение всех книг")
    fun getAllBooks(): BookListDTO = bookService.getAllBooks()

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги", description = "Удаление книги по id")
    fun deleteBook(@PathVariable id: Long): BookAuthorShortDTO = bookService.deleteBook(id)

    @PutMapping("/{id}")
    @Operation(summary = "Обновление книги", description = "Обновление книги по id")
    fun updateBook(@PathVariable id: Long, @RequestBody newBookShortDTO: NewBookShortDTO) =
        bookService.updateBook(id, newBookShortDTO)
}