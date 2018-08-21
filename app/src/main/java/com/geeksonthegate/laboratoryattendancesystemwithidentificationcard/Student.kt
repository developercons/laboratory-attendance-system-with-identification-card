package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Student(@PrimaryKey var IDm: String? = null,
                   var studentID: String? = null,
                   var name: String? = null,
                   var lab: Lab? = null) : RealmObject()
