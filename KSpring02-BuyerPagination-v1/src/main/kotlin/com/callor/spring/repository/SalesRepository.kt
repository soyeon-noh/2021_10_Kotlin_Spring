package com.callor.spring.repository

import com.callor.spring.model.Sales
import org.springframework.data.jpa.repository.JpaRepository

// Repository 인터페이스 생성
// JpaRepository 를 상속받고 데이터 DTO 와 테이블의 PK 값을
// Generic 으로 설정해준다.
interface SalesRepository:JpaRepository<Sales, Long> { // 오른쪽 요소는 Sales객체의 PK 형식
    fun findByPname(pname: String):Array<Sales>
    fun findByUserid(userid: String):Array<Sales>
}