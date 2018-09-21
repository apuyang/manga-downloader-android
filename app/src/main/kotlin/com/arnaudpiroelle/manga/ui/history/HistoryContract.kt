package com.arnaudpiroelle.manga.ui.history

import com.arnaudpiroelle.manga.data.model.History

interface HistoryContract {
    interface View {
        fun displayHistories(histories: List<History>)
    }

    interface UserActionsListener {
        fun register()
        fun unregister()
        fun cleanHistory()
    }
}