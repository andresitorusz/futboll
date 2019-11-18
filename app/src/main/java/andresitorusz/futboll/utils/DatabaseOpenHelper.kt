package andresitorusz.futboll.utils

import andresitorusz.futboll.models.Team
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "FavoriteDB.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) instance = DatabaseOpenHelper(context.applicationContext)
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Team.TABLE_FAVORITE_TEAM, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Team.TEAM_ID to TEXT + UNIQUE,
            Team.TEAM_SPORT to TEXT,
            Team.TEAM_NAME to TEXT,
            Team.TEAM_DESCRIPTION to TEXT,
            Team.TEAM_BADGE to TEXT,
            Team.TEAM_BANNER to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Team.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)