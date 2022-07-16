package com.themovieviewer.presentation.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.themovieviewer.BaseApplication
import com.themovieviewer.databinding.FragmentNowPalyingBinding
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NowPlayingFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject
    lateinit var application: BaseApplication

    private val viewModel: NowPlayingViewModel by viewModels()
    private var _binding: FragmentNowPalyingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPalyingBinding.inflate(inflater, container, false)

        initAdapter()
        initObserve()

        return binding.root
    }

    private fun initAdapter() {
    }

    private fun initObserve() {
//        lifecycleScope.launch {
//            viewModel.nowPlayingList.collectLatest { pagedData ->
//                oneRowAdapter.submitData(pagedData)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
    }
}
