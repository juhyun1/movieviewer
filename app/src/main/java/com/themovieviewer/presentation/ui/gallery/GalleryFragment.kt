package com.themovieviewer.presentation.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.databinding.FragmentGalleryBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.ui.main.MainFragmentDirections
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModels()
    private var _binding: FragmentGalleryBinding? = null
    @Inject lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject lateinit var application: BaseApplication
    @Inject lateinit var daoMapper: DaoMapper

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

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.nowPlayingList.adapter = oneRowAdapter
        oneRowAdapter.useTracker = true

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

        lifecycleScope.launch {
            galleryViewModel.nowPlayingList.collectLatest { pagedData ->
                oneRowAdapter.submitData(pagedData)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_settings -> {
            galleryViewModel.favoriteAddMode = true
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

        if (galleryViewModel.favoriteAddMode) {
            addItem.isVisible = false
            doneItem.isVisible = true
        } else {
            addItem.isVisible = true
            doneItem.isVisible = false
        }
    }
}
