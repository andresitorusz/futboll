package andresitorusz.futboll.fragments.team.player

import andresitorusz.futboll.models.Player

interface PlayerContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showAllPlayer(data: List<Player>)
    }
}