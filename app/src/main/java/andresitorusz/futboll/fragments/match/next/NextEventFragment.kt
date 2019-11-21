package andresitorusz.futboll.fragments.match.next


import android.os.Bundle
import androidx.fragment.app.Fragment

import andresitorusz.futboll.R
import andresitorusz.futboll.adapters.EventAdapter
import andresitorusz.futboll.fragments.match.EventContract
import andresitorusz.futboll.models.League
import andresitorusz.futboll.models.Match
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.utils.invisible
import andresitorusz.futboll.utils.visible
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_event.*

/**
 * A simple [Fragment] subclass.
 */
class NextEventFragment : Fragment(), EventContract.View {

    private lateinit var presenter: NextEventPresenter
    private lateinit var adapter: EventAdapter
    private var matchLists: MutableList<Match> = mutableListOf()
    private var leagueLists: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventAdapter(matchLists)
        recycler_view_next.layoutManager = LinearLayoutManager(context)
        recycler_view_next.adapter = adapter
        presenter = NextEventPresenter(this, ApiRepository(), Gson())
        presenter.getListLeague()
        retainInstance = true
    }

    override fun showLoading() {
        progress_bar_next.visible()
        recycler_view_next.invisible()
        empty_next_event.invisible()
    }

    override fun hideLoading() {
        progress_bar_next.invisible()
        recycler_view_next.visible()
        empty_next_event.invisible()
    }

    override fun showEmpty() {
        progress_bar_next.invisible()
        recycler_view_next.invisible()
        empty_next_event.visible()
    }

    override fun showEventList(data: List<Match>?) {
        matchLists.clear()
        if (data != null) {
            matchLists.addAll(data)
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
        next_event_spinner.adapter = spinnerAdapter
        next_event_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val leagueName: League = next_event_spinner.selectedItem as League
                presenter.getNextEventList(leagueName.leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelCoroutine()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_matches, menu)
    }
}
