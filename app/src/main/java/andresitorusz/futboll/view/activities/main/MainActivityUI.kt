package andresitorusz.futboll.view.activities.main

import andresitorusz.futboll.R
import andresitorusz.futboll.utils.bottomNavigation
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        constraintLayout {
            val fragmentContainer = frameLayout {
                id = R.id.fragment_container
            }.lparams {
                width = matchParent
                height = matchConstraint
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
                bottomToTop = R.id.bottom_nav_view
            }

            val bottomNavigation = bottomNavigation {
                id = R.id.bottom_nav_view
                inflateMenu(R.menu.bottom_nav_menu)
            }.lparams {
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToBottom = R.id.fragment_container
                bottomToBottom = ConstraintSet.PARENT_ID
            }
        }
    }
}