package com.callor.readbook.service

import com.callor.readbook.MemberVO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Security login Service 클래스
 * ( *UserDetailService ) 라는 이름으로 구현한다.
 *
 * Security login Service 는  UserDetailsService 를 상속받는다
 *
 */
@Service
class MemberLoginService : UserDetailsService {

    // 가상의 member list 생성해두기
    private val userList = listOf(
        MemberVO(username = "callor", password = "1234"),
        MemberVO(username = "callor88", password = "1234"),
        MemberVO(username = "callorok", password = "1234"),
    )

    // findByUserName(username) : UserDetails
    // 사용자의 아이디를 가지고 조회해서 데이터베이스에 있으면 그 정보를 리턴하라
    override fun loadUserByUsername(username: String): UserDetails {

        // 배열.find {} : 배열의 요소중 원하는 값이 담겨있는가?
        // 담겨 있으면 해당 값을 return 학, 없으면 null return
        // => 매개변수로 받은 username이 userList에 포함되어있느냐?
        // 없으면 null이 담기기떄문에 null safe로 생성
        val member: UserDetails? = userList.find { it.username == username }

        // member가 있으면 아무일도 안 일어나고
        // member가 null이면 Exception을 발생시킨다
        // return 문보다 강력한 것이며 spring의 try catch가 캐치해서 redirect화면으로 메시지를 전달한다.
        member ?: throw UsernameNotFoundException("사용자 ID가 잘못되었습니다")


        return member
    }
}
