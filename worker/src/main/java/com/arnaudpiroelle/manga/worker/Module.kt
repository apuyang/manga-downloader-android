package com.arnaudpiroelle.manga.worker

import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager
import com.arnaudpiroelle.manga.worker.notification.NotificationCenter
import com.arnaudpiroelle.manga.worker.utils.FileHelper
import com.arnaudpiroelle.manga.worker.utils.PreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val workerModule = module {
    single { WorkManager.getInstance(androidContext()) }
    single { PreferencesHelper(androidContext(), get()) }
    single { FileHelper(get()) }
    single { TaskManager(get()) }
    single { NotificationManagerCompat.from(androidContext()) }
    single { NotificationCenter(androidContext(), get(), get()) }
}