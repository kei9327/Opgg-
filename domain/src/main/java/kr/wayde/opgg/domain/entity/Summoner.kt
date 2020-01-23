package kr.wayde.opgg.domain.entity

data class Summoner(
    val name: String,
    val level: Int,
    val profileImageUrl: String,
    val profileBorderImageUrl: String,
    val url: String,
    val leagues: List<Leagues>,
    val profileBackgroundImageUrl: String
)

data class Leagues(
    val hasResults: Boolean,
    val wins: Int,
    val losses: Int,
    val tierRank: TierRank
)

data class TierRank(
    val name: String,
    val tier: String,
    val tierDivision: String,
    val string: String,
    val shortString: String,
    val division: String,
    val imageUrl: String,
    val lp: Int,
    val tierRankPoint: Int
)