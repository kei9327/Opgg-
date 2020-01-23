package kr.wayde.opgg.remote.mapper

import kr.wayde.opgg.domain.entity.Leagues
import kr.wayde.opgg.domain.entity.Summoner
import kr.wayde.opgg.domain.entity.TierRank
import kr.wayde.opgg.remote.model.SummonerModel

class SummonerEntityMapper : EntityMapper<SummonerModel, Summoner> {
    override fun mapFromRemote(model: SummonerModel) = Summoner(
        model.summoner.name?: "",
        model.summoner.level,
        model.summoner.profileImageUrl?: "",
        model.summoner.profileBorderImageUrl?: "",
        model.summoner.profileImageUrl?: "",
        model.summoner.leagues.map {
            Leagues(
                it.hasResults,
                it.wins,
                it.losses,
                TierRank(
                    it.tierRank.name?: "",
                    it.tierRank.tier?: "",
                    it.tierRank.tierDivision?: "",
                    it.tierRank.shortString?: "",
                    it.tierRank.string?: "",
                    it.tierRank.division?: "",
                    it.tierRank.imageUrl?: "",
                    it.tierRank.lp,
                    it.tierRank.tierRankPoint
                )
            )
        },
        model.summoner.profileBackgroundImageUrl?: ""
    )
}
