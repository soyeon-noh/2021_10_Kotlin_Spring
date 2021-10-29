package com.callor.readbook.models

import javax.persistence.*

@Entity
@Table(name="tbl_readbooks", schema = "naraDB")
data class ReadBookDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    var seq: Long? = 0,

    @Column(columnDefinition = "VARCHAR(13)", nullable = false)
    var isbn: String,

    var title: String? = null,
    var sdate: String? = null,
    var stime: String? = null,
    var etime: String? = null,
    var subject: String? = null,
    var content: String? = null
)