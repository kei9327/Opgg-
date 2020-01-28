package kr.wayde.opgg.util

import androidx.paging.PagedList

val config: PagedList.Config = PagedList.Config.Builder().apply {
    setInitialLoadSizeHint(20)
    setPageSize(20)
    setPrefetchDistance(10)
    setEnablePlaceholders(true)
}.build()