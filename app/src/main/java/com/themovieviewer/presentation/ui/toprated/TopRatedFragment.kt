package com.themovieviewer.presentation.ui.toprated

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.themovieviewer.R
import com.themovieviewer.databinding.FragmentTopRatedBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.ui.main.MainFragmentDirections
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private val viewModel: TopRatedViewModel by viewModels()
    private var _binding: FragmentTopRatedBinding? = null
    @Inject
    lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject
    lateinit var application: BaseApplication

    //region recyclerview-selection
    private val tracker by lazy {
        with(binding) {
            SelectionTracker.Builder(
                "Selection",
                movieList,
                MovieOneRowAdapter.SelectionKeyProvider(oneRowAdapter),
                MovieOneRowAdapter.SelectionDetailsLookup(movieList),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                SelectionPredicates.createSelectSingleAnything()
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val tracker = this@apply
                        val adapter = (movieList.adapter as MovieOneRowAdapter)
                        tracker.selection.forEach {
                            val movie = adapter.peek(it.toInt())
                            Log.d(TAG, "position : $it Selection : $movie")
                            movie?.let {
                                application.selectedMovie = movie
                                try {
                                    val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie, false)
                                    findNavController().navigate(action)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                })
            }
        }
    }
    //endregion

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

        binding.movieList.adapter = oneRowAdapter
        oneRowAdapter.useTracker = false

        if (oneRowAdapter.useTracker) {
            oneRowAdapter.tracker = tracker
        } else {
            oneRowAdapter.onItemClick = {
                application.selectedMovie = it
                try {
                    val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(it, false)
                    findNavController().navigate(action)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        oneRowAdapter.addLoadStateListener { combinedLoadStates ->
            val error = combinedLoadStates.source.refresh is LoadState.Error
            if (error) {
                oneRowAdapter.retry()
            }
        }
        binding.refreshlayout.setOnRefreshListener {
            oneRowAdapter.refresh()
            binding.refreshlayout.isRefreshing = false
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.topRatedList.collectLatest { pagedData ->
                oneRowAdapter.submitData(pagedData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_settings -> {
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
