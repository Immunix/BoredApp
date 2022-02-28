package com.immunix.boredapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.immunix.boredapp.R
import com.immunix.boredapp.databinding.FragmentMainBinding
import com.immunix.boredapp.state.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        binding.refreshData.setOnClickListener {
            viewModel.fetchData()
        }
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() = binding.apply {
        viewModel.boredActivity.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> {
                    activityName.text = state.data.activity
                    activityType.text = state.data.type
                    progressBar.visibility = View.GONE
                    Log.d("OBSERVE", state.data.activity)
                }
                is ViewState.Failure -> {
                    activityName.text = state.msg
                    activityType.text = ""
                    progressBar.visibility = View.GONE
                    Log.d("OBSERVE", state.msg)
                }
                ViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    Log.d("OBSERVE", "LOADING")
                }
            }
        }
    }
}