package com.callor.spring.config

import com.callor.spring.ConfigData
import com.callor.spring.model.Sales
import com.callor.spring.repository.SalesRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import java.text.SimpleDateFormat
import java.util.*

/**
 * Spring  Boot 가 시작될 때 사용할 초기값, 설정 등을 수행하는 클래스
 *
 * SalesRepository 를 생성자 주입방식으로 wiring 한다.
 * 클래스의 생성자 method 에 매개변수로 설정만 해두면 된다.
 */

@SpringBootConfiguration
class SalesDataInit(val saleDao:SalesRepository) {

    // 해당 class에 대한 정보를 logger에게 주입하여 log를 사용할 수 있게 한다.
    val logger = LoggerFactory.getLogger(SalesDataInit::class.java)

    private val pnames = listOf(
        "아이폰13", "갤럭시폴드",
        "에어팟프로", "갤럭시버즈",
        "애플워치", "갤럭시워치"
    )

    @Bean
    fun dataInit ():CommandLineRunner {

        for(num in 1..100){
            saleDataInit()
        }

        // 마지막으로
        return CommandLineRunner {
            logger.debug("Sale 데이터 Complete!!!")
        }
    }

    private fun saleDataInit(){
        // 1 ~ 20까지 임의의 수 만들기
        val userid = String.format("B%03d", ConfigData.RND.nextInt(20) + 1)
        // pnames에서 한개를 무작위로 선택하기
        val pname = pnames[ConfigData.RND.nextInt(pnames.size)]

        val qty = ConfigData.RND.nextInt((10) + 10) * 10 // 100 ~ 190
        val price = ConfigData.RND.nextInt((100) + 100) * 1000

        val df = SimpleDateFormat("yyyy-MM-dd")
        val dt = SimpleDateFormat("hh:mm:ss")

        val toDate = df.format(Date())
        val toTime = dt.format(Date())

        // 가상 데이터 객체 생성
        val sales = Sales(
            userid = userid,
            pname = pname,
            qty = qty,
            amt = price,
            total = qty * price,
            date = toDate,
            time = toTime
        )

        // 가상의 데이터가 생성된다
        saleDao.save(sales)
    }
}