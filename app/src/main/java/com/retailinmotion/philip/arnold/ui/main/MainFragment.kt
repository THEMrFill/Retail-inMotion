package com.retailinmotion.philip.arnold.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.retailinmotion.philip.arnold.R
import com.retailinmotion.philip.arnold.databinding.MainFragmentBinding
import com.retailinmotion.philip.arnold.utils.nonNullObserve
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private val stopAdapter = StopAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        with (binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = stopAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        with (viewModel) {
            forecast.nonNullObserve(viewLifecycleOwner) { forecast ->
                binding.forecast = forecast
            }
            trams.nonNullObserve(viewLifecycleOwner) { direction ->
                binding.direction = direction
                direction.trams?.let {
                    stopAdapter.setStops(it)
                }
            }
        }
    }
}