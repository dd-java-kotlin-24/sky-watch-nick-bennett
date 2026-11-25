package edu.cnm.deepdive.skywatch.app.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil3.load
import coil3.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
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
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class]
        viewModel
            .selectedItem
            .observe(viewLifecycleOwner) { searchResult ->
                binding.loading.visibility = View.VISIBLE
                binding.image.contentDescription = searchResult.description
                binding.image.tooltipText = searchResult.description
                binding.image.load(searchResult.image.toString()) { setHandlers() }
            }
    }

    private fun ImageRequest.Builder.setHandlers(): Unit {
        listener(
            onSuccess = { _, _ -> binding.loading.visibility = View.GONE },
            onError = { _, _ ->
                Snackbar
                    .make(binding.root, R.string.image_load_failure, Snackbar.LENGTH_LONG)
                    .addCallback(object: Snackbar.Callback() {
                        override fun onDismissed(snackbar: Snackbar, event: Int) {
                            super.onDismissed(snackbar, event)
                            binding.root.findNavController().navigateUp()
                        }
                    })
                    .show()
                binding.loading.visibility = View.GONE
            },
            onCancel = {
                binding.loading.visibility = View.GONE
                binding.root.findNavController().navigateUp()
            }
        )
    }
}
