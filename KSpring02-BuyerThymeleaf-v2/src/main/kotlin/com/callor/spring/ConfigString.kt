package com.callor.spring

class ConfigString {
    // public static final String APP_NAME = "나라상사 고객관리"
    //      System.out.println( ConfigString.APP_NAME )
    // 객체를 선언하지않고 클래스.객체명으로 어디서든 사용할 수 있게 됐었음.

    // 위와같은 java static 키워드와 유사한 역할을 하는 객체
    // 비슷한 것이지 똑같지는 않다 !
    companion object {
        val APP_NAME = "나라상사 고객관리"
        val APP_VERSION = "2021 v2"
    }
}