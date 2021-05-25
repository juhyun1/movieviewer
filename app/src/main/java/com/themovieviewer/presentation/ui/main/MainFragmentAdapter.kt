package com.themovieviewer.presentation.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.themovieviewer.presentation.ui.gallery.GalleryFragment
import com.themovieviewer.presentation.ui.home.HomeFragment

class MainFragmentAdapter(private val fa: FragmentActivity, private val fragments: List<Fragment>): FragmentStateAdapter(fa) {

    companion object {
        private const val PAGES = 2
    }

    override fun getItemCount() = PAGES

    override fun createFragment(position: Int) =
        if (position == 0) {
            HomeFragment()
        } else {
            GalleryFragment()
        }
}