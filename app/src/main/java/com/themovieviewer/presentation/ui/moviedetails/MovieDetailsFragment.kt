package com.themovieviewer.presentation.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.databinding.FragmentMovieDetailsBinding
import com.themovieviewer.presentation.paging.CreditsAdapter
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
        val rv: RecyclerView = dataBinding.root.findViewById<RecyclerView>(R.id.creditsRecyclerView)
        rv.adapter = creditsAdapter
        (creditsAdapter as CreditsAdapter).onItemClick = {
            Log.d(TAG, it.toString())
        }

        val root: View = dataBinding.root
        Log.d(TAG, "Selected Movie : " + args.movie.toString())

//        movieDetailsFragmentViewModel.getCredits(args.movie)

        lifecycleScope.launch {
            movieDetailsFragmentViewModel.creditsList.collectLatest { pagedData ->
                creditsAdapter.submitData(pagedData)
            }
        }

//        movieDetailsFragmentViewModel.init(args.movie)
        return dataBinding.root
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
            args.movie.let{
                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                //just db test, have to implement insert movie to favorite db
                movieDetailsFragmentViewModel.insertFavoriteMovie(favorite, favoriteMovie)
            }
            true
        }

        R.id.action_remove -> {
            args.movie.let{
                val favorite = Favorites(name = it.original_title, kind = "movie", kindId = it.id, date = it.release_date)
                val favoriteMovie = daoMapper.mapFromDomainModel(it)

                //just db test, have to implement insert movie to favorite db
                movieDetailsFragmentViewModel.deleteFavoriteMovie(favorite, favoriteMovie)
                Log.d(TAG, "Inserted Favorite Movie to DB")
            }
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