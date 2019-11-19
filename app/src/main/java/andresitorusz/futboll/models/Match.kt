package andresitorusz.futboll.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Match(
    var id: Long? = null,

    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("idHomeTeam")
    var homeId: String? = null,

    @SerializedName("idAwayTeam")
    var awayId: String? = null,

    @SerializedName("strSport")
    var eventLeague: String? = null,

    @SerializedName("strEvent")
    var eventName: String? = null,

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("strHomeTeam")
    var homeName: String? = null,

    @SerializedName("strAwayTeam")
    var awayName: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(eventId)
        parcel.writeString(homeId)
        parcel.writeString(awayId)
        parcel.writeString(eventLeague)
        parcel.writeString(eventName)
        parcel.writeString(eventDate)
        parcel.writeString(eventTime)
        parcel.writeString(homeName)
        parcel.writeString(awayName)
        parcel.writeString(homeScore)
        parcel.writeString(awayScore)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        const val TABLE_FAVORITE_MATCH: String = "table_favorite_match"
        const val ID: String = "_ID"
        const val EVENT_ID: String = "event_id"
        const val HOME_ID: String = "home_id"
        const val AWAY_ID: String = "away_id"
        const val EVENT_LEAGUE: String = "event_league"
        const val EVENT_NAME: String = "event_name"
        const val EVENT_DATE: String = "event_date"
        const val EVENT_TIME: String = "event_time"
        const val HOME_NAME: String = "home_name"
        const val AWAY_NAME: String = "away_name"
        const val HOME_SCORE: String = "home_score"
        const val AWAY_SCORE: String = "away_score"

        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }

    }
}