package andresitorusz.futboll.models

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strSport")
    var leagueSport: String? = null
) {
    override fun toString(): String =
        leagueName.toString()
}