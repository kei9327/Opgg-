package kr.wayde.opgg.pagingnation

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import kr.wayde.opgg.domain.entity.Games
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase


class MatchesPagingNation(val compositeDisposable: CompositeDisposable,
                          val userName: String,
                          val getMatchesUseCase: GetMatchesUseCase):
        PageKeyedDataSource<Long, Games>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Games>
    ) {
        compositeDisposable.add(
            getMatchesUseCase.get(GetMatchesUseCase.Params(userName, System.currentTimeMillis() / 1000))
                .subscribe({
                    callback.onResult(it.games, null, it.games.last().createDate)
                }, {
                    callback.onResult(emptyList(), null, null)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Games>) {
        compositeDisposable.add(
            getMatchesUseCase.get(GetMatchesUseCase.Params(userName, params.key))
                .subscribe({
                    callback.onResult(it.games, it.games.last().createDate)
                }, {

                })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Games>) {

    }
}