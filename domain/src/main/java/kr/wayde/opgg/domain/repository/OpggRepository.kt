package kr.wayde.opgg.domain.repository

import io.reactivex.Single
import kr.wayde.opgg.domain.entity.*

interface OpggRepository {
    fun getMatches(userName: String, lastMatch: Long): Single<Matches>
    fun getSummonerInfo(userName: String): Single<Summoner>
}