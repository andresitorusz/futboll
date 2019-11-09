package andresitorusz.futboll.view.fragments.match

import andresitorusz.futboll.R
import android.view.View
import org.jetbrains.anko.*

class MatchFragmentUI : AnkoComponent<MatchFragment> {
    override fun createView(ui: AnkoContext<MatchFragment>): View = with(ui){
        relativeLayout {
            with(resources) {
                textView {
                    text = "Match"
                    textColor = getColor(R.color.colorPrimaryDark)
                    textSize = 20f
                }.lparams { centerInParent() }
            }
        }
    }
}