package kr.wayde.opgg.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
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
    private val getSummonerInfoUseCase: GetSummonerInfoUseCase,
    private val getMatchesUseCase: GetMatchesUseCase
) : BaseViewModel() {

    val summonerResult = MutableLiveData<Summoner>()
    val matchesInfoResult = MutableLiveData<Matches>()
    val gamesLiveData = MutableLiveData<PagedResult<Games>>()

    fun getSummonerInfo(userName: String) {
        compositeDisposable.add(
            getSummonerInfoUseCase.get(userName).subscribe { it: Summoner ->
                summonerResult.postValue(it)
            }
        )
    }

    fun getMatchesInfo(userName: String) {
        userName
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                getMatchesUseCase
                    .get(GetMatchesUseCase.Params(it, System.currentTimeMillis() / 1000))
                    .subscribe { it: Matches ->
                        matchesInfoResult.postValue(it)
                    }
            }
    }

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