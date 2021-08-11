package com.themovieviewer.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.themovieviewer.databinding.FragmentMainBinding
import com.themovieviewer.presentation.ui.gallery.GalleryFragment
import com.themovieviewer.presentation.ui.home.HomeFragment
import com.themovieviewer.presentation.ui.popular.MoviePopularFragment
import com.themovieviewer.presentation.ui.slideshow.SlideshowFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    @Inject lateinit var homeFragment: HomeFragment
    @Inject lateinit var galleryFragment: GalleryFragment
    @Inject lateinit var slideshowFragment: SlideshowFragment
    @Inject lateinit var moviePopularFragment: MoviePopularFragment

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(galleryFragment)
        fragmentList.add(homeFragment)
        fragmentList.add(moviePopularFragment)
        fragmentList.add(slideshowFragment)

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
