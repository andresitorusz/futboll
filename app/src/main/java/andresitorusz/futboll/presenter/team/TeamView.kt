package andresitorusz.futboll.presenter.team

import andresitorusz.futboll.model.team.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}