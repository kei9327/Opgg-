package kr.wayde.opgg.remote.model

import com.google.gson.annotations.SerializedName

data class MatchesModel(
    @SerializedName("games") val games: List<Games>,
    @SerializedName("champions") val champions: List<Champions>,
    @SerializedName("positions") val positions: List<Positions>
)

data class Games(
    @SerializedName("mmr") val mmr: Int,
    @SerializedName("champion") val champion: Champion,
    @SerializedName("spells") val spells: List<Spells>,
    @SerializedName("items") val items: List<Items>,
    @SerializedName("needRenew") val needRenew: Boolean,
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("createDate") val createDate: Long,
    @SerializedName("gameLength") val gameLength: Int,
    @SerializedName("gameType") val gameType: String,
    @SerializedName("summonerId") val summonerId: Int,
    @SerializedName("summonerName") val summonerName: String,
    @SerializedName("tierRankShort") val tierRankShort: String,
    @SerializedName("stats") val stats: Stats,
    @SerializedName("mapInfo") val mapInfo: String,
    @SerializedName("peak") val peak: List<String>,
    @SerializedName("isWin") val isWin: Boolean
)

data class Champions(
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("games") val games: Int,
    @SerializedName("kills") val kills: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("assists") val assists: Int,
    @SerializedName("wins") val wins: Int,
    @SerializedName("losses") val losses: Int
)

data class Champion(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("level") val level: Int
)

data class Positions(
    @SerializedName("games") val games: Int,
    @SerializedName("wins") val wins: Int,
    @SerializedName("losses") val losses: Int,
    @SerializedName("position") val position: String,
    @SerializedName("positionName") val positionName: String
)

data class Spells(@SerializedName("imageUrl") val imageUrl: String)

data class Items(@SerializedName("imageUrl") val imageUrl: String)

data class Stats(
    @SerializedName("general") val general: General,
    @SerializedName("ward") val ward: Ward
)

data class General(
    @SerializedName("kill") val kill: Int,
    @SerializedName("death") val death: Int,
    @SerializedName("assist") val assist: Int,
    @SerializedName("kdaString") val kdaString: String,
    @SerializedName("cs") val cs: Int,
    @SerializedName("csPerMin") val csPerMin: Double,
    @SerializedName("contributionForKillRate") val contributionForKillRate: String,
    @SerializedName("goldEarned") val goldEarned: Int,
    @SerializedName("totalDamageDealtToChampions") val totalDamageDealtToChampions: Int,
    @SerializedName("largestMultiKillString") val largestMultiKillString: String,
    @SerializedName("opScoreBadge") val opScoreBadge: String
)

data class Ward(
    @SerializedName("sightWardsBought") val sightWardsBought: Int,
    @SerializedName("visionWardsBought") val visionWardsBought: Int
)