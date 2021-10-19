package com.callor.spring.service.impl

import com.callor.spring.model.Sale
import com.callor.spring.service.SaleService
import org.springframework.stereotype.Service

@Service("sServiceV1")
class SaleServiceImplV1 : SaleService {

    private companion object {
        val SALE_LIST = arrayOf(
            Sale(
                s_id = "S001",
                s_userid= "B001",
                s_name = "아이폰13",
                s_price = "1200000",
                s_quantity = "10",
                s_total_price = "12000000"
            ),
            Sale(
                s_id = "S002",
                s_userid= "B001",
                s_name = "갤럭시폴드",
                s_price = "1500000",
                s_quantity = "10",
                s_total_price = "15000000"
            )
        )
    }

    override fun selectAll(): Array<Sale> {
        TODO("Not yet implemented")
    }

    override fun findById(s_id: String): Sale {
        val findSale = SALE_LIST.filter { sale -> sale.s_id == s_id }
        return findSale[0]
    }

    override fun findByUserId(userid: String): List<Sale> {
        val findByUserid = SALE_LIST.filter { sale -> sale.s_userid == userid}
        return findByUserid
    }

    override fun insert(buyer: Sale): Int {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String): Int {
        TODO("Not yet implemented")
    }

    override fun update(buyer: Sale): Int {
        TODO("Not yet implemented")
    }


}