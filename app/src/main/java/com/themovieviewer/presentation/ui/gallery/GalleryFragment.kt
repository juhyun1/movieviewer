package com.themovieviewer.presentation.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.themovieviewer.data.*
import com.themovieviewer.databinding.FragmentGalleryBinding
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.ui.main.MainFragmentDirections
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                MovieOneRowAdapter.SelectionPredicate(nowPlayingList)
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val tracker = this@apply
                        val adapter = (nowPlayingList.adapter as MovieOneRowAdapter)
                        var movie_: Movie? = null
                        tracker.selection.forEach {
                            val movie = adapter.peek(it.toInt())
                            movie_ = movie
                            Log.d(TAG, "position : $it Selection : $movie")
                        }

                        val fa = Favorites(name = movie_!!.original_title, kind = "movie", kindId = movie_!!.id, date = movie_!!.release_date)
                        val fmovie = daoMapper.mapFromDomainModel(movie_!!)

                        //just db test, have to implement insert movie to favorite db
                        galleryViewModel.test(fa, fmovie)
                    }
                })
            }
        }
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.nowPlayingList.adapter = oneRowAdapter
        oneRowAdapter.useTracker = false

        if (oneRowAdapter.useTracker) {
            tracker
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
}