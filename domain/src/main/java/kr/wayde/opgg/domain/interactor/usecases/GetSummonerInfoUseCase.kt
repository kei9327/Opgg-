package kr.wayde.opgg.domain.interactor.usecases

import io.reactivex.Single
import kr.wayde.opgg.domain.entity.Summoner
import kr.wayde.opgg.domain.interactor.SingleUseCase
import kr.wayde.opgg.domain.repository.OpggRepository
import kr.wayde.opgg.domain.schedulers.SchedulersProvider

class GetSummonerInfoUseCase(
    private val opggRepository: OpggRepository,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<Summoner, String>(schedulersProvider) {
    override fun buildUseCaseSingle(userName: String): Single<Summoner> =
        opggRepository.getSummonerInfo(userName)
}
