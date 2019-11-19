package andresitorusz.futboll.fragments.team

import andresitorusz.futboll.models.LeagueResponse
import andresitorusz.futboll.models.TeamResponse
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.networks.TheSportDBAPI
import andresitorusz.futboll.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeamPresenter(
    private val teamView: TeamContract.View,
    private val repository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    private var jobTeam: Job? = null
    private var jobLeague: Job? = null

    fun getListTeam(leagueId: String?) {
        jobTeam = GlobalScope.launch(context.main) {
            teamView.showLoading()
            val data = gson.fromJson(
                repository.doRequest(TheSportDBAPI.getAllTeam(leagueId)),
                TeamResponse::class.java
            )
            try {
                teamView.showTeamList(data.teams)
            } catch (e: NullPointerException) {
                teamView.showEmpty()
            }
            teamView.hideLoading()
        }
        jobTeam?.start()
    }

    fun getListLeague() {
        jobLeague = GlobalScope.launch(context.main) {
            teamView.showLoading()
            val league: LeagueResponse? = gson.fromJson(
                repository.doRequest(TheSportDBAPI.getLeague()),
                LeagueResponse::class.java
            )
            teamView.showLeagues(league?.leagues?.filter { it.leagueSport.equals("Soccer") })
            teamView.hideLoading()
        }
        jobLeague?.start()
    }

    fun searchTeam(teamName: String?) {
        teamView.showLoading()
        GlobalScope.launch(context.main) {
            val data: TeamResponse? = gson.fromJson(
                repository.doRequest(TheSportDBAPI.searchTeam(teamName)),
                TeamResponse::class.java
            )
            try {
                teamView.showTeamList(data?.teams?.filter { it.teamSport.equals("Soccer") })
            } catch (e: NullPointerException) {
                teamView.showEmpty()
            }
            teamView.hideLoading()
        }
    }

    fun cancelCoroutine() {
        if (jobLeague!!.isActive && !(jobLeague!!.isCompleted)) {
            jobLeague!!.cancel()
        }
        if (jobTeam!!.isActive && !(jobTeam!!.isCompleted)) {
            jobTeam!!.cancel()
        }
    }
}