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
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.ActingAdapter
import com.themovieviewer.presentation.paging.ActingDataSource
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

    private val peopleDetailsFragmentViewModel: PeopleDetailsFragmentViewModel by viewModels()
    private var _binding: FragmentPeopleBinding? = null
    private val args by navArgs<PeopleDetailsFragmentArgs>()
    @Inject
    lateinit var application: BaseApplication
    @Inject
    lateinit var movieRecommendationsAdapter: MovieRecommendationsAdapter
    @Inject
    lateinit var actingAdapter: ActingAdapter
    @Inject
    lateinit var daoMapper: DaoMapper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding: FragmentPeopleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        dataBinding.viewModel = peopleDetailsFragmentViewModel
        dataBinding.lifecycleOwner = this
        _binding = dataBinding

        val moviesRecyclerView: RecyclerView = dataBinding.root.findViewById(R.id.moviesRecyclerView)
        moviesRecyclerView.adapter = movieRecommendationsAdapter
        movieRecommendationsAdapter.onItemClick = {
            Log.d(TAG, it.toString())
            application.selectedMovie = it
            try {
                val action = PeopleDetailsFragmentDirections.actionPeopleDetailsToMovieDetails(it, false)
                findNavController().navigate(action)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val actingRv: RecyclerView = dataBinding.root.findViewById(R.id.actingList)
        actingRv.adapter = actingAdapter
        actingAdapter.onItemClick = {
            Log.d(TAG, it.toString())
        }

        val root: View = dataBinding.root
        Log.d(TAG, "Selected person : " + args.personId.toString())
        lifecycleScope.launch {
            peopleDetailsFragmentViewModel.movieList.collectLatest { pagedData ->
                movieRecommendationsAdapter.submitData(pagedData)
            }
        }

        lifecycleScope.launch {
            peopleDetailsFragmentViewModel.actingList.collectLatest { pagedData ->
                actingAdapter.submitData(pagedData)
            }
        }

        return dataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}