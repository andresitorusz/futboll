package andresitorusz.futboll.fragments.team.overview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andresitorusz.futboll.R
import kotlinx.android.synthetic.main.fragment_overview.*

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {
    companion object {
        private const val KEY_OVERVIEW = "key_overview"
        fun newInstance(values: String?): OverviewFragment {
            val overviewTeamFragment =
                OverviewFragment()
            val args = Bundle()
            args.putString(KEY_OVERVIEW, values)
            overviewTeamFragment.arguments = args
            return overviewTeamFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val overviewText = arguments?.getString(KEY_OVERVIEW)
        overview_text.text = overviewText
    }

}
