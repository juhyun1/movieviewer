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
    @Inject
    lateinit var oneRowAdapter: MovieOneRowAdapter
    @Inject
    lateinit var application: BaseApplication

    private val tracker by lazy {
        with(binding) {
            SelectionTracker.Builder(
                "Selection",
                nowPlayingList,
                MovieOneRowAdapter.SelectionKeyProvider(nowPlayingList),
                MovieOneRowAdapter.SelectionDetailsLookup(nowPlayingList),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                MovieOneRowAdapter.SelectionPredicate(nowPlayingList)
            ).build().apply {
                addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val tracker = this@apply

                        val sel = tracker.selection
                        val sel2 = tracker.selection
//                        if (tracker.hasSelection() && menu.findItem(MENU_DELETE) == null) {
//                            menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete")
//                                .setIcon(R.drawable.ic_delete)
//                                .setOnMenuItemClickListener {
//                                    tracker.selection.forEach {
//                                        val holder = recyclerView.findViewHolderForItemId(it)
//                                        if (holder is SelectionAdapter.SelectionHolder) {
//                                            list.remove(holder.element)
//                                        }
//                                    }
//
//                                    tracker.clearSelection()
//                                    adapter.notifyDataSetChanged()
//                                    true
//                                }
//                                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//                        } else if (!tracker.hasSelection() && menu.findItem(MENU_DELETE) != null){
//                            menu.removeItem(MENU_DELETE)
//                        }

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
        oneRowAdapter.tracker = tracker

        (binding.nowPlayingList.adapter as MovieOneRowAdapter).onItemClick = {
            application.selectedMovie = it
            try {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(it)
                findNavController().navigate(action)
            } catch(e: Exception) {
                e.printStackTrace()
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