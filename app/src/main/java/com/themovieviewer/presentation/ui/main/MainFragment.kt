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
import com.themovieviewer.presentation.ui.slideshow.SlideshowFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel: MainFragmentViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    @Inject
    lateinit var homeFragment: HomeFragment
    @Inject
    lateinit var galleryFragment: GalleryFragment

    @Inject
    lateinit var slideshowFragment: SlideshowFragment
//    @Inject
//    lateinit var topRatedAdapter: TopRatedAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val fragmentList = ArrayList<Fragment>()

        fragmentList.add(homeFragment)
        fragmentList.add(galleryFragment)
        fragmentList.add(slideshowFragment)

        binding.viewPager.adapter = MainFragmentAdapter(requireActivity(), fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text =
                if (position == 0) {
                    "Top Rated"
                } else if (position == 1) {
                    "Now Playing"
                } else {
                    "Favorite Movies"
                }
        }.attach()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        homeFragment.onDestroyView()
        galleryFragment.onDestroyView()
    }
}
