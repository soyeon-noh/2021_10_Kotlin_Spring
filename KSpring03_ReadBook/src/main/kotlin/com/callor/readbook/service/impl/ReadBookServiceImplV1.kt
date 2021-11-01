package com.callor.readbook.service.impl

import com.callor.readbook.model.ReadBook
import com.callor.readbook.repository.ReadBookRepository
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Service

@Service("rServiceV1")
class ReadBookServiceImplV1(val rRepo: ReadBookRepository): ReadBookService {
    override fun insert(readBook: ReadBook): ReadBook {
        return rRepo.save(readBook)
    }
}