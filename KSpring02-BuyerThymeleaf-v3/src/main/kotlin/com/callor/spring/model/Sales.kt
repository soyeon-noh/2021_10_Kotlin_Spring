package com.callor.spring.model

import java.util.*
import javax.persistence.*

/**
 * ID 칼럼을 자동 증가 옵션으로 자동 생성하기
 * SEQUENCE, IDENTITY, TABLE, AUTO
 * SEQUENCE : Oracle Sequence
 * IDENTITY : Auto_increment 가 지원되는 DB
 * TABLE : Hibernate가 자체적으로 SEQUENCE TABLE을 만들고
 *      증가값을 관리하도록 하기
 * AUTO : 사용하는 DBMS 특성에 따라 SEQ를 만들거나
 *      AUTO_IN.. 를 생성하여 증가값을 관리
 *
 * AUTO 로 설정하면 DB 특성에 따라 자체적으로 관리를 하는데
 *      현재는 그렇지 않고 hibernate_sequence 테이블을 생성하여
 *      JPA 가 자체적으로 Seq를 생성하도록 만들어진다.
 *      auto_increment 가 없는 DB에서는 AUTO로 설정하는 것을 권장한다.
 */

@Entity
@Table(name = "tbl_sales", schema="naraDB")
data class Sales(
    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 증가하는 옵션 추가
    var seq: Long,
    var date: String,
    var time: String,
    var pname: String,
    var qty: Int,
    var amt: Int,
    var total: Int,

    // 데이터에 특별하게 Date(날짜, 시간형) 값을 사용하고 싶을 때

    @Temporal(TemporalType.DATE) // 날짜값만
    var date1 : Date, // util.date 를 import , temporla에서 sql.date을 지원하지 않는듯

    @Temporal(TemporalType.TIME) // 시간값만
    var time1 : Date,

    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간 동시에
    var date_time : Date,
)
