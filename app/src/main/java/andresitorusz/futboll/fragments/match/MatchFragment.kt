package andresitorusz.futboll.fragments.match


import andresitorusz.futboll.R
import andresitorusz.futboll.R.id.search_matches
import andresitorusz.futboll.activities.SearchActivity
import andresitorusz.futboll.fragments.match.last.LastEventFragment
import andresitorusz.futboll.fragments.match.next.NextEventFragment
import andresitorusz.futboll.utils.ViewPagerUtils
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {
    private lateinit var page: Map<Fragment, String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = mutableMapOf(
            LastEventFragment() to getString(R.string.last_match),
            NextEventFragment() to getString(R.string.next_match)
        )
        match_view_pager.adapter = ViewPagerUtils(childFragmentManager, page)
        match_tab.setupWithViewPager(match_view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_matches, menu)

        val searchView = menu.findItem(search_matches)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnSearchClickListener {
            context?.startActivity<SearchActivity>()
        }
    }


//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                activity?.finish()
//                true
//            }
//            search_matches -> {
////                requireActivity().run {
////                    startActivity(Intent(this@MatchFragment.context, SearchActivity::class.java))
////                }
////                context?.startActivity<SearchActivity>()
////                true
//                val intent = Intent(activity, SearchActivity::class.java)
//                startActivity(intent)
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//
//    }

}
