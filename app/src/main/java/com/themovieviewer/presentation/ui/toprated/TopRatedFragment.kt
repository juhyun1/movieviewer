package com.themovieviewer.presentation.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.themovieviewer.BaseApplication
import com.themovieviewer.databinding.FragmentTopRatedBinding
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private val viewModel: TopRatedViewModel by viewModels()
    private var _binding: FragmentTopRatedBinding? = null
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
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initAdapter()
        initObserve()

        return root
    }
    private fun initAdapter() {
    }

    private fun initObserve() {
//        lifecycleScope.launch {
//            viewModel.topRatedList.collectLatest { pagedData ->
//                oneRowAdapter.submitData(pagedData)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}
