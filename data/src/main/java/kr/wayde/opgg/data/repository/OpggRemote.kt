package kr.wayde.opgg.data.repository

import io.reactivex.Single
import kr.wayde.opgg.domain.entity.*

interface OpggRemote{
    fun getMatches(userName: String, lastMatch: Long): Single<Matches>
    fun getSummonerInfo(userName: String): Single<Summoner>
}
