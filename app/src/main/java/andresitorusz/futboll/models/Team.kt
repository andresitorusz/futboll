package andresitorusz.futboll.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Team(

    var id: Long? = null,

    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strSport")
    var teamSport: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeamFanart1")
    var teamBanner: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(teamId)
        parcel.writeString(teamSport)
        parcel.writeString(teamName)
        parcel.writeString(teamDescription)
        parcel.writeString(teamBadge)
        parcel.writeString(teamBanner)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        const val TABLE_FAVORITE_TEAM = "table_favorite_team"
        const val ID: String = "_ID"
        const val TEAM_ID: String = "team_id"
        const val TEAM_SPORT: String = "team_sport"
        const val TEAM_NAME: String = "team_name"
        const val TEAM_DESCRIPTION = "team_description"
        const val TEAM_BADGE: String = "team_badge"
        const val TEAM_BANNER: String = "team_banner"

        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}