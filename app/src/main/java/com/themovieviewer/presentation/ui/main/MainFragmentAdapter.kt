package com.themovieviewer.presentation.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentAdapter(private val fa: FragmentActivity, private val fragments: List<Fragment>): FragmentStateAdapter(fa) {

    companion object {
        private const val PAGES = 3
    }

    override fun getItemCount() = PAGES

    override fun createFragment(position: Int) = fragments[position]
}