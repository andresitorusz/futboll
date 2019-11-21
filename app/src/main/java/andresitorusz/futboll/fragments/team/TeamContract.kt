package andresitorusz.futboll.fragments.team

import andresitorusz.futboll.models.League
import andresitorusz.futboll.models.Team

interface TeamContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showEmpty()
        fun showTeamList(data: List<Team>?)
        fun showLeagues(data: List<League>?)
    }
}