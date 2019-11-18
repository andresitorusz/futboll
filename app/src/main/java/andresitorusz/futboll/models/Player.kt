package andresitorusz.futboll.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strWeight")
    var playerWeight: String? = null,

    @SerializedName("strHeight")
    var playerHeight: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDescription: String? = null,

    @SerializedName("strCutout")
    var playerProfile: String? = null,

    @SerializedName("strFanart1")
    var playerBack: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(playerId)
        parcel.writeString(playerName)
        parcel.writeString(playerWeight)
        parcel.writeString(playerHeight)
        parcel.writeString(playerPosition)
        parcel.writeString(playerDescription)
        parcel.writeString(playerProfile)
        parcel.writeString(playerBack)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
