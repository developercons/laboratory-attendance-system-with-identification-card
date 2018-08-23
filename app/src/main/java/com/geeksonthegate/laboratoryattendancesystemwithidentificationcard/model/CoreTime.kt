package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model

import io.realm.RealmObject
import java.util.*

open class coretime(var startcoretime: Date? = null,
                    var endcoretime: Date? = null,
                    var isCoreDay: Boolean? = true) : RealmObject()
