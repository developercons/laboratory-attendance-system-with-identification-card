package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.annotations.PrimaryKey
import java.util.*

open class AttendanceLog(@PrimaryKey var logID: String = UUID.randomUUID().toString(),
                         var IDm: ByteArray? = null,
                         var enterTime: Date? = null,
                         var exitTime: Date? = null)
