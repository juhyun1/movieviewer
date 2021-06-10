package com.themovieviewer.presentation.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.databinding.FragmentMovieDetailsBinding
import com.themovieviewer.domain.model.Trailer
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.CreditsAdapter
import com.themovieviewer.presentation.paging.MovieRecommendationsAdapter
import com.themovieviewer.presentation.paging.VideosAdapter
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsFragmentViewModel: MovieDetailsFragmentViewModel by viewModels()
    private var _binding: FragmentMovieDetailsBinding? = null
    private val args by navArgs<MovieDetailsFragmentArgs>()
    @Inject lateinit var creditsAdapter: CreditsAdapter
    @Inject lateinit var application: BaseApplication
    @Inject lateinit var movieRecommendationsAdapter: MovieRecommendationsAdapter
    @Inject lateinit var videosAdapter: VideosAdapter
    @Inject lateinit var daoMapper: DaoMapper

    // This property is only valid between onCreateView and
    // onDestroyView.
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
//        val _bind = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val dataBinding: FragmentMovieDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        dataBinding.viewModel = movieDetailsFragmentViewModel
        dataBinding.lifecycleOwner = this
        _binding = dataBinding
        val rv: RecyclerView = dataBinding.root.findViewById(R.id.creditsRecyclerView)
        rv.adapter = creditsAdapter
        creditsAdapter.onItemClick = {
            Log.d(TAG, it.toString())
            application.selectedPerson = it.id
            val action = MovieDetailsFragmentDirections.actionMovieDetailsToPeopleDetails(it.id)
            findNavController().navigate(action)
        }

        val recommendationRv: RecyclerView = dataBinding.root.findViewById(R.id.recommendationsRecyclerView)
        recommendationRv.adapter = movieRecommendationsAdapter
        movieRecommendationsAdapter.onItemClick = {
            Log.d(TAG, it.toString())
            application.selectedMovie = it
            try {
                val action = MovieDetailsFragmentDirections.actionMovieDetailsToMovieDetails(it, false)
                findNavController().navigate(action)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val videoRv: RecyclerView = dataBinding.root.findViewById(R.id.videoRecyclerView)
        videoRv.adapter = videosAdapter
        videosAdapter.onItemClick = {
            Log.d(TAG, it.toString())
            playTrailer(it)
        }

        val root: View = dataBinding.root
        Log.d(TAG, "Selected Movie : " + args.movie.toString())

        lifecycleScope.launch {
            movieDetailsFragmentViewModel.creditsList.collectLatest { pagedData ->
                creditsAdapter.submitData(pagedData)
            }
        }

        lifecycleScope.launch {
            movieDetailsFragmentViewModel.movieList.collectLatest { pagedData ->
                movieRecommendationsAdapter.submitData(pagedData)
            }
        }

        lifecycleScope.launch {
            movieDetailsFragmentViewModel.videoList.collectLatest { pagedData ->
                videosAdapter.submitData(pagedData)
            }
        }

        return dataBinding.root
    }

    private fun playTrailer(trailer: Trailer) {
//        trailer.let {
//            val sendIntent = Intent(requireContext(), TrailerActivity::class.java)
//            sendIntent.putExtra("trailer", movieDetailsFragmentViewModel.trailer)
//            startActivity(sendIntent)
//        }

        trailer.let{
            val intent = YouTubeStandalonePlayer.createVideoIntent(requireActivity(),
                trailer.id,
                trailer.key,
                0,
                true,
                true
            )
            requireActivity().startActivity(intent)
        }

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
            args.movie.let {
                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                movieDetailsFragmentViewModel.insertFavoriteMovie(favorite, favoriteMovie)
            }
            true
        }

        R.id.action_remove -> {
            args.movie.let {
                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                movieDetailsFragmentViewModel.deleteFavoriteMovie(favorite, favoriteMovie)
                Log.d(TAG, "Inserted Favorite Movie to DB")
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val addItem = menu.findItem(R.id.action_add)
        val removeItem = menu.findItem(R.id.action_remove)

        if (args.fromFavoriteScreen) {
            addItem.isVisible = false
            removeItem.isVisible = true
        } else {
            addItem.isVisible = true
            removeItem.isVisible = false
        }
    }
}