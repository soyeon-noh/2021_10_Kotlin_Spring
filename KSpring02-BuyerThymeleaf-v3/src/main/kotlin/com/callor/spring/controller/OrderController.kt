package com.callor.spring.controller

import com.callor.spring.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(value = ["/order"])
// OrderService 주입받기
class OrderController(val orService: OrderService) {


    // localhost:8080/order/ 또는
    // localhost:8080/order 요청을 모두 수용
    // 두가지를 구분하기 시작함
    // 둘다 적어주자
    @RequestMapping(value = ["", "/"], method = [RequestMethod.GET])
    fun list(model: Model):String {

        val salesList = orService.selectAll()
        model["SALES"] = salesList

        return "order/list"
    }

    @RequestMapping(value=["/detail/{seq}"], method = [RequestMethod.GET])
    fun detail(model:Model, @PathVariable("seq") seq: Long):String {

        var detailList = orService.findById(seq)
        model["SALES"] = detailList

        return "order/detail"
    }
}