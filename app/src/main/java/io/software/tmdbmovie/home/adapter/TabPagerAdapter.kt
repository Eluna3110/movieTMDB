package io.software.tmdbmovie.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(fragmentManager : FragmentManager, private val fragments : ArrayList<Fragment>, private val tabTitles : ArrayList<String>): FragmentPagerAdapter(fragmentManager)
{
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount() : Int
    {
        return fragments.size
    }

    override fun getPageTitle(position : Int) : CharSequence?
    {
        return tabTitles[position]
    }
}