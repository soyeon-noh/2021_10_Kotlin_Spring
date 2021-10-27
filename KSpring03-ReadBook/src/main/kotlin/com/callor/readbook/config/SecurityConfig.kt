package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SpringBootConfiguration
// spring Security를 Custom 하겠다 라는 선언
@EnableWebSecurity
class SecurityConfig:WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {

        // httpSecurity를 통해 오는 request를 가로쳐서 관리하겠다.
        http.authorizeRequests()
            .mvcMatchers("/**") // "/**" : 모든 request 에 대하여
            .anonymous() // 누구나
            .mvcMatchers("/admin") // "/admin"을 통해 접속하면
            .fullyAuthenticated() // 인증을 받아야 한다

    }
}