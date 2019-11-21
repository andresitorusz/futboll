package andresitorusz.futboll.activities

import andresitorusz.futboll.models.Match

interface SearchContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showEventList(data: List<Match>)
        fun showEmpty()
    }
}