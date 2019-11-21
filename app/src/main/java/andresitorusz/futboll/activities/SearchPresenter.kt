package andresitorusz.futboll.activities

import andresitorusz.futboll.models.MatchesResponse
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.networks.TheSportDBAPI
import andresitorusz.futboll.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class SearchPresenter(
    private val searchView: SearchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    private var jobSearch: Job? = null

    fun searchMatch(eventName: String?) {
        jobSearch = GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.searchEvent(eventName)),
                MatchesResponse::class.java
            )
            try {
                searchView.showLoading()
                searchView.showEventList(data.event.filter { it.eventLeague.equals("Soccer") })
            } catch (e: NullPointerException) {
                searchView.showEmpty()
            }
            searchView.hideLoading()
        }
        jobSearch?.start()
    }

    fun cancelCoroutine() {
        if (jobSearch!!.isActive && !(jobSearch!!.isCompleted)) {
            jobSearch?.cancel()
        }
    }
}