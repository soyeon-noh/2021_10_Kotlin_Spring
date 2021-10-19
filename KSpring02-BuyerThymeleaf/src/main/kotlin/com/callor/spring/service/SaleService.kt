package com.callor.spring.service

import com.callor.spring.model.Sale

interface SaleService {
    fun selectAll():Array<Sale>
    fun findById(s_id:String): Sale
    fun findByUserId(s_id:String) : List<Sale>

    fun insert(buyer: Sale):Int
    fun delete(userid:String) : Int
    fun update(buyer: Sale): Int
}