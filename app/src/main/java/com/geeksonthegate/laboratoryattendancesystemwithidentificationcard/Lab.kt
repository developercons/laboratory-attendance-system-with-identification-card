package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Lab(@PrimaryKey var id: String = UUID.randomUUID().toString(),
               var labName: String,
               var coreTimeArray: List<CoreTime>) : RealmObject()
