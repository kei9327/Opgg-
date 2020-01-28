package kr.wayde.opgg.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.wayde.opgg.domain.entity.Games
import kr.wayde.opgg.domain.entity.Matches
import kr.wayde.opgg.domain.entity.Summoner
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase
import kr.wayde.opgg.domain.interactor.usecases.GetSummonerInfoUseCase
import kr.wayde.opgg.pagingnation.MatchesPagingNationFactory
import kr.wayde.opgg.pagingnation.PagedResult
import kr.wayde.opgg.ui.BaseViewModel
import kr.wayde.opgg.util.config

class MainViewModel(
    private val getMatchesUseCase: GetMatchesUseCase
) : BaseViewModel() {

    private val gamesLiveData = MutableLiveData<PagedResult<Games>>()
    val pagedList: LiveData<PagedList<Games>> = Transformations.switchMap(gamesLiveData) {it.data}

    fun getGames(userName: String) {
        userName
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                launch {
                    val temp = withContext(coroutineContext) {
                        val sourceFactory =
                            MatchesPagingNationFactory(compositeDisposable, it, getMatchesUseCase)
                        val livePagedList = LivePagedListBuilder(sourceFactory, config).build()
                        return@withContext PagedResult(data = livePagedList)
                    }

                    gamesLiveData.postValue(temp)
                }
            }
    }
}