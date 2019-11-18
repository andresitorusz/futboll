package andresitorusz.futboll.activities

import andresitorusz.futboll.models.Team
import andresitorusz.futboll.utils.database
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class TeamDetailDatabase(private val context: Context) {
    fun addRowTeam(data: Team) {
        try {
            context.database.use {
                insert(
                    Team.TABLE_FAVORITE_TEAM,
                    Team.TEAM_ID to data.teamId,
                    Team.TEAM_SPORT to data.teamSport,
                    Team.TEAM_NAME to data.teamName,
                    Team.TEAM_DESCRIPTION to data.teamDescription,
                    Team.TEAM_BADGE to data.teamBadge,
                    Team.TEAM_BANNER to data.teamBanner
                )
            }
        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }
    }

    fun removeRowTeam(data: Team) {
        try {
            context.database.use {
                delete(
                    Team.TABLE_FAVORITE_TEAM,
                    Team.TEAM_ID + "={id}",
                    "id" to data.teamId.toString()
                )
            }
        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }
    }
}