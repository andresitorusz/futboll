package andresitorusz.futboll.fragments.match


import andresitorusz.futboll.R
import andresitorusz.futboll.fragments.match.last.LastEventFragment
import andresitorusz.futboll.fragments.match.next.NextEventFragment
import andresitorusz.futboll.utils.ViewPagerUtils
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_match.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        page = mutableMapOf(LastEventFragment() to getString(R.string.last_match), NextEventFragment() to getString(R.string.next_match))
        match_view_pager.adapter = ViewPagerUtils(childFragmentManager, page)
        match_tab.setupWithViewPager(match_view_pager)
    }

}
