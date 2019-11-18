package andresitorusz.futboll.adapters

import andresitorusz.futboll.R
import andresitorusz.futboll.models.Player
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_teams.view.*

class PlayerAdapter(private val playerList: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(player: Player) {
            Glide.with(itemView.context)
                .load(player.playerProfile)
                .apply(RequestOptions().error(R.drawable.ic_error_player))
                .apply(RequestOptions().placeholder(R.drawable.ic_hourglass_empty_black_24dp))
                .apply(RequestOptions().override(120, 120))
                .into(itemView.image_teams)
            itemView.text_teams.text = player.playerName
            itemView.setOnClickListener {

            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(playerList[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false))

    override fun getItemCount(): Int =
        playerList.size


}