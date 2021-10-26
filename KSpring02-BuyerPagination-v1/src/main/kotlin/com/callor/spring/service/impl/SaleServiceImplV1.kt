package com.callor.spring.service.impl

import com.callor.spring.model.Sales
import com.callor.spring.service.SaleService
import org.springframework.stereotype.Service

@Service("sServiceV1")
class SaleServiceImplV1 : SaleService {

    private companion object {
    }

    override fun selectAll(): Array<Sales> {
        TODO("Not yet implemented")
    }

    override fun findById(s_id: String): Sales {
        TODO("Not yet implemented")
    }

    override fun findByUserId(userid: String): List<Sales> {
        TODO("Not yet implemented")
    }

    override fun insert(buyer: Sales): Int {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String): Int {
        TODO("Not yet implemented")
    }

    override fun update(buyer: Sales): Int {
        TODO("Not yet implemented")
    }


}