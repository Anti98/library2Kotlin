package com.example.library2kotlin.repository

import com.example.library2kotlin.model.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<AuthorEntity, Long>