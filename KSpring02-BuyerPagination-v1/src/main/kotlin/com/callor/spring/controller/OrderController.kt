package com.callor.spring.controller

import com.callor.spring.ConfigData
import com.callor.spring.config.logger
import com.callor.spring.model.Sales
import com.callor.spring.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

@Controller
@RequestMapping(value = ["/order"])
// OrderService 주입받기
class OrderController(val orService: OrderService) {

    // localhost:8080/order/ 또는
    // localhost:8080/order 요청을 모두 수용
    // 두가지를 구분하기 시작함
    // 둘다 적어주자
    @RequestMapping(value = ["", "/"], method = [RequestMethod.GET])
    fun list(model: Model, pageable: Pageable): String {

        // log대체 왜안나오는지 아시는분
        logger().debug("주문조회 Pageable {}", pageable.toString())

        val salesList = orService.selectAll(pageable)
        model["SALES"] = salesList

        return "order/list"
    }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(model: Model, @RequestParam("seq") seq: Long): String {

        var detailList = orService.findById(seq)
        model["SALES"] = detailList

        return "order/detail"
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun insert(model: Model, sales: Sales): String {
        orService.insert(sales)

        model["SALES"] = ConfigData.SALES_NULL

        return "order/write"
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(redirectAttributes: RedirectAttributes, sales: Sales): String {

        // total을 자동으로 계산하여 넣어주려고했는데 string to int 에서 문제가 생김
/*        var qty: Int = 0
        var amt: Int = 0
        try {
            qty = sales.qty.toInt()
            amt = sales.amt.toInt()
        } catch (e: NumberFormatException){
            return "order/write"
        }
        val total: Int = qty * amt
        sales.total = total*/
        val df = SimpleDateFormat("yyyy년 MM월 dd일")
        val dt = SimpleDateFormat("hh : mm : ss")

        val toDate = df.format(Date())
        val toTime = dt.format(Date())

        sales.date = toDate
        sales.time = toTime

        orService.insert(sales)
        redirectAttributes["seq"] = sales.seq.toString()
        return "redirect:/order/detail"
    }

    @RequestMapping(value = ["/update/{seq}"], method = [RequestMethod.GET])
    fun update(model: Model, @PathVariable("seq") seq: Long): String {


        model["SALES"] = orService.findById(seq)

        return "order/write"
    }

    @RequestMapping(value= ["/update/{seq}"], method=[RequestMethod.POST])
    fun update(redirectAttributes: RedirectAttributes, sales: Sales): String {

        val df = SimpleDateFormat("yyyy년 MM월 dd일 (수정됨)")
        val dt = SimpleDateFormat("hh : mm : ss (수정됨)")

        val toDate = df.format(Date())
        val toTime = dt.format(Date())

        sales.date = toDate
        sales.time = toTime

        orService.update(sales)

        redirectAttributes["seq"] = sales.seq.toString()
        return "redirect:/order/detail"
    }

    @RequestMapping(value=["/delete/{seq}"], method=[RequestMethod.GET])
    fun delete(@PathVariable("seq")seq:Long):String {

        orService.delete(seq)

        return "redirect:/order"
    }
}