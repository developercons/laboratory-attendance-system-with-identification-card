package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class AttendanceLog(@PrimaryKey var logID: String = UUID.randomUUID().toString(),
                         var IDm: String? = null,
                         var enterTime: Date? = null,
                         var exitTime: Date? = null) : RealmObject()
