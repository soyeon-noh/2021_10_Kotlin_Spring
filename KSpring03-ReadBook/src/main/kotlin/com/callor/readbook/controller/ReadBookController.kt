package com.callor.readbook.controller

import com.callor.readbook.model.Book
import com.callor.readbook.model.ReadBook
import com.callor.readbook.service.BookService
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value=["/read"])
class ReadBookController(val bService: BookService, val rService: ReadBookService) {

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun insert(): String {

        return "write"
    }

    @ResponseBody
    @RequestMapping(value= ["/insert"], method =[RequestMethod.POST])
    fun insert(book:Book, readBook:ReadBook): Book {
        readBook.seq = "1"
        bService.insert(book)
        rService.insert(readBook)



        return book
    }
}