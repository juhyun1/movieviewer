package com.themovieviewer.presentation.ui.people

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
import com.themovieviewer.R
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.databinding.FragmentMovieDetailsBinding
import com.themovieviewer.databinding.FragmentPeopleBinding
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.CreditsAdapter
import com.themovieviewer.presentation.paging.MovieRecommendationsAdapter
import com.themovieviewer.presentation.ui.moviedetails.MovieDetailsFragmentArgs
import com.themovieviewer.presentation.ui.moviedetails.MovieDetailsFragmentDirections
import com.themovieviewer.presentation.ui.moviedetails.MovieDetailsFragmentViewModel
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PeopleDetailsFragment : Fragment() {

    private val movieDetailsFragmentViewModel: MovieDetailsFragmentViewModel by viewModels()
    private var _binding: FragmentPeopleBinding? = null
    private val args by navArgs<MovieDetailsFragmentArgs>()
    @Inject
    lateinit var creditsAdapter: CreditsAdapter
    @Inject
    lateinit var application: BaseApplication
    @Inject
    lateinit var movieRecommendationsAdapter: MovieRecommendationsAdapter
    @Inject
    lateinit var daoMapper: DaoMapper

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
        val dataBinding: FragmentPeopleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        dataBinding.viewModel = movieDetailsFragmentViewModel
        dataBinding.lifecycleOwner = this
        _binding = dataBinding

        //region TODO
        //        val rv: RecyclerView = dataBinding.root.findViewById(R.id.creditsRecyclerView)
//        rv.adapter = creditsAdapter
//        creditsAdapter.onItemClick = {
//            Log.d(TAG, it.toString())
//        }
//
//        val recommendationRv: RecyclerView = dataBinding.root.findViewById(R.id.recommendationsRecyclerView)
//        recommendationRv.adapter = movieRecommendationsAdapter
//        movieRecommendationsAdapter.onItemClick = {
//            Log.d(TAG, it.toString())
//            application.selectedMovie = it
//            try {
//                val action = MovieDetailsFragmentDirections.actionMovieDetailsToMovieDetails(it, false)
//                findNavController().navigate(action)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        val root: View = dataBinding.root
//        Log.d(TAG, "Selected Movie : " + args.movie.toString())
//
//        lifecycleScope.launch {
//            movieDetailsFragmentViewModel.creditsList.collectLatest { pagedData ->
//                creditsAdapter.submitData(pagedData)
//            }
//        }
//
//        lifecycleScope.launch {
//            movieDetailsFragmentViewModel.movieList.collectLatest { pagedData ->
//                movieRecommendationsAdapter.submitData(pagedData)
//            }
//        }
        //endregion

        return dataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}