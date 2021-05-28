package com.themovieviewer.presentation.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.themovieviewer.databinding.FragmentSlideshowBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.ui.main.MainFragmentDirections
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private val slideshowViewModel: SlideshowViewModel by viewModels()
    private var _binding: FragmentSlideshowBinding? = null

    @Inject
    lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject
    lateinit var application: BaseApplication

    //region recyclerview-selection
    private val tracker by lazy {
        with(binding) {
            SelectionTracker.Builder(
                "Selection",
                favoritesList,
                MovieOneRowAdapter.SelectionKeyProvider(oneRowAdapter),
                MovieOneRowAdapter.SelectionDetailsLookup(favoritesList),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                MovieOneRowAdapter.SelectionPredicate(favoritesList)
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val tracker = this@apply
                        val adapter = (favoritesList.adapter as MovieOneRowAdapter)
                        tracker.selection.forEach {
                            val movie = adapter.peek(it.toInt())
                            Log.d(TAG, "position : $it Selection : $movie")
                        }
                    }
                })
            }
        }
    }
    //endregion

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.favoritesList.adapter = oneRowAdapter
//        oneRowAdapter.useTracker = false
//
//        if (oneRowAdapter.useTracker) {
//            tracker
//        } else {
            oneRowAdapter.onItemClick = {
                application.selectedMovie = it
                Log.d(TAG, "Selected Favorites Movie : ${it.original_title}")
                try {
                    val action = SlideshowFragmentDirections.actionSlideshowFragmentToMovieDetailsFragment(it)
                    findNavController().navigate(action)
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
//        }

        lifecycleScope.launch() {
            slideshowViewModel.favoritesList.collectLatest { pagedData ->
                oneRowAdapter.submitData(pagedData)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}