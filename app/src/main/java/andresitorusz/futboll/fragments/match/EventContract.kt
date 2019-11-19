package andresitorusz.futboll.fragments.match

import andresitorusz.futboll.models.League
import andresitorusz.futboll.models.Match

interface EventContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showEventList(data: List<Match>?)
        fun showLeagues(data: List<League>?)
        fun showEmpty()
    }
}