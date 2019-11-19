package andresitorusz.futboll.activities

import andresitorusz.futboll.R
import andresitorusz.futboll.fragments.team.overview.OverviewFragment
import andresitorusz.futboll.fragments.team.player.PlayerFragment
import andresitorusz.futboll.models.Team
import andresitorusz.futboll.utils.ViewPagerUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.layout_detail_team.*

class TeamDetailActivity : AppCompatActivity() {
    private var team: Team? = null
    private lateinit var page: Map<Fragment, String>

    companion object {
        const val KEY_TEAM = "key_team"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        team = intent.getParcelableExtra(KEY_TEAM)
        val teamId = team?.teamId
        val teamDescription = team?.teamDescription

        Glide.with(this)
            .load(team?.teamBanner)
            .apply(RequestOptions().error(R.drawable.ic_hourglass_empty_black_24dp))
            .into(team_detail_banner)

        page = mutableMapOf(
            OverviewFragment.newInstance(teamDescription) to getString(R.string.overview), PlayerFragment.newInstance(teamId) to getString(
                    R.string.players))
        detail_team_view_pager.adapter = ViewPagerUtils(supportFragmentManager, page)
        detail_team_tab.setupWithViewPager(detail_team_view_pager)
    }
}
