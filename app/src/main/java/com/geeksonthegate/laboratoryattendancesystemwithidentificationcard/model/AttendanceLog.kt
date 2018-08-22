package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class AttendanceLog(var idm: String? = null,
                         var enterTime: Date? = null,
                         var exitTime: Date? = null) : RealmObject() {
    @PrimaryKey
    var logId: String = UUID.randomUUID().toString()

    init {
        idm ?: throw Exception("Invalid idm")
        enterTime ?: throw Exception("Invalid enterTime")
        exitTime ?: throw Exception("Invalid exitTime")
    }
}
