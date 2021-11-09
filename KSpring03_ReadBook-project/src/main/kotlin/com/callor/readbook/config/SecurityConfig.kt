package com.callor.readbook.config

import com.callor.readbook.service.MemberLoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

// 프로젝트 설정을 위한 클래스다.
@SpringBootConfiguration
// 프로젝트에 Spring Security 설정을 추가한다.
// Spring Security 를 Customizing 하겠다.
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        // Security가 이건 간섭하지 말라는 뜻.
        // 아래 url로 request가 날라오면 건들이지 말아라.
        web.ignoring().antMatchers(
            "/static/**",
            "/static/css/**",
            "/static/js/**",
            "/static/images/**"
        )

        // 원래 위의 것만 해놔도 yml을 읽어서
        web.ignoring()
            .requestMatchers(
                PathRequest
                    .toStaticResources()
                    .atCommonLocations()
            )
    }

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
            // 인증절차를 수행하라 (인증되어있으면 보여주고, 그렇지않으면 login으로 이동)
            .antMatchers("/member/login").permitAll()
            .antMatchers("/member/mypage").authenticated()
            .antMatchers("/member/**").permitAll()

            .antMatchers("home/**").permitAll()
            .antMatchers("home/merry").authenticated()

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
//            .usernameParameter("userid")
//            .passwordParameter("userpw")

        // logout을 구현하겠다.
        http.logout()
            // ${rootPath}/logout 으로 요청이 들어오면
            // logout 을 수행하라
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            // logout 이 정상적으로 수행되면
            // /member/mypage 로 redirect 를 수행하라
            .logoutSuccessUrl("/member/mypage")

    } // config(http) end

    /**
     * "{noop}12341234"
     * Spring Security 에서 제공하는 password 정책
     * 5.x 버전 이상에서는
     * 의무적으로 password 를 DB 에 저장하거나 비교연산등을 할 때
     * 반드시 암호화를 하도록 강제하고 있다.
     *
     * 아직 암호화를 구현하지 않은 상태에서 테스트를 하기 위해서
     * 임시로 암호화 되지 않은 비밀번호를 사용해서
     * 비밀번호 비교를 하겠다 라는 의미의 메시지
     */
    override fun configure(auth: AuthenticationManagerBuilder) { // 물음표 지워줌.
//        // 인증을하고
//        auth.inMemoryAuthentication()
//            // 임의로 테스트 id, pw를 정해놓는다.
//            .withUser("callor")
//            // 원래 비밀번호는 암호화해야한다(encoding)
//            .password("{noop}12341234")
//            // 그 사용자에게 USER과 ADMIN이라는 권한을 부여한다
//            .roles("USER", "ADMIN")

        // 우리가 만든 MemberLoginService를 사용하겠다는 것!
        // security 에게 User Detailservice 인터페이스를 상속받은
        //      MemberLoginService 클래스의 객체를 건네줄테니
        //      회원 정보 인증할 떄 사용하라.
        // MemberLoginService.loadUserByUserName() 함수를 실행하여
        //      사용자 정보를 나에게 달라
        auth.userDetailsService(MemberLoginService())
            // auth 에 담긴 사용자정보에서 password 항목을 찾아
            // CustomPasswordEncoder() 에게 보내서
            // 비밀번호가 맞는지 검사하라
            .passwordEncoder(CustomPasswordEncoder())
    }
}

class CustomPasswordEncoder : PasswordEncoder {
    override fun encode(plan: CharSequence): String {
        return plan.toString()
    }

    override fun matches(plan: CharSequence?, crypt: String?): Boolean {
        // 암호화를 못해서 일단 다 맞다고 넘어감
        // 모든 패스워드는 일치한다!! 하고 넘어감
        return true
    }

}