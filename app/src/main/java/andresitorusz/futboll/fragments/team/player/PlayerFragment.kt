package andresitorusz.futboll.fragments.team.player


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andresitorusz.futboll.R
import andresitorusz.futboll.adapters.PlayerAdapter
import andresitorusz.futboll.models.Player
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.utils.invisible
import andresitorusz.futboll.utils.visible
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment(), PlayerContract.View {
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private var playerLists: MutableList<Player> = mutableListOf()

    companion object {
        private const val KEY_ALL_PLAYER = "key_all_player"
        fun newInstance(values: String?): PlayerFragment {
            val playerFragment = PlayerFragment()
            val args = Bundle()
            args.putString(KEY_ALL_PLAYER, values)
            playerFragment.arguments = args
            return playerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamId = arguments?.getString(KEY_ALL_PLAYER)
        presenter =
            PlayerPresenter(
                this,
                ApiRepository(),
                Gson()
            )
        adapter = PlayerAdapter(playerLists)
        recycler_view_player.layoutManager = GridLayoutManager(context, 3)
        recycler_view_player.adapter = adapter
        presenter.getAllPlayer(teamId)
    }

    override fun showLoading() = progress_bar_player.visible()

    override fun hideLoading() = progress_bar_player.invisible()

    override fun showAllPlayer(data: List<Player>) {
        playerLists.clear()
        playerLists.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
