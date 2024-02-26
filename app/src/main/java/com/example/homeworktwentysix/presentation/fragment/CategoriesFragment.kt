package com.example.homeworktwentysix.presentation.fragment

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktwentysix.data.common.Resource
import com.example.homeworktwentysix.databinding.FragmentCategoriesBinding
import com.example.homeworktwentysix.presentation.adapter.CategoriesRecyclerAdapter
import com.example.homeworktwentysix.presentation.base.BaseFragment
import com.example.homeworktwentysix.presentation.viewModels.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    private val viewModel: CategoriesViewModel by viewModels()

    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null


    override fun setUp() {
        setRecyclerUp()
        viewModel.getCategoriesList()
        bindObserves()

        viewModel.search("Earthmoving")
        bindSearch()
    }

    override fun listeners() {

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Cancel any previous postDelayed calls
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    // This code will execute after 1 second of inactivity
                    collectInput(s.toString())
                }.also { handler.postDelayed(it, 1000) } // Adjust the delay as needed
            }
        })

    }

    private fun setRecyclerUp(){
        categoriesAdapter = CategoriesRecyclerAdapter()
        binding.apply {
            categoriesRecycler.adapter = categoriesAdapter
            categoriesRecycler.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoriesFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbCategories.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbCategories.visibility = View.GONE
                            val res = it.responseData
                           categoriesAdapter.submitList(res)

                        }

                        is Resource.Failed -> {
                            binding.pbCategories.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }
            }
        }
    }

     private fun bindSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.foundFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbCategories.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbCategories.visibility = View.GONE
                            val res = it.responseData
                            d("Found results", res.toString())

                        }

                        is Resource.Failed -> {
                            binding.pbCategories.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }
            }
        }
    }

    private fun collectInput(input: String) {

            viewModel.search(input)
            bindSearch()

        d("Final input:", input)
    }

}