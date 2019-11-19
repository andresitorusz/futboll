package andresitorusz.futboll.adapters

import andresitorusz.futboll.R
import andresitorusz.futboll.models.Match
import andresitorusz.futboll.utils.dateFormatter
import andresitorusz.futboll.utils.timeFormatter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_matches.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat

class EventAdapter(private val matchLists: List<Match>) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(match: Match) {
            val newDate = if (match.eventDate != null) dateFormatter(match.eventDate) else "No Date"
            val newTime = if (match.eventTime != null) timeFormatter(match.eventTime) else "No Time"
            itemView.match_date.text = newDate
            itemView.match_time.text = newTime
            itemView.match_home_team.text =
                if (match.homeName != null) match.homeName else "No Home Team"
            itemView.match_away_team.text =
                if (match.awayName != null) match.awayName else "No Away Team"
            itemView.match_home_score.text = match.homeScore
            itemView.match_away_score.text = match.awayScore
            itemView.setOnClickListener {
                //               //Fill this later
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_matches, parent, false
        )
    )


    override fun getItemCount(): Int = matchLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(matchLists[position])
}
