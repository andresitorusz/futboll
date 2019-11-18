package andresitorusz.futboll.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class ViewPagerUtils(fm: FragmentManager, private val page: Map<Fragment, String>) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment =
        page.keys.toList()[position]

    override fun getCount(): Int =
        page.size

    override fun getPageTitle(position: Int): String =
        page.values.toList()[position]

}