package kr.wayde.opgg.data.interactor

import io.reactivex.Single
import kr.wayde.opgg.domain.repository.OpggRepository
import kr.wayde.opgg.data.repository.OpggRemote
import kr.wayde.opgg.domain.entity.*


class OpggDataSource(
    private val remote: OpggRemote
) : OpggRepository {
    override fun getMatches(userName: String, lastMatch: Long): Single<Matches>
            = remote.getMatches(userName, lastMatch)

    override fun getSummonerInfo(userName: String): Single<Summoner>
            = remote.getSummonerInfo(userName)
}
