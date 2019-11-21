package andresitorusz.futboll.activities

import andresitorusz.futboll.fragments.LeagueFragment
import andresitorusz.futboll.models.Item
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeagueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = intent.getParcelableExtra<Item>(LeagueFragment.PARCELABLE_ITEM_DATA)
        LeagueDetailActivityUI(item!!).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item!!)
        }
    }

    inner class LeagueDetailActivityUI(private val item: Item) : AnkoComponent<LeagueDetailActivity> {
        private val viewId = 1
        private val imageId = 2
        private val nameId = 3

        override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui) {
            scrollView {
                relativeLayout {
                    lparams(wrapContent, wrapContent)

                    view {
                        id = viewId
                        setBackgroundColor(Color.rgb(100, 128, 100))
                    }.lparams(matchParent, dip(150))

                    imageView {
                        id = imageId
                        item.image?.let { Picasso.get().load(item.image).fit().into(this) }
                    }.lparams(dip(100), dip(100)) {
                        centerHorizontally()
                        topMargin = dip(100)
                    }

                    textView {
                        id = nameId
                        text = item.name
                        textSize = 24f
                        setTypeface(null, Typeface.BOLD)
                    }.lparams {
                        below(imageId)
                        centerHorizontally()
                    }

                    textView {
                        padding = dip(16)
                        text = item.desc
                    }.lparams {
                        below(nameId)
                    }
                }
            }
        }
    }
}
