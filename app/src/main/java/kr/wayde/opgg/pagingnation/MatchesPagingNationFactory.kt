package kr.wayde.opgg.pagingnation

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import kr.wayde.opgg.domain.entity.Games
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase

class MatchesPagingNationFactory(
    private val compositeDisposable: CompositeDisposable,
    private val userName: String,
    private val getMatchesUseCase: GetMatchesUseCase
) : DataSource.Factory<Long, Games>() {
    val sourceLiveData = MutableLiveData<MatchesPagingNation>()

    override fun create(): DataSource<Long, Games> {
        val userDataSource = MatchesPagingNation(compositeDisposable, userName, getMatchesUseCase)
        sourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}
