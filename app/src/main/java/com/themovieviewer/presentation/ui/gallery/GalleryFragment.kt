package com.themovieviewer.presentation.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.themovieviewer.databinding.FragmentGalleryBinding
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.paging.MovieTwoColumnsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModels()
    private var _binding: FragmentGalleryBinding? = null
    @Inject
    lateinit var twoColumns: MovieOneRowAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.nowPlayingList.adapter = twoColumns
        lifecycleScope.launch {
            galleryViewModel.nowPlayingList.collectLatest { pagedData ->
                twoColumns.submitData(pagedData)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}