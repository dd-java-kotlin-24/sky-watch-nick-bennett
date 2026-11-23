package edu.cnm.deepdive.skywatch.app.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil3.load
import dagger.hilt.android.AndroidEntryPoint
import edu.cnm.deepdive.skywatch.app.R
import edu.cnm.deepdive.skywatch.app.databinding.FragmentItemBinding
import edu.cnm.deepdive.skywatch.app.viewmodel.SearchViewModel

@AndroidEntryPoint
class ItemFragment : Fragment() {

    private lateinit var _binding: FragmentItemBinding
    private val binding: FragmentItemBinding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class]
        viewModel
            .selectedItem
            .observe(viewLifecycleOwner) { searchResult ->
                Log.d(tag, searchResult.image.toString())
                binding.loading.visibility = View.VISIBLE
                binding.image.load(searchResult.image.toString()) {
                    listener(
                        onSuccess = { _, _ -> binding.loading.visibility = View.GONE },
                        onError = { _, result ->
                            // FIXME: Display Snackbar with the error.
                            binding.loading.visibility = View.GONE
                        },
                        onCancel = { binding.loading.visibility = View.GONE }
                    )
                }
            }
    }

    companion object {
        private val tag = ItemFragment::class.java.simpleName
    }

}