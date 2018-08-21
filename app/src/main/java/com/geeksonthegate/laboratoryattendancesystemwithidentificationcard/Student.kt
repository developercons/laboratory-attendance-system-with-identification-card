package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Student(@PrimaryKey var idm: String? = null,
                   var studentId: String? = null,
                   var name: String? = null,
                   var lab: Lab? = null) : RealmObject()
