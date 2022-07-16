package com.themovieviewer.presentation.ui.people

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.themovieviewer.BaseApplication
import com.themovieviewer.databinding.FragmentPeopleBinding
import com.themovieviewer.presentation.paging.ActingAdapter
import com.themovieviewer.presentation.paging.MovieRecommendationsAdapter
import com.themovieviewer.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleDetailsFragment : Fragment() {

    private val viewModel: PeopleDetailsFragmentViewModel by viewModels()
    private var _binding: FragmentPeopleBinding? = null
    private val args by navArgs<PeopleDetailsFragmentArgs>()
    @Inject
    lateinit var application: BaseApplication
    @Inject
    lateinit var movieRecommendationsAdapter: MovieRecommendationsAdapter
    @Inject
    lateinit var actingAdapter: ActingAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        initAdapter()
        initObserve()

        binding.lifecycleOwner = this
        Log.d(TAG, "Selected person : " + args.personId.toString())

        return binding.root
    }

    private fun initAdapter() {
    }

    private fun initObserve() {
//        lifecycleScope.launch {
//            viewModel.movieList.collectLatest { pagedData ->
//                movieRecommendationsAdapter.submitData(pagedData)
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.actingList.collectLatest { pagedData ->
//                actingAdapter.submitData(pagedData)
//            }
//        }
//
//        viewModel.initModelDone.observe(viewLifecycleOwner) {
//            if (it) {
//                binding.model = viewModel.model
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
