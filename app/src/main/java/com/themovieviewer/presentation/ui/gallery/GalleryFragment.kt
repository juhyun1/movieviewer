package com.themovieviewer.presentation.ui.gallery

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
import com.themovieviewer.data.*
import com.themovieviewer.data.vo.Favorites
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
                            if(galleryViewModel.favoriteAddMode) {
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
                                        val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie)
                                        findNavController().navigate(action)
                                    } catch(e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                    val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(it)
                    findNavController().navigate(action)
                } catch(e: Exception) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_add -> {
            galleryViewModel.favoriteAddMode = true
            requireActivity().invalidateOptionsMenu()
            true
        }

        R.id.action_remove -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        R.id.action_done -> {
            galleryViewModel.favoriteAddMode = false
            application.selectedMovie?.let{
                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                //just db test, have to implement insert movie to favorite db
                galleryViewModel.insertFavoriteMovie(favorite, favoriteMovie)
                Log.d(TAG, "Inserted Favorite Movie to DB")
            }

            tracker.clearSelection()
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
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