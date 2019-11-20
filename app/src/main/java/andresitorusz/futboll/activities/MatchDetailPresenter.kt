package andresitorusz.futboll.activities

import andresitorusz.futboll.models.EventResponse
import andresitorusz.futboll.models.Match
import andresitorusz.futboll.models.TeamResponse
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.networks.TheSportDBAPI
import andresitorusz.futboll.utils.CoroutineContextProvider
import andresitorusz.futboll.utils.database
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class MatchDetailPresenter(
    private val matchDetailView: MatchDetailContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: Context,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    private suspend fun getTeam(teamId: String?) =
        gson.fromJson(
            apiRepository.doRequest(TheSportDBAPI.getDetailTeam(teamId)),
            TeamResponse::class.java
        )

    fun getDetailTeam(homeId: String?, awayId: String?) {
        matchDetailView.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val home = getTeam(homeId)
            val away = getTeam(awayId)
            matchDetailView.hideLoading()
            matchDetailView.showDetailTeam(home.teams, away.teams)
        }
    }

    fun getDetailEvent(eventId: String?) {
        matchDetailView.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getDetailEvent(eventId)),
                EventResponse::class.java
            )
            matchDetailView.showDetailEvent(data.events)
            matchDetailView.hideLoading()
        }
    }
}
