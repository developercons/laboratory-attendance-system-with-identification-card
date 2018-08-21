package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Lab(@PrimaryKey var id: String = UUID.randomUUID().toString(),
               var labName: String? = null,
               var coreTimeArray: RealmList<CoreTime>? = null) : RealmObject()
