package com.themovieviewer.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.themovieviewer.databinding.FragmentMainBinding
import com.themovieviewer.presentation.ui.nowplaying.NowPlayingFragment
import com.themovieviewer.presentation.ui.toprated.TopRatedFragment
import com.themovieviewer.presentation.ui.popular.MoviePopularFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    @Inject lateinit var topRatedFragment: TopRatedFragment
    @Inject lateinit var nowPlayingFragment: NowPlayingFragment
    @Inject lateinit var moviePopularFragment: MoviePopularFragment

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(nowPlayingFragment)
        fragmentList.add(topRatedFragment)
        fragmentList.add(moviePopularFragment)

        binding.viewPager.adapter = MainFragmentAdapter(requireActivity(), fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text =
                when (position) {
                    0 -> "Now Playing"
                    1 -> "Top Rated"
                    2 -> "Popular"
                    else -> "Favorite Movies"
                }
        }.attach()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
