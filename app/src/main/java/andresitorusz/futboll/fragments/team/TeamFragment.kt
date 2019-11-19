package andresitorusz.futboll.fragments.team


import andresitorusz.futboll.R
import andresitorusz.futboll.adapters.TeamAdapter
import andresitorusz.futboll.models.League
import andresitorusz.futboll.models.Team
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.utils.invisible
import andresitorusz.futboll.utils.visible
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamContract.View {
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: League
    private var teamLists: MutableList<Team> = mutableListOf()
    private var leagueLists: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        adapter = TeamAdapter(teamLists)
        rv_team.layoutManager = GridLayoutManager(context, 3)
        rv_team.adapter = adapter
        presenter =
            TeamPresenter(this, ApiRepository(), Gson())
        presenter.getListLeague()
    }

    override fun showLoading() {
        progress_bar_team.visible()
        empty_team.invisible()
        rv_team.invisible()
    }

    override fun hideLoading() {
        progress_bar_team.invisible()
        empty_team.invisible()
        rv_team.visible()
    }

    override fun showEmpty() {
        progress_bar_team.invisible()
        empty_team.visible()
        rv_team.invisible()
    }

    override fun showTeamList(data: List<Team>?) {
        teamLists.clear()
        if (data != null) {
            teamLists.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun showLeagues(data: List<League>?) {
        leagueLists.clear()
        if (data != null) {
            leagueLists.addAll(data)
        }
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            leagueLists
        )
        spinner_team.adapter = spinnerAdapter
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                leagueName = spinner_team.selectedItem as League
                presenter.getListTeam(leagueName.leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelCoroutine()
    }

}
