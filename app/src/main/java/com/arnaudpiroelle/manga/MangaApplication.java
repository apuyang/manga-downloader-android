package com.arnaudpiroelle.manga;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.arnaudpiroelle.manga.core.inject.DaggerMangaComponent;
import com.arnaudpiroelle.manga.core.inject.MangaComponent;
import com.arnaudpiroelle.manga.core.inject.module.ApplicationModule;
import com.arnaudpiroelle.manga.service.DownloadService;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

public class MangaApplication extends Application {

    public static MangaComponent GRAPH;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            //TODO: Add Timber
        } else {
            Fabric.with(this, new Crashlytics());
        }

        DownloadService.updateScheduling(this);

        GRAPH = DaggerMangaComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Sprinkles sprinkles = Sprinkles.init(this);

        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL(
                        "CREATE TABLE Mangas (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT," +
                                "mangaAlias TEXT," +
                                "provider TEXT," +
                                "lastChapter TEXT" +
                                ")"
                );
            }
        });

        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL(
                        "CREATE TABLE Histories (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "label TEXT," +
                                "date INTEGER" +
                                ")"
                );
            }
        });

    }

}
