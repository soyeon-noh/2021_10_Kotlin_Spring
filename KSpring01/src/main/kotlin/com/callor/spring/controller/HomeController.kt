package com.callor.spring.controller

import com.callor.spring.model.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    /**
     * BuyerService 인터페이스를 사용하여
     * bService 객체를 선언하고
     * Spring 에게 객체 주입을 요청하기 위하여 Autowired를 선언했다.
     *
     * Kotlin은 절대 null값이나 없는 값으로 변수, 객체 선언 금지!
     * 그런데 여기서는 선언만했고 초기화를 안시킨 bService가 있다.
     * 이러한 경우에는 반드시 lateinit var를 부착해줘야 한다.
     * 나중에 초기화 한다는 뜻이다.0
     */
    @Autowired
    private lateinit var bService : BuyerService

    @ResponseBody
    @RequestMapping(value=["/"], method = [RequestMethod.GET])
    fun home():String {
        return "반갑습니다!!!"
    }

    @RequestMapping(value=["/hello"], method=[RequestMethod.GET])
    fun hello(model:Model) : String {

        model.addAttribute("name", "홍길동이")
        return "hello"
    }

    @ResponseBody
    @RequestMapping(value=["/list"], method=[RequestMethod.GET])
    fun list():  Array<Buyer>{
        return bService.selectAll();
    }

    @ResponseBody
    @RequestMapping(value=["/getuser"],method=[RequestMethod.GET])
    fun getUser() : Buyer {
        return bService.findById("user")
    }
}