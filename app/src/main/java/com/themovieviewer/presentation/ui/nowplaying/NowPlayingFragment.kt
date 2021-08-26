package com.themovieviewer.presentation.ui.nowplaying

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.databinding.FragmentNowPalyingBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.ui.main.MainFragmentDirections
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.String
import javax.inject.Inject


@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private val viewModel: NowPlayingViewModel by viewModels()
    private var _binding: FragmentNowPalyingBinding? = null
    @Inject lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject lateinit var application: BaseApplication
    @Inject lateinit var daoMapper: DaoMapper

    //region recyclerview-selection
    private val tracker by lazy {
        with(binding) {
            SelectionTracker.Builder(
                "Selection",
                nowPlayingList,
                MovieOneRowAdapter.SelectionKeyProvider(oneRowAdapter),
                MovieOneRowAdapter.SelectionDetailsLookup(nowPlayingList),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                SelectionPredicates.createSelectSingleAnything()
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val tracker = this@apply
                        val adapter = (nowPlayingList.adapter as MovieOneRowAdapter)
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
        _binding = FragmentNowPalyingBinding.inflate(inflater, container, false)




        initAdapter()
        initObserve()

        return binding.root
    }

    private fun initAdapter() {
        binding.nowPlayingList.adapter = oneRowAdapter
        oneRowAdapter.useTracker = false

        if (oneRowAdapter.useTracker) {
            oneRowAdapter.tracker = tracker
        } else {
            oneRowAdapter.onItemClick = {
                application.selectedMovie = it
                try {
                    val action =
                        MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(it, false)
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
            viewModel.nowPlayingList.collectLatest { pagedData ->
                oneRowAdapter.submitData(pagedData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_settings -> {
            viewModel.favoriteAddMode = true
            requireActivity().invalidateOptionsMenu()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val addItem = menu.findItem(R.id.action_add)
        val doneItem = menu.findItem(R.id.action_done)

        if (viewModel.favoriteAddMode) {
            addItem.isVisible = false
            doneItem.isVisible = true
        } else {
            addItem.isVisible = true
            doneItem.isVisible = false
        }
    }
}