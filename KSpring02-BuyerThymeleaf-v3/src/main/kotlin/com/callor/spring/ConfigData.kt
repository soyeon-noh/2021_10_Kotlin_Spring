package com.callor.spring

import com.callor.spring.model.Buyer
import com.callor.spring.model.Sales
import kotlin.random.Random

class ConfigData {
    // public static final String APP_NAME = "나라상사 고객관리"
    //      System.out.println( ConfigString.APP_NAME )
    // 객체를 선언하지않고 클래스.객체명으로 어디서든 사용할 수 있게 됐었음.

    // 위와같은 java static 키워드와 유사한 역할을 하는 객체
    // 비슷한 것이지 똑같지는 않다 !
    companion object {
        val APP_NAME = "나라상사 고객관리"
        val APP_VERSION = "2021 v2"

        val RND = Random(System.currentTimeMillis())
        val BUYER_LIST = arrayOf(
            Buyer(
                userid = "B001",
                name = "홍길동",
                address = "서울시",
                tel = "02-111-1234",
                manager = "이몽룡",
                man_tel = "010-111-1111",
                buy_total = 10000
            ),
            Buyer(
                userid = "B002",
                name = "성춘향",
                address = "남원시",
                tel = "042-222-2222",
                manager = "월매",
                man_tel = "010-222-2222",
                buy_total = 10000
            ),
            Buyer(
                userid = "B003",
                name = "장보고",
                address = "해남시",
                tel = "063-333-3333",
                manager = "매니저",
                man_tel = "010-333-3333",
                buy_total = 10000
            ),
        )

        val SALES_NULL =
            Sales(
                seq = null,
                userid = null,
                date = null,
                time = null,
                pname = null,
                qty = null,
                amt = null,
                total = null
            )
    }


}