package andresitorusz.futboll.view.fragments.team

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.*
import andresitorusz.futboll.R.id.team_badge
import andresitorusz.futboll.R.id.team_name
import andresitorusz.futboll.model.team.Team
import andresitorusz.futboll.presenter.team.TeamPresenter

class TeamFragmentUI : AnkoComponent<TeamFragment> {

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
//    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String

    override fun createView(ui: AnkoContext<TeamFragment>): View = with(ui){
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }
}