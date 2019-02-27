package com.arnaudpiroelle.manga.provider.japscan

import com.arnaudpiroelle.manga.api.Plugin
import com.arnaudpiroelle.manga.api.provider.MangaProvider
import okhttp3.OkHttpClient

class JapScan : Plugin {

    override fun getName(): String {
        return "JapScan (old)"
    }

    override fun getProvider(okHttpClient: OkHttpClient): MangaProvider {
        return JapScanMangaProvider(okHttpClient)
    }

}