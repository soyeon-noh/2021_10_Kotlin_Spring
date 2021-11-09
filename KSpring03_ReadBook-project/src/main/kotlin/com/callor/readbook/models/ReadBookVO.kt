package com.callor.readbook.models

// write에서 insert하는 독서록을 담아둘 VO
data class ReadBookVO(
    var isbn: String,
    var title: String,
    var sdate: String,
    var stime: String,
    var etime: String,
    var subject: String,
    var content: String
)
