package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Controler 를 만들지 않고 MVC 패턴
 */
@SpringBootConfiguration
class WebMVCReqController:WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry
            .addViewController("/member/mypage") // 이 주소로 요청왔을떄
            .setViewName("member/mypage") // 해당 페이지를 랜더링해라?
    }
}