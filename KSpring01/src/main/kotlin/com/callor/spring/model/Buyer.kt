package com.callor.spring.model

/**
 * Kotlin 에서 DTO(VO)용 클래스를 만들기
 * 1. data class 로 클래스 생성
 * 2. 멤버변수를 함수처럼 클래스( 매개변수 ) 와 같이 선언
 */
data class Buyer(var id:String, val name:String, var age:Int)
