package com.callor.readbook.service.impl

import com.callor.readbook.model.Book
import com.callor.readbook.repository.BookRepository
import com.callor.readbook.service.BookService
import org.springframework.stereotype.Service

@Service("bServiceV1")
class BookServiceImplV1(val bRepo: BookRepository): BookService {
    override fun insert(book: Book): Book {
        return bRepo.save(book)
    }
}