package kr.wayde.opgg.remote

import io.reactivex.Single
import kr.wayde.opgg.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpggService {

    @GET("summoner/{userName}/matches")
    fun getMatches(
        @Path("userName") userName: String,
        @Query(value = "lastMatch") lastMatch: Long
    ): Single<MatchesModel>

    @GET("summoner/{userName}")
    fun getSummonerInfo(@Path("userName") userName: String): Single<SummonerModel>
}
