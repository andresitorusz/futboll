package andresitorusz.futboll.adapters

import andresitorusz.futboll.R
import andresitorusz.futboll.activities.TeamDetailActivity
import andresitorusz.futboll.models.Team
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(private val teamList: List<Team>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.TeamViewHolder =
        TeamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teams,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bind(teamList[position])

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            itemView.text_teams.text = team.teamName
            Glide.with(itemView.context)
                .load(team.teamBadge)
                .apply(RequestOptions().error(R.drawable.ic_hourglass_empty_black_24dp))
                .apply(RequestOptions().override(120, 120))
                .into(itemView.image_teams)

            itemView.setOnClickListener {
                itemView.context.startActivity<TeamDetailActivity>(TeamDetailActivity.KEY_TEAM to team)
            }
        }
    }

}