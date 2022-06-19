package com.themovieviewer.presentation.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.themovieviewer.BaseApplication
import com.themovieviewer.databinding.FragmentPopularBinding
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviePopularFragment : Fragment() {

    private val viewModel: MoviePopularViewModel by viewModels()
    private var _binding: FragmentPopularBinding? = null
    @Inject
    lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject
    lateinit var application: BaseApplication

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)

        initAdapter()
        initObserve()

        return binding.root
    }

    private fun initAdapter() {

    }

    private fun initObserve() {
//        lifecycleScope.launch {
//            viewModel.movieList.collectLatest { pagedData ->
//                oneRowAdapter.submitData(pagedData)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
