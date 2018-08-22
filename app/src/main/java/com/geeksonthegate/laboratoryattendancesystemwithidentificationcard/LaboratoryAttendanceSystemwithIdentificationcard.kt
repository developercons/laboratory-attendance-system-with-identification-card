package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class LaboratoryAttendanceSystemwithIdentificationcard : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val reamConfig = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(reamConfig)
    }
}
