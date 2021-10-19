package com.sample.openweather.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.openweather.R
import com.sample.openweather.common.ViewModelFactory
import com.sample.openweather.databinding.FragmentGetweatherBinding
import com.sample.openweather.ui.GetWeatherViewModel.UiEvent
import com.sample.openweather.ui.GetWeatherViewModel.UiState
import com.sample.openweather.ui.adapter.GetWeatherAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class GetWeatherFragment : Fragment(R.layout.fragment_getweather) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<GetWeatherViewModel> { viewModelFactory }

    private var binding: FragmentGetweatherBinding? = null

    private lateinit var getWeatherAdapter: GetWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetweatherBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeUiState()
        observeUiEvent()
        viewModel.getWeather()
    }

    private fun initViews() {
        binding?.listWeatherDay?.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            getWeatherAdapter = GetWeatherAdapter(requireContext())
            adapter = getWeatherAdapter
        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Data -> getWeatherAdapter.setData(it.weatherList)
                else -> Unit
            }
        }
    }

    private fun observeUiEvent() {
        viewModel.uiEvent.observe(viewLifecycleOwner) {
            when (it) {
                is UiEvent.Error -> handleError(it.msg)
                else -> Unit
            }
        }
    }

    private fun handleError(msg: String) {
        val dialog = AlertDialog.Builder(requireContext()).setMessage(msg)
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
