package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class AttendanceLog() : RealmObject() {
    @PrimaryKey
    var logId: String = UUID.randomUUID().toString()
    var idm: String? = null
        set(value) {
            value ?: throw Exception("Invalid idm")
        }

    var enterTime: Date? = null
        set(value) {
            value ?: throw Exception("Invalid enterTime")
        }

    var exitTime: Date? = null
        set(value) {
            value ?: throw Exception("Invalid exitTime")
        }

    constructor(idm: String?, enterTime: Date?, exitTime: Date?) : this() {
        this.logId = UUID.randomUUID().toString()
        this.idm = idm
        this.enterTime = enterTime
        this.exitTime = exitTime
    }
}
