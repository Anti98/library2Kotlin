package com.example.library2kotlin.repository

import com.example.library2kotlin.model.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookEntity, Long>