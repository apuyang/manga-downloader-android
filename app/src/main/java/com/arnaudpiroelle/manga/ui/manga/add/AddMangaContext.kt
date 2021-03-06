package com.arnaudpiroelle.manga.ui.manga.add

import androidx.annotation.StringRes
import com.arnaudpiroelle.manga.api.core.provider.ProviderRegistry
import com.arnaudpiroelle.manga.api.model.Manga
import com.arnaudpiroelle.manga.ui.common.BaseAction
import com.arnaudpiroelle.manga.ui.common.BaseState

interface AddMangaContext {

    sealed class Action : BaseAction {
        object LoadProvidersAction : Action()
        data class SelectProviderAction(val provider: ProviderRegistry.Provider) : Action()
        data class FilterResultsAction(val query: String) : Action()
        data class AddNewMangaAction(val manga: Manga) : Action()
    }


    data class State(
            val isLoading: Boolean = false,
            val error: ActionError? = null,
            val status: WizardStatus = WizardStatus.STARTED,
            val providers: List<ProviderRegistry.Provider> = listOf(),
            val results: List<Manga> = listOf(),
            val filter: String = ""
    ) : BaseState {
        fun getFilteredResults() = results.filter { it.name.toLowerCase().contains(filter) }
    }

    enum class WizardStatus {
        STARTED,
        FINISHED
    }

    data class ActionError(@StringRes val message: Int, val retry: AddMangaContext.Action? = null)
}

