package kr.wayde.opgg.remote.model

import com.google.gson.annotations.SerializedName

data class SummonerModel(@SerializedName("summoner") val summoner: Summoner)

data class Summoner(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Int,
    @SerializedName("profileImageUrl") val profileImageUrl: String,
    @SerializedName("profileBorderImageUrl") val profileBorderImageUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("leagues") val leagues: List<Leagues>,
    @SerializedName("profileBackgroundImageUrl") val profileBackgroundImageUrl: String
)

data class Leagues(
    @SerializedName("hasResults") val hasResults: Boolean,
    @SerializedName("wins") val wins: Int,
    @SerializedName("losses") val losses: Int,
    @SerializedName("tierRank") val tierRank: TierRank
)

data class TierRank(
    @SerializedName("name") val name: String,
    @SerializedName("tier") val tier: String,
    @SerializedName("tierDivision") val tierDivision: String,
    @SerializedName("string") val string: String,
    @SerializedName("shortString") val shortString: String,
    @SerializedName("division") val division: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("lp") val lp: Int,
    @SerializedName("tierRankPoint") val tierRankPoint: Int
)