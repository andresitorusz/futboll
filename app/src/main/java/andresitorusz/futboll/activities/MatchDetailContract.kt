package andresitorusz.futboll.activities

import andresitorusz.futboll.models.Event
import andresitorusz.futboll.models.Team

interface MatchDetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetailTeam(homeTeam: List<Team>, awayTeam: List<Team>)
        fun showDetailEvent(data: List<Event>)
    }
}