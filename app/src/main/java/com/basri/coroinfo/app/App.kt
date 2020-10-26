package com.basri.coroinfo.app

import android.app.Application
import android.content.Context
import com.basri.coroinfo.database.AppDatabase
import com.facebook.stetho.Stetho

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.createDatabase(this)
        Stetho.initializeWithDefaults(this)
        Stetho.initializeWithDefaults(this)
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                    Stetho.defaultDumperPluginsProvider(this)
                )
                .enableWebKitInspector(
                    Stetho.defaultInspectorModulesProvider(this)
                )
                .build()
        )
    }

    companion object {
        private val mContext: Context? = null
    }
}