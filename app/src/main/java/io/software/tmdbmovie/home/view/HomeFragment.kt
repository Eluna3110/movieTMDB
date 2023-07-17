package io.software.tmdbmovie.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.software.tmdbmovie.R
import io.software.tmdbmovie.databinding.MainFragmentBinding
import io.software.tmdbmovie.home.adapter.TabPagerAdapter
import io.software.tmdbmovie.maps.view.MapsFragment
import io.software.tmdbmovie.movie.view.MovieFragment
import io.software.tmdbmovie.perfil.view.ProfileFragment
import io.software.tmdbmovie.storage.view.StorageFragment

class HomeFragment : Fragment()
{
    private lateinit var binding: MainFragmentBinding
    private lateinit var tabPagerAdapter : TabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        val listFragments = ArrayList<Fragment>()
        listFragments.add(ProfileFragment())
        listFragments.add(MovieFragment())
        listFragments.add(MapsFragment())
        listFragments.add(StorageFragment())

        val lisTitles = ArrayList<String>()
        lisTitles.add(getString(R.string.tab_profile))
        lisTitles.add(getString(R.string.tab_movie))
        lisTitles.add(getString(R.string.tab_maps))
        lisTitles.add(getString(R.string.tab_storage))


        tabPagerAdapter = TabPagerAdapter(fragmentManager = childFragmentManager, fragments = listFragments, tabTitles =  lisTitles)
        binding.pagerAdapter = tabPagerAdapter
    }

}