package edu.cnm.deepdive.skywatch.app.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import edu.cnm.deepdive.skywatch.app.adapter.SearchResultsAdapter
import edu.cnm.deepdive.skywatch.app.databinding.FragmentSearchBinding
import edu.cnm.deepdive.skywatch.app.viewmodel.SearchViewModel
import jakarta.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var adapter: SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is where non-visual initialization occurs; this includes reading arguments passed
        //  in the args bundle.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupListeners()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        viewModel
            .searchResults
            .observe(viewLifecycleOwner) { results ->
                adapter.clear()
                adapter.addAll(results)
            }
    }

    private fun setupListeners() {
        binding?.run {
            searchResults.adapter = adapter
            submit
                .setOnClickListener {
                    val searchText = searchFilter
                        .text
                        .toString()
                        .trim()
                    if (searchText.length >= 3) {
                        viewModel.search(
                            searchText,
                            null,
                            null,
                        ) // TODO: Add values from date/time entry fields.
                    }
                }
        }
    }

}