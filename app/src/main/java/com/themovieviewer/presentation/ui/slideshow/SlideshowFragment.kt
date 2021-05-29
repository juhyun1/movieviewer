package com.themovieviewer.presentation.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.data.Favorites
import com.themovieviewer.databinding.FragmentSlideshowBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
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
    @Inject lateinit var daoMapper: DaoMapper

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
                SelectionPredicates.createSelectSingleAnything()
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        try {
                            val tracker = this@apply
                            val adapter = (favoritesList.adapter as MovieOneRowAdapter)
                            tracker.selection.forEach {
                                val movie = adapter.peek(it.toInt())
                                Log.d(TAG, "position : $it Selection : $movie")
                                if(slideshowViewModel.favoriteRemoveMode) {
                                    if (movie == null) {
                                        application.selectedMovie = null
                                    } else {
                                        application.selectedMovie = movie
                                        Log.d(TAG, "set Favorite Movie to application.selectedMovie")
                                    }
                                } else {
                                    movie?.let {
                                        application.selectedMovie = movie
                                        try {
                                            val action = SlideshowFragmentDirections.actionSlideshowFragmentToMovieDetailsFragment(movie)
                                            findNavController().navigate(action)
                                        } catch(e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
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
    ): View? {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.favoritesList.adapter = oneRowAdapter
        oneRowAdapter.useTracker = true

        if (oneRowAdapter.useTracker) {
            oneRowAdapter.tracker = tracker
        } else {
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
        }

        lifecycleScope.launch() {
            slideshowViewModel.favoritesList.collectLatest { pagedData ->
                oneRowAdapter.submitData(pagedData)
            }
        }
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_remove -> {
            slideshowViewModel.favoriteRemoveMode = true
            requireActivity().invalidateOptionsMenu()
            true
        }

        R.id.action_done -> {
            slideshowViewModel.favoriteRemoveMode = false
            application.selectedMovie?.let{

                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                //just db test, have to implement insert movie to favorite db
                slideshowViewModel.deleteFavoriteMovie(favorite, favoriteMovie)
                Log.d(TAG, "Inserted Favorite Movie to DB")
            }

            tracker.clearSelection()
            requireActivity().invalidateOptionsMenu()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val removeItem = menu.findItem(R.id.action_remove)
        val doneItem = menu.findItem(R.id.action_done)

        if (slideshowViewModel.favoriteRemoveMode) {
            removeItem.isVisible = false
            doneItem.isVisible = true
        } else {
            removeItem.isVisible = true
            doneItem.isVisible = false
        }
    }
}