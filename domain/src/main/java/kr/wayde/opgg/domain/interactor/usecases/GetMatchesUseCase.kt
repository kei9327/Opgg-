package kr.wayde.opgg.domain.interactor.usecases

import io.reactivex.Single
import kr.wayde.opgg.domain.entity.Matches
import kr.wayde.opgg.domain.interactor.SingleUseCase
import kr.wayde.opgg.domain.repository.OpggRepository
import kr.wayde.opgg.domain.schedulers.SchedulersProvider

class GetMatchesUseCase(
    private val opggRepository: OpggRepository,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<Matches, GetMatchesUseCase.Params>(schedulersProvider) {
    override fun buildUseCaseSingle(params: Params): Single<Matches>
        = opggRepository.getMatches(userName = params.userName, lastMatch = params.lastMatch)

    data class Params(val userName: String, val lastMatch: Long)
}
