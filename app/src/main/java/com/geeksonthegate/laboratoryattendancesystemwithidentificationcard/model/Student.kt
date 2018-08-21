package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Student() : RealmObject() {
    @PrimaryKey
    var idm: String? = null
        set(value) {
            value ?: throw Exception("Invalid idm")
        }

    var lab: Lab? = null
        set(value) {
            value ?: throw Exception("Invalid Lab")
        }

    var studentId: String? = null
        set(value) {
            value ?: throw Exception("Invalid studentId")
            if (value.length != 7) throw Exception("Invalid studentId")
        }

    var name: String? = null
        set(value) {
            value ?: throw Exception("Invalid name")
            if (value.length > 7 || value.isEmpty()) throw Exception("Invalid name")
        }

    constructor(idm: String?, studentId: String?, name: String?, lab: Lab?) : this() {
        this.idm = idm
        this.lab = lab
        this.studentId = studentId
        this.name = name
    }
}
