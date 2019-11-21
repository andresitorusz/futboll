package andresitorusz.futboll.activities

import andresitorusz.futboll.R
import andresitorusz.futboll.adapters.EventAdapter
import andresitorusz.futboll.models.Match
import andresitorusz.futboll.networks.ApiRepository
import andresitorusz.futboll.utils.invisible
import andresitorusz.futboll.utils.visible
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import andresitorusz.futboll.R.id.search_matches
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {
    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: EventAdapter
    private var matchLists: MutableList<Match> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = "Search"
        presenter = SearchPresenter(this, ApiRepository(), Gson())
        adapter = EventAdapter(matchLists)
    }

    override fun showLoading() {
        progress_bar_search.visible()
        recycler_view_match_search.invisible()
        empty_search.invisible()
    }

    override fun hideLoading() {
        progress_bar_search.invisible()
        recycler_view_match_search.visible()
        empty_search.invisible()
    }

    override fun showEmpty() {
        progress_bar_search.invisible()
        recycler_view_match_search.invisible()
        empty_search.visible()
    }

    override fun showEventList(data: List<Match>) {
        matchLists.clear()
        matchLists.addAll(data)
        adapter.notifyDataSetChanged()
        recycler_view_match_search.layoutManager = LinearLayoutManager(this)
        recycler_view_match_search.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_matches, menu)
        val searchView = menu?.findItem(search_matches)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchMatch(newText)
                return false
            }
        })
        return true
    }

    override fun onDestroy() {
        presenter.cancelCoroutine()
        super.onDestroy()
    }
}
