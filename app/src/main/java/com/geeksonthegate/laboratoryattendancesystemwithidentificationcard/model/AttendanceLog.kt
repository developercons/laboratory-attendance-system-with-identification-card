package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class AttendanceLog(@PrimaryKey var logId: String = UUID.randomUUID().toString(),
                         var idm: String? = null,
                         var enterTime: Date? = null,
                         var exitTime: Date? = null) : RealmObject()
