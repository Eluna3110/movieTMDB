package io.software.tmdbmovie.home.adapter

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_viewPager")
fun setWithViewPager(tabLayout : TabLayout, pager : ViewPager) {
    tabLayout.setupWithViewPager(pager)
}

