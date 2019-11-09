package andresitorusz.futboll.presenter.team

import andresitorusz.futboll.model.team.TeamResponse
import andresitorusz.futboll.network.ApiRepository
import andresitorusz.futboll.network.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter (private val view: TeamView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view
                    .showTeamList(data.teams)
            }
        }
    }
}