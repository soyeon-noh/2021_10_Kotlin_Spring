package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Controler 를 만들지 않고 MVC 패턴
 *
 * 현재 작동하지 않음.
 * (mypage -> view로 변경한 채 예제로 남겨놓음)
 */
@SpringBootConfiguration
class WebMVCReqController:WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry
            .addViewController("/member/view") // 이 주소로 요청왔을떄
            .setViewName("member/view") // 해당 페이지를 랜더링해라?
    }
}