package andresitorusz.futboll.fragments

import andresitorusz.futboll.models.PlayerResponse
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.networks.TheSportDBAPI
import andresitorusz.futboll.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val playerView: PlayerContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getAllPlayer(teamId: String?) {
        playerView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBAPI.getAllPlayer(teamId)),
                PlayerResponse::class.java
            )
            playerView.showAllPlayer(data.player)
            playerView.hideLoading()
        }
    }
}