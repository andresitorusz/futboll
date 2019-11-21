package andresitorusz.futboll.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import andresitorusz.futboll.R
import andresitorusz.futboll.activities.LeagueDetailActivity
import andresitorusz.futboll.activities.MainActivity
import andresitorusz.futboll.adapters.LeagueAdapter
import andresitorusz.futboll.models.Item
import andresitorusz.futboll.models.League
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    companion object {
        const val PARCELABLE_ITEM_DATA = "Item"
    }

    private var items: MutableList<Item> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = LeagueFragmentUI(items).createView(AnkoContext.create(ctx, this))

    inner class LeagueFragmentUI(private val item: List<Item>) : AnkoComponent<LeagueFragment> {
        override fun createView(ui: AnkoContext<LeagueFragment>) = with(ui) {
            verticalLayout {
                lparams(matchParent, wrapContent)

                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, 1))
                    adapter = LeagueAdapter(items) {
                        startActivity<LeagueDetailActivity>(PARCELABLE_ITEM_DATA to it)
                    }
                }
            }
        }

    }

    private fun loadData() {
        val image = resources.obtainTypedArray(R.array.league_images)
        val name = resources.getStringArray(R.array.league_names)
        val desc = resources.getStringArray(R.array.league_description)

        items.clear()

        for (i in name.indices) {
            items.add(Item(image.getResourceId(i, 0), name[i], desc[i]))
        }
        image.recycle()
    }


}
