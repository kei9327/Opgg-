package kr.wayde.opgg.domain.entity

data class Matches(
    val games: List<Games>,
    val champions: List<Champions>,
    val positions: List<Positions>
)

data class Games(
    val mmr: Int,
    val champion: Champion,
    val spells: List<Spells>,
    val items: List<Items>,
    val needRenew: Boolean,
    val gameId: Int,
    val createDate: Long,
    val gameLength: Int,
    val gameType: String,
    val summonerId: Int,
    val summonerName: String,
    val tierRankShort: String,
    val stats: Stats,
    val mapInfo: String,
    val peak: List<String>,
    val isWin: Boolean
)

data class Champions(
    val name: String,
    val imageUrl: String,
    val games: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val wins: Int,
    val losses: Int
)

data class Champion(val imageUrl: String, val level: Int)

data class Positions(
    val games: Int,
    val wins: Int,
    val losses: Int,
    val position: String,
    val positionName: String
)

data class Spells(val imageUrl: String)

data class Items(val imageUrl: String)

data class Stats(val general: General, val ward: Ward)

data class General(
    val kill: Int,
    val death: Int,
    val assist: Int,
    val kdaString: String,
    val cs: Int,
    val csPerMin: Double,
    val contributionForKillRate: String,
    val goldEarned: Int,
    val totalDamageDealtToChampions: Int,
    val largestMultiKillString: String,
    val opScoreBadge: String
)

data class Ward(val sightWardsBought: Int, val visionWardsBought: Int)