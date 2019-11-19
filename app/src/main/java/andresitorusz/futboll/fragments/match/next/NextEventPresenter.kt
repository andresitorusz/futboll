package andresitorusz.futboll.fragments.match.next

import andresitorusz.futboll.fragments.match.EventContract
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

class NextEventPresenter(
    private val nextView: EventContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    private var jobNextEvent: Job? = null
    private var jobListLeague: Job? = null

    fun getNextEventList(leagueId: String?) {
        jobNextEvent = GlobalScope.launch(context.main) {
            nextView.showLoading()
            val data: MatchResponse? = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getNextEvents(leagueId)),
                MatchResponse::class.java
            )
            try {
                nextView.showEventList(data?.events)
            } catch (e: Exception) {
                nextView.showEmpty()
            }
            nextView.hideLoading()
        }
        jobNextEvent?.start()
    }

    fun getListLeague() {
        jobListLeague = GlobalScope.launch(context.main) {
            nextView.showLoading()
            val league: LeagueResponse? = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getLeague()),
                LeagueResponse::class.java
            )
            nextView.showLeagues(league?.leagues?.filter { it.leagueSport.equals("Soccer") })
            nextView.hideLoading()
        }
        jobListLeague?.start()
    }

    fun cancelCoroutine() {
        if (jobListLeague!!.isActive && !(jobListLeague!!.isCompleted)) {
            jobListLeague?.cancel()
        }
        if (jobNextEvent!!.isActive && !(jobNextEvent!!.isCompleted)) {
            jobNextEvent?.cancel()
        }
    }

}