package andresitorusz.futboll.fragments.match.last

import andresitorusz.futboll.fragments.match.EventContract
import andresitorusz.futboll.models.League
import andresitorusz.futboll.models.LeagueResponse
import andresitorusz.futboll.models.MatchResponse
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.networks.TheSportDBAPI
import andresitorusz.futboll.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class LastEventPresenter(
    private val lastView: EventContract.View?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    private var jobLastEvent: Job? = null
    private var jobListLeague: Job? = null

    fun getLastEventList(leagueId: String?) {
        jobLastEvent = GlobalScope.launch(context.main) {
            lastView?.showLoading()
            val data: MatchResponse? = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getLastEvents(leagueId)),
                MatchResponse::class.java
            )
            try {
                lastView?.showEventList(data?.events)
            } catch (e: Exception) {
                lastView?.showEmpty()
            }
            lastView?.hideLoading()
        }
        jobLastEvent?.start()
    }

    fun getListLeague() {
        jobListLeague = GlobalScope.launch(context.main) {
            lastView?.showLoading()
            val league: LeagueResponse? = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getLeague()),
                LeagueResponse::class.java
            )
            lastView?.showLeagues(league?.leagues?.filter { it.leagueSport.equals("Soccer") })
            lastView?.hideLoading()
        }
        jobListLeague?.start()
    }

    fun cancelCoroutine() {
        if (jobListLeague!!.isActive && !(jobListLeague!!.isCompleted)) {
            jobListLeague?.cancel()
        }
        if (jobLastEvent!!.isActive && !(jobLastEvent!!.isCompleted)) {
            jobLastEvent?.cancel()
        }
    }
}