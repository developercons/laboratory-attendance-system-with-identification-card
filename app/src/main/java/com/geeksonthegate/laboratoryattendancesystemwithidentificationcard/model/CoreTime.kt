package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import java.util.*

open class CoreTime(var startCoreTime: Date? = null,
                    var endCoreTime: Date? = null,
                    var isCoreDay: Boolean? = null) : RealmObject()
