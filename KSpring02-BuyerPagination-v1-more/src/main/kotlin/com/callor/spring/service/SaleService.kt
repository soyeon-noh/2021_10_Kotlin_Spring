package com.callor.spring.service

import com.callor.spring.model.Sales

interface SaleService {
    fun selectAll():Array<Sales>
    fun findById(s_id:String): Sales
    fun findByUserId(s_id:String) : List<Sales>

    fun insert(buyer: Sales):Int
    fun delete(userid:String) : Int
    fun update(buyer: Sales): Int
}