package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Lab() : RealmObject() {
    @PrimaryKey
    var labId: String = UUID.randomUUID().toString()
    var labName: String? = null
        set(value) {
            value ?: throw Exception("Invalid LabName")
            if (value.length > 7 || value.isEmpty()) throw Exception("Invalid labName")
        }

    var coreTimeArray: RealmList<CoreTime>? = null
        set(value) {
            value ?: throw Exception("Invalid CoreTime")
            if (value.size == 0) throw Exception("Invalid coreTimeArray")
        }

    constructor(labName: String?, coreTimeArray: RealmList<CoreTime>?) : this() {
        this.labId = UUID.randomUUID().toString()
        this.labName = labName
        this.coreTimeArray = coreTimeArray
    }
}
