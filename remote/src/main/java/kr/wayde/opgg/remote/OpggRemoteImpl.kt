package kr.wayde.opgg.remote

import io.reactivex.Single
import kr.wayde.opgg.data.repository.OpggRemote
import kr.wayde.opgg.domain.entity.*
import kr.wayde.opgg.remote.mapper.MatchesEntityMapper
import kr.wayde.opgg.remote.mapper.SummonerEntityMapper

class OpggRemoteImpl(
    private val opggService: OpggService,
    private val mathcesEntityMapper: MatchesEntityMapper,
    private val summonerEntityMapper: SummonerEntityMapper
    ) : OpggRemote {
    override fun getMatches(userName: String, lastMatch: Long): Single<Matches> =
        opggService
            .getMatches(userName, lastMatch)
            .map { mathcesEntityMapper.mapFromRemote(it) }

    override fun getSummonerInfo(userName: String): Single<Summoner> =
        opggService.getSummonerInfo(userName).map {
            summonerEntityMapper.mapFromRemote(it)
        }
}
