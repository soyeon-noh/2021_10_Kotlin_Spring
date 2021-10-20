package com.callor.spring.controller

import com.callor.spring.ConfigData
import com.callor.spring.model.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = ["/buyer"])
class BuyerController(val bService: BuyerService) {

    // GetMapping을 사용해도 결국 RequestMapping으로 번역되어 돌아간다.
    // RequestMapping은 다른옵션들도 사용할 수 있어서 추천한다.

    //    @GetMapping(name="/list")
    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list(model: Model): String {
        val buyerList = bService.selectAll()
        model["BUYERS"] = buyerList // alt + enter로 import
        return "buyer/list"
    }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(
        model: Model,
//        userId: String, // 이렇게 해도된다. 대신 변수명은 쿼리문과 같게 써야함
        @RequestParam("userid") userId: String // 이렇게 하면 변수명은 아무거나 가능
    ): String {

        val userDetail = bService.findById(userId)
        model["BUYER"] = userDetail

        return "buyer/detail" // detail.html 을 열어라
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun insert(): String {
//        val insertBuyer = ConfigData.BUYER_LIST[0]
//        bService.insert(insertBuyer)
//
//        return insertBuyer
        return "buyer/write"
    }

    @ResponseBody
    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(buyer: Buyer): String {
        return "" + buyer
    }
}