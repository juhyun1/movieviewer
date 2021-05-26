package com.themovieviewer.presentation.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.themovieviewer.databinding.FragmentMovieDetailsBinding
import com.themovieviewer.presentation.ui.gallery.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModels()
    private var _binding: FragmentMovieDetailsBinding? = null
//    @Inject
//    lateinit var twoColumns: MovieOneRowAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        binding.nowPlayingList.adapter = twoColumns
//        lifecycleScope.launch {
//            galleryViewModel.nowPlayingList.collectLatest { pagedData ->
//                twoColumns.submitData(pagedData)
//            }
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}