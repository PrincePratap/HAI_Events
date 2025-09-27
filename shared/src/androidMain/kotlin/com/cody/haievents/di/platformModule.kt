package com.cody.haievents.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.cody.haievents.common.data.AndroidUserPreferences
import com.cody.haievents.common.data.UserSettingsSerializer
import com.cody.haievents.common.data.local.PREFERENCES_FILE_NAME
import com.cody.haievents.common.data.local.UserPreferences

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<UserPreferences> { AndroidUserPreferences(get()) }

    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(
                    fileName = PREFERENCES_FILE_NAME
                )
            }
        )
    }
}