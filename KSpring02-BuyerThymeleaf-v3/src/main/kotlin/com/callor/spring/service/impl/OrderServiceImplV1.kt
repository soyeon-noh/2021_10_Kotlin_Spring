package com.callor.spring.service.impl

import com.callor.spring.model.Sales
import com.callor.spring.repository.SalesRepository
import com.callor.spring.service.OrderService
import org.springframework.stereotype.Service

@Service("orderServiceV1")
// (salesDao: SalesRepository) : 생성자로 주입받기
class OrderServiceImplV1(val salesDao: SalesRepository):OrderService {
    override fun selectAll(): Array<Sales> {
        return salesDao.findAll().toTypedArray()
    }

    override fun findById(seq: Long): Sales {
        // findById는 뒤에 getter를 써줘라
        return salesDao.findById(seq).get()
    }

    override fun findByUserId(userid: String): Array<Sales> {
        return salesDao.findByUserid(userid)
    }

    override fun findByPname(pname: String): Array<Sales> {
        return salesDao.findByPname(pname)
    }

    override fun findByDateDestance(sDate: String, eDate: String): Array<Sales> {
        TODO("Not yet implemented")
    }

    override fun insert(sales: Sales): Sales {
        return salesDao.save(sales)
    }

    override fun delete(seq: Long) {
        salesDao.deleteById(seq)
    }

    override fun update(sales: Sales): Sales {
        return salesDao.save(sales)
    }


}