package com.reactions.fixt.presentation.ui.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reactions.fixt.presentation.ui.features.fixtures.FixturesFragment
import com.reactions.fixt.presentation.ui.features.results.ResultsFragment

class MainFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FixturesFragment()
            1 -> return ResultsFragment()
            else -> return FixturesFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = if(position == 0) {
            "Fixtures"
        } else {
            "Results"
        }
        return title
    }
}
