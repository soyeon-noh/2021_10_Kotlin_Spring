package com.callor.readbook.models

import javax.persistence.Entity
import javax.persistence.Table

//@Entity
//@Table()
data class ReadBook(
    var isbn: String,
    var title: String,
    var sdate: String,
    var stime: String,
    var etime: String,
    var subject: String,
    var content: String
)
