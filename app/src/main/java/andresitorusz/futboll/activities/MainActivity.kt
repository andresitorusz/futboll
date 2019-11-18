package andresitorusz.futboll.activities

import andresitorusz.futboll.R
import andresitorusz.futboll.fragments.MatchFragment
import andresitorusz.futboll.fragments.TeamFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {

            val fragment = MatchFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    fragment,
                    fragment.javaClass.simpleName
                )
                .commit()
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matches_fragment_item -> {
                    val fragment = MatchFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_container,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.teams_fragment_item -> {
                    val fragment = TeamFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_container,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
