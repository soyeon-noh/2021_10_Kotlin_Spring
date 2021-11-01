package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

// 프로젝트 설정을 위한 클래스다.
@SpringBootConfiguration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    /**
     * 인증절차를 수행하는 policy(정책) 설정
     */
    override fun configure(http: HttpSecurity) {
//        // 인가 , 누군가가 request로 요청을보냈는데 그게 허가된 요청인가? 를 처리한다.
//        // client 로부터 전달된 Req 가 인가된 요청인지 확인하겠다.
//        http.authorizeRequests()
//            // /member/mypage로 요청되면
//            .antMatchers("/member/mypage")
//            .authenticated()
//        http.authorizeRequests()
//            // 모든걸 통과시키겠다.
//            .antMatchers("/**")
//            .permitAll()

        // antMatchers(), mvcMatcher() 등은
        // authorizeRequest() 함수와 chaining 관계에 있는 함수들이다.
        // authorizeRequests() 함수 아래에 다수의 antMatchers() 를
        // 계속해서 추가할 수 있다.
        http.authorizeRequests()
            // member/mypage 로 req(요청) 이 오면
            // 인증절차를 수행하라 (인증되어있으면 보여주고, 그렇지않으면 log인으로 이동)
            .antMatchers("/member/mypage").authenticated()
            .antMatchers("/**").permitAll()

        // 단독으로 사용되는 method 함수들은
        // http.함수() 형식으로 사용한다
//        http.httpBasic()
//        http.formLogin()

        // 단독으로 사용되는 method 함수들을
        // chaining 방식으로 사용할 떄는 and() 함수로 연결해 준다.
//            .and().httpBasic() // popup 로그인
//            .and().formLogin() // 로그인 form 보이기

        // custom login 폼을 사용할 때는 .and()로 연결하지 말 것
        //  => and()로 했더니 문제생겨서 그냥 단독으로 사용하기로 함
        http.formLogin()
            // security 기본 form 화면을 보이는 대신
            // 내가 만든 MemberController 의 login URL으로
            // redirect 하라
            .loginPage("/member/login").permitAll()
            // 로그인을 할때 어떤 ul을 쓸 것인가
            // custom login form 의 action과 같은 Url 을 지정
            .loginProcessingUrl("/login")
            .usernameParameter("userid")
            .passwordParameter("userpw")
    } // config(http) end

    override fun configure(auth: AuthenticationManagerBuilder) { // 물음표 지워줌.
        // 인증을하고
        auth.inMemoryAuthentication()
            .withUser("callor")
            .password("12341234")
                // 그 사용자에게 USER과 ADMIN이라는 권한을 부여한다
            .roles("USER","ADMIN")
    }
}