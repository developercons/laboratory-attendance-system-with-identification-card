package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import java.util.*

open class CoreTime(var day: Int? = null,
               var startTime: Date? = null,
               var endTime: Date? = null,
               var isCoreDay: Boolean? = null) : RealmObject()
