package com.callor.spring.controller

import com.callor.spring.ConfigData
import com.callor.spring.model.Buyer
import com.callor.spring.service.BuyerService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(value = ["/buyer"])
class BuyerController(val bService: BuyerService) {

    private val logger = LoggerFactory.getLogger(BuyerController::class.java)

    // GetMapping을 사용해도 결국 RequestMapping으로 번역되어 돌아간다.
    // RequestMapping은 다른옵션들도 사용할 수 있어서 추천한다.

    //    @GetMapping(name="/list")
    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list(model: Model): String {

        logger.debug("fun list()가 여기다!")

        val buyerList = bService.selectAll()
        model["BUYERS"] = buyerList // alt + enter로 import
        return "buyer/list"
    }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(
        model: Model,
//        userId: String, // 이렇게 해도된다. 대신 변수명은 쿼리문과 같게 써야함
        @RequestParam("userid") userid: String // 이렇게 하면 변수명은 아무거나 가능
    ): String {
        var userInfo = bService.findById(userid)
        model["BUYER"] = userInfo
//        println(userInfo.toString())

//        var proInfo = pService.selectAll()
//        model["PRODUCT"] = proInfo

        return "buyer/detail"
    }

//    @ResponseBody
//    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
//    fun insert(): Buyer {
//
//        val insertBuyer = ConfigData.BUYER_LIST[0]
//        bService.insert(insertBuyer)
//
//        return insertBuyer
//    }

    /**
     * ModelAndAttribute 속성, 기능
     * Controller에서 Model 객체를 담고 form 화면을 rendering하면
     * 보통은 form의 value 속성에 하나하나 데이터를 추가하여 작성을 한다
     *
     * ModelAndAttribute를 사용하면 각각 view template의 고유 기능을 사용하여
     * id, name, value 값을 자동으로 채워넣는 기능을 만들 수 있다.
     *
     * thymeleaf template를 사용할 때는 form tag의 model에 담긴 object를 지정해주고
     * 각 input box에서는 field 속성으로 해당 멤버변수(요소, 속성)을 지정해주면
     * template 엔진이 rendering을 수행하면서 input에 필요한 요소들을 적절하게 생성해준다
     */
    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun write(model: Model): String {
        val insertBuyer = ConfigData.BUYER_LIST[0]
        model["BUYER"] = insertBuyer

        return "buyer/write"
    }


    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(model: Model, buyer: Buyer): String {
        bService.insert(buyer)

        return "redirect:/buyer/list"
    }

    // localhost:8080/update/B001 URL로 요청을 하면
    // B001 값을 userid에 담아서 함수 내부로 전달한다
    @RequestMapping(value = ["/update/{userid}"], method = [RequestMethod.GET])
    fun update(model: Model, @PathVariable("userid") userid: String): String {
        val buyer = bService.findById(userid)
        model["BUYER"] = buyer
        return "buyer/write"
    }

    /**
     * update를 실행할 때
     *      localhost:8080/buyer/update/userid 값으로 URL이 구성되어있음
     * update 화면에서 저장을 누르면 원래 요청했던 주소가 action이 되어 요청되므로
     * 여기에서는 userid가 필요없지만 pathVariable로 설정해주어야 한다
     */
    @RequestMapping(value = ["/update/{userid}"], method = [RequestMethod.POST])
    fun update(
        // return 문이 redirect인 경우에만
        // boot에서는 Model이 아닌 이것을 사용
        redirectAttributes: RedirectAttributes,
        buyer: Buyer,
        @PathVariable("userid") userid: String
    ): String {

        logger.debug(buyer.toString())
        bService.update(buyer)

        /**
         * Spring MVC 에서는 model 에 변수를 담으면
         * redirect 를 실행할 때 model 에 담긴 변수를
         * queryString 으로 부착하여 전송한다.
         *
         * 이 기능이 boot 에서는 금지되고
         * 같은 기능을 구현하기 위하여
         * model 대신 RedirectAttributes 를 사용한다.
         */

        // model + return 주소 =>
        // localhost:8080/buyer/detail?userid=ㅇㅇㅇ 형식으로
        // redirect 주소가 만들어진다
//        model["userid"] = buyer.userid.toString()
        redirectAttributes["userid"] = buyer.userid.toString()
        // redirectAttributes 를 사용하지 않으면 다음처럼 작성해야 한다.
        // return "redirect:/buyer/detail?userid=" + buyer.userid.toString()
        // 번거로우니 아래처럼 쓰자
        return "redirect:/buyer/detail"
    }

    @RequestMapping(value = ["/delete/{userid}"], method = [RequestMethod.GET])
    fun delete(@PathVariable("userid") userid: String): String {
        bService.delete(userid)
        return "redirect:/buyer/list"
    }
}