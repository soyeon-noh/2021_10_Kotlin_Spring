package com.callor.readbook.repository

import com.callor.readbook.models.BookDTO
import com.callor.readbook.models.ReadBookDTO
import org.springframework.data.jpa.repository.JpaRepository

// 두개의 interface를 작성할 수 있긴한데 좋은 코드는 아니다
interface ReadBookRepository:JpaRepository<ReadBookDTO, Long> {}
interface BookRepository:JpaRepository<BookDTO,String> {}