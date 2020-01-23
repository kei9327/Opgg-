package kr.wayde.opgg.remote.mapper

import kr.wayde.opgg.domain.entity.*
import kr.wayde.opgg.remote.model.MatchesModel

class MatchesEntityMapper : EntityMapper<MatchesModel, Matches> {
    override fun mapFromRemote(model: MatchesModel) = Matches(
        model.games.map {
            Games(
                it.mmr,
                Champion(it.champion.imageUrl, it.champion.level),
                it.spells.map { Spells(it.imageUrl) },
                it.items.map { Items(it.imageUrl) },
                it.needRenew,
                it.gameId,
                it.createDate,
                it.gameLength,
                it.gameType?: "",
                it.summonerId,
                it.summonerName?: "",
                it.tierRankShort?: "",
                Stats(
                    General(
                        it.stats.general.kill,
                        it.stats.general.death,
                        it.stats.general.assist,
                        it.stats.general.kdaString?: "",
                        it.stats.general.cs,
                        it.stats.general.csPerMin,
                        it.stats.general.contributionForKillRate?: "",
                        it.stats.general.goldEarned,
                        it.stats.general.totalDamageDealtToChampions,
                        it.stats.general.largestMultiKillString?: "",
                        it.stats.general.opScoreBadge?: ""
                    ),
                    Ward(
                        it.stats.ward.sightWardsBought,
                        it.stats.ward.visionWardsBought
                    )
                ),
                it.mapInfo?: "",
                it.peak,
                it.isWin
            )
        },
        model.champions.map {
            Champions(
                it.name?: "",
                it.imageUrl?: "",
                it.games,
                it.kills,
                it.deaths,
                it.assists,
                it.wins,
                it.losses
            )
        },
        model.positions.map {
            Positions(
                it.games,
                it.wins,
                it.losses,
                it.position?: "",
                it.positionName?: "")
        }
    )
}
