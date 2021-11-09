package com.callor.readbook.repository

import com.callor.readbook.models.BookDTO
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<BookDTO, String> {}