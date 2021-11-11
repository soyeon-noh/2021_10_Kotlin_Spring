package com.callor.readbook

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// Spring Security 에서 login 한 사용자(User, Member)를 담아둘 VO 클래스
data class MemberVO(
    private var username: String,
    private var password: String,
    private var enabled: Boolean = true,
    private var credentialNonExpired: Boolean = true, // 사용자가 제거됏냐?
    private var accountNonExpired: Boolean = true, // 어카운드카 제거되어있냐?
    private var accountNotLocked: Boolean = true, // 어카운드가 잠겨잇냐?
    private var authorities: Collection<GrantedAuthority> = setOf() // 다수의 데이터를 포함할 수 있는 칼럼
) : UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = password

    override fun isEnabled(): Boolean = enabled
    override fun isCredentialsNonExpired(): Boolean = credentialNonExpired
    override fun isAccountNonExpired(): Boolean = accountNonExpired
    override fun isAccountNonLocked(): Boolean = accountNotLocked

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities
    fun setAuthorities(authorities: Collection<GrantedAuthority>) {
        this.authorities = authorities
    }
}

