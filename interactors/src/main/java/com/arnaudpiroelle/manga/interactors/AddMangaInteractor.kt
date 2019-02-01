package com.arnaudpiroelle.manga.interactors

import com.arnaudpiroelle.manga.api.model.Manga
import com.arnaudpiroelle.manga.data.dao.MangaDao
import com.arnaudpiroelle.manga.data.model.Manga.Status.ADDED
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddMangaInteractor(private val mangaDao: MangaDao, private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(manga: Manga) = withContext(dispatcher) {
        mangaDao.insert(com.arnaudpiroelle.manga.data.model.Manga(
                name = manga.name,
                alias = manga.alias,
                provider = manga.provider,
                thumbnail = "",
                status = ADDED)
        )
    }
}