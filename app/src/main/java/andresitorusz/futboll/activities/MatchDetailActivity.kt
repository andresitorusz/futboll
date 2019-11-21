package andresitorusz.futboll.activities

import andresitorusz.futboll.R
import andresitorusz.futboll.models.Event
import andresitorusz.futboll.models.Match
import andresitorusz.futboll.models.Team
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.utils.dateFormatter
import andresitorusz.futboll.utils.invisible
import andresitorusz.futboll.utils.timeFormatter
import andresitorusz.futboll.utils.visible
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.item_matches.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailContract.View {
    private lateinit var presenter: MatchDetailPresenter
    private var itemList: Match? = null

    companion object {
        const val KEY_MATCH = "key_match"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        supportActionBar?.title = "Futboll"

        itemList = intent.getParcelableExtra(KEY_MATCH)
        presenter = MatchDetailPresenter(this, ApiRepository(), Gson(), this)
        val homeId = if (itemList!!.homeId != null) itemList?.homeId else " "
        val awayId = if (itemList!!.awayId != null) itemList?.awayId else " "
        presenter.getDetailTeam(homeId, awayId)
        presenter.getDetailEvent(itemList?.eventId)
        match_detail_date.text =
            if (itemList?.eventDate != null) dateFormatter(itemList?.eventDate) else "No Date"
        match_detail_time.text =
            if (itemList?.eventTime != null) timeFormatter(itemList?.eventTime) else "No Time"
        match_detail_home_name.text =
            if (itemList?.homeName != null) itemList?.homeName else "No Home"
        match_detail_away_name.text =
            if (itemList?.awayName != null) itemList?.awayName else "No Away"
        match_detail_home_score.text = itemList?.homeScore
        match_detail_away_score.text = itemList?.awayScore
    }

    private fun setLogo(imgUrl: String?, imageView: ImageView) =
        Glide
            .with(applicationContext)
            .load(imgUrl)
            .apply(RequestOptions().error(R.drawable.ic_hourglass_empty_black_24dp))
            .apply(RequestOptions().override(100, 100))
            .into(imageView)

    override fun showLoading() = progress_bar_match_detail.visible()

    override fun hideLoading() = progress_bar_match_detail.invisible()

    override fun showDetailTeam(homeTeam: List<Team>, awayTeam: List<Team>) {
        setLogo(homeTeam[0].teamBadge, match_detail_home_logo)
        setLogo(awayTeam[0].teamBadge, match_detail_away_logo)
    }

    override fun showDetailEvent(data: List<Event>) {
        Log.d("String", (data[0].awayShots).toString())
        match_goal_home.text = data[0].homeGoalPlayer
        match_goal_away.text = data[0].awayGoalPlayer
        match_home_shot.text = data[0].homeShots
        match_away_shot.text = data[0].awayShots
        goalkeeper_home.text = data[0].homeGoalKeeperName
        goalkeeper_away.text = data[0].awayGoalKeeperName
        defense_home.text = data[0].homeDefense
        defense_away.text = data[0].awayDefense
        midfield_home.text = data[0].homeMidField
        midfield_away.text = data[0].awayMidField
        forward_home.text = data[0].homeForward
        forward_away.text = data[0].awayForward
        substitutes_home.text = data[0].homeSubstitutes
        substitutes_away.text = data[0].awaySubstitutes
    }
}
