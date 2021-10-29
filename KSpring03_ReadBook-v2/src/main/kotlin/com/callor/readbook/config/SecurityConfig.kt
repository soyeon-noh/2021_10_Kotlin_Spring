package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

// 'Spring Security 를 custom 하겠다' 라는 선언
@EnableWebSecurity
@SpringBootConfiguration
// WebSecurityConfigurerAdapter를 상속받아야한다
class SecurityConfig:WebSecurityConfigurerAdapter(){

    // 매개변수 HttpSecurity 뒤에 붙어 있던 '?' 삭제
    // 여기서 NullpointException 관리 안 할 것.
    override fun configure(http: HttpSecurity) {

        // HttpSecurity 를 통해서 오는 모든 요청을 가로챌 것이다.
        http.authorizeRequests()
                // member 페이지로 요청이 들어오면 인증을 확인하고 수락하거나 거부해라
            .antMatchers("/member/mypage").authenticated()
                // 그외의 모든 것에 대하여 허락해라
            .antMatchers("/**").permitAll()
            .mvcMatchers("/**").anonymous()
//            .and()
//            .httpBasic()

        // pop up login 창 띄우기
        // httpBasic이라는 함수를 연결시켰다.
        // 인증이되지 않은 경우에는 팝업창을 띄워 로그인을 받아라
//        http.httpBasic()

        // 만약 인증이 필요한 페이지에 권한 없이 접근하면
        // /member/login으로 redirect 하라
        http.formLogin()
            .loginPage("/member/login").permitAll()
    }
}